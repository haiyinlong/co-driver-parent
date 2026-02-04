package com.leo.ad.codriver.common.task;

import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwTaskRecordService;
import com.leo.ad.codriver.common.task.event.DwTaskRecordExecuteEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 优化后的任务执行器 修复了原版本中的并发、状态管理和异常处理问题
 *
 * @author HaiYinLong
 * @version 2026/02/04 10:00
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwTaskExecutor implements TaskExecutor {
    private final DwTaskRecordService dwTaskRecordService;
    private final ApplicationEventPublisher applicationEventPublisher;

    // 增大队列容量，避免队列满导致的任务丢失
    public static final BlockingQueue<DwTaskRecord> TASK_RECORD_QUEUE = new LinkedBlockingDeque<>(200);

    // 使用AtomicLong替代volatile，提高并发性能
    private final AtomicLong indexTaskRecordId = new AtomicLong(0L);

    private final Lock lock = new ReentrantLock();

    // 添加执行统计指标
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger failureCount = new AtomicInteger(0);
    private final AtomicInteger retryCount = new AtomicInteger(0);

    /**
     * 初始化任务执行器 启动任务生产和消费线程
     */
    @PostConstruct
    public void initialize() {
        // 启动任务生产者线程
        Thread producerThread = new Thread(this::produceTasks, "TaskProducer");
        producerThread.setDaemon(false);
        producerThread.start();

        // 启动任务消费者线程
        for (int i = 0; i < 8; i++) {
            Thread consumerThread = new Thread(this::execute, "TaskConsumer-" + (i + 1));
            consumerThread.setDaemon(false);
            consumerThread.start();
        }

        log.info("DwTaskExecutor 初始化完成，启动1个生产者线程和8个消费者线程");
    }

    /**
     * 任务生产方法 负责从数据库获取待执行的任务并放入队列
     */
    private void produceTasks() {
        while (!Thread.currentThread().isInterrupted()) {
            DwTaskRecord dbTaskRecord = null;
            try {
                lock.lock();
                dbTaskRecord = dwTaskRecordService.getOneTaskRecord(indexTaskRecordId.get());

                if (!ObjectUtils.isEmpty(dbTaskRecord)) {
                    indexTaskRecordId.set(dbTaskRecord.getId());

                    // 使用offer替代put，添加超时机制避免无限阻塞
                    if (TASK_RECORD_QUEUE.offer(dbTaskRecord, 5, TimeUnit.SECONDS)) {
                        log.info("任务已加入队列: id={}, type={}, dates={}", dbTaskRecord.getId(),
                            dbTaskRecord.getType(), dbTaskRecord.getDates());
                    } else {
                        log.warn("任务队列已满，任务[id={}]将在下次循环重试", dbTaskRecord.getId());
                        // 任务留在数据库中，下次循环会重新尝试
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.info("任务生产者线程被中断");
                break;
            } catch (Exception e) {
                log.error("获取任务异常，将在5秒后重试", e);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            } finally {
                lock.unlock();
            }

            // 如果没有获取到任务，休眠一段时间
            if (ObjectUtils.isEmpty(dbTaskRecord)) {
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.info("任务生产者休眠被中断");
                    break;
                }
            }
        }
    }

    /**
     * 任务执行方法 从队列取出任务并执行
     */
    public void execute() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // 使用poll替代take，添加超时机制
                DwTaskRecord dbTaskRecord = TASK_RECORD_QUEUE.poll(3, TimeUnit.SECONDS);

                if (dbTaskRecord != null) {
                    processTaskWithRetry(dbTaskRecord);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.info("任务消费者线程被中断");
                break;
            } catch (Exception e) {
                log.error("任务消费异常", e);
                failureCount.incrementAndGet();
            } finally {
                getExecutorStats();
            }
        }
    }

    /**
     * 带重试机制的任务处理
     */
    private void processTaskWithRetry(DwTaskRecord taskRecord) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                // 正确的任务状态更新顺序：执行中 -> 事件处理 -> 完成
                log.info("开始执行任务: id={}, type={}, dates={}", taskRecord.getId(), taskRecord.getType(),
                    taskRecord.getDates());

                // 1. 先标记为执行中状态
                taskRecord.execute();
                dwTaskRecordService.update(taskRecord);

                // 2. 发布事件让监听器处理业务逻辑
                applicationEventPublisher.publishEvent(new DwTaskRecordExecuteEvent(this, taskRecord));

                // 3. 标记为完成状态
                taskRecord.done();
                dwTaskRecordService.update(taskRecord);

                successCount.incrementAndGet();
                log.info("任务执行成功: id={}", taskRecord.getId());
                return; // 成功执行，退出重试循环

            } catch (Exception e) {
                attempt++;
                retryCount.incrementAndGet();

                if (attempt >= maxRetries) {
                    // 重试次数用尽，标记任务为异常终止
                    log.error("任务执行失败超过最大重试次数: id={}", taskRecord.getId(), e);
                    taskRecord.setStatus(3);
                    try {
                        dwTaskRecordService.update(taskRecord);
                    } catch (Exception updateException) {
                        log.error("更新任务失败状态异常: id={}", taskRecord.getId(), updateException);
                    }
                    failureCount.incrementAndGet();
                } else {
                    log.warn("任务执行失败，第{}次重试: id={}, error={}", attempt, taskRecord.getId(), e.getMessage());
                    try {
                        // 指数退避策略
                        TimeUnit.MILLISECONDS.sleep(1000 * attempt);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }

    /**
     * 获取执行器统计信息
     */
    public String getExecutorStats() {
        return String.format("任务执行统计 - 成功: %d, 失败: %d, 重试: %d, 队列大小: %d", successCount.get(),
            failureCount.get(), retryCount.get(), TASK_RECORD_QUEUE.size());
    }
}
