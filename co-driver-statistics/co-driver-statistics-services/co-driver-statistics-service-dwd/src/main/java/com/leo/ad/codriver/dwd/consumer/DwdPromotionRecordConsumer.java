package com.leo.ad.codriver.dwd.consumer;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dwd.consumer.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.service.impl.DwdPromotionRecordServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

/**
 * DataChangeConsumer
 *
 * @author HaiYinLong
 * @version 2024/06/04 15:27
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DwdPromotionRecordConsumer {
    private final DwdPromotionRecordServiceImpl dwdPromotionRecordServiceImpl;
    private static final String PROMOTE = "promote";

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final Map<Integer, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();
    private static final int DELAY_TIME = 5;

    @RabbitListener(queues = {"data_change_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.data_change_queue.enable:true}")
    public void notifyDataChange(String dataChangeMsg) {
        DataChangeDTO dataChangeDTO = JSONObject.parseObject(dataChangeMsg, DataChangeDTO.class);
        if (PROMOTE.equalsIgnoreCase(dataChangeDTO.getChangeType())) {
            Integer dates = dataChangeDTO.getDates();
            log.info("mq接收到 {} 推广花费数据", dates);

            // 取消之前针对此日期的定时任务（如果有）
            ScheduledFuture<?> previousTask = scheduledTasks.get(dates);
            if (previousTask != null && !previousTask.isDone()) {
                previousTask.cancel(false);
            }

            try {
                // 为这个特定日期安排倒计时任务，如果1分钟内没有新数据则才执行
                ScheduledFuture<?> newTask = executorService.schedule(() -> {
                    log.info("{} 时间内未接收到新数据，开始执行 {} 推广花费数据同步", DELAY_TIME, dates);
                    try {
                        dwdPromotionRecordServiceImpl.syncData(dates);
                    } catch (Exception e) {
                        log.error("scheduled sync dwdPromotionRecordServiceImpl data error for date: {}", dates, e);
                    } finally {
                        scheduledTasks.remove(dates);
                    }
                }, DELAY_TIME, TimeUnit.MINUTES);

                // 将新任务存储到map中
                scheduledTasks.put(dates, newTask);

            } catch (Exception e) {
                log.error("sync dwdPromotionRecordServiceImpl data error for date: {}", dates, e);
            }
        }
    }
}
