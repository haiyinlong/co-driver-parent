package com.leo.ad.codriver.common.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwTaskRecordService;
import com.leo.ad.codriver.common.task.event.DwTaskRecordExecuteEvent;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwExecutor
 *
 * @author HaiYinLong
 * @version 2024/11/29 16:19
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwTaskExecutor implements TaskExecutor {
    private final DwTaskRecordService dwTaskRecordService;
    private final ApplicationEventPublisher applicationEventPublisher;
    public static final BlockingQueue<DwTaskRecord> TASK_RECORD_QUEUE = new LinkedBlockingDeque<>(20);
    private static volatile long indexTaskRecordId = 0L;

    private final Lock lock = new ReentrantLock();

    /**
     * 通过对象或线程池获取要处理的数据，然后进行执行，有空线程时就获取数据；
     */
    @PostConstruct
    public void initialize() {
        new Thread(() -> {
            while (true) {
                DwTaskRecord dbTaskRecord = null;
                lock.lock();
                try {
                    dbTaskRecord = dwTaskRecordService.getOneTaskRecord(indexTaskRecordId);
                    if (!ObjectUtils.isEmpty(dbTaskRecord)) {
                        indexTaskRecordId = dbTaskRecord.getId();
                    }
                } catch (Exception e) {
                    log.error("获取任务异常", e);
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }

                if (ObjectUtils.isEmpty(dbTaskRecord)) {
                    try {
                        TimeUnit.SECONDS.sleep(30);
                        continue;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    TASK_RECORD_QUEUE.put(dbTaskRecord);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("添加队列时异常", e);
                    throw new RuntimeException(e);
                }
            }
        }).start();

        for (int i = 0; i < 8; i++) {
            new Thread(this::execute).start();
        }
    }

    public void execute() {
        while (true) {
            try {
                DwTaskRecord dbTaskRecord = TASK_RECORD_QUEUE.take();
                // 事件发布任务 dbTaskRecord
                applicationEventPublisher.publishEvent(new DwTaskRecordExecuteEvent(this, dbTaskRecord));
                // 更新任务状态
                dbTaskRecord.done();
                dwTaskRecordService.update(dbTaskRecord);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("获取队列数据异常", e);
                break;
            }
        }
    }
}
