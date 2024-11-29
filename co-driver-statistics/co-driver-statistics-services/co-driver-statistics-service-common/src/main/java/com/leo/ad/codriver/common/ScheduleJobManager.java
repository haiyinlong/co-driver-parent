package com.leo.ad.codriver.common;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.dao.entity.DwSync;
import com.leo.ad.codriver.common.service.DwSyncService;
import com.leo.ad.codriver.common.util.DateUtils;

import lombok.RequiredArgsConstructor;

/**
 * 动态调整定时任务执行频率，通过数据配置的进行动态初始化定时任务执行频率
 *
 * @author HaiYinLong
 * @version 2024/11/29 11:47
 **/
@Component
@RequiredArgsConstructor
public class ScheduleJobManager {
    private final Map<Long, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();
    private final DwSyncService dwSyncService;
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private final DwManager dwManager;

    // @PostConstruct
    public void initialize() {
        // 获取ods 配置;
        List<DwSync> syncConfig = dwSyncService.queryList();
        // 遍历创建Job；
        if (CollectionUtils.isEmpty(syncConfig)) {
            return;
        }

        for (DwSync dwSync : syncConfig) {
            // 创建线程对象
            Runnable task = () -> {
                Integer dates = DateUtils.getNowDates();
                System.out.println(dates + " " + dwSync.getTableName() + " " + dwSync.getId());
                // TODO 创建任务
                // dwManager.createTask(dates, dwSync.getTableName());
            };
            PeriodicTrigger trigger = new PeriodicTrigger(Duration.of(dwSync.getPeriod(), ChronoUnit.SECONDS));
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task, trigger);
            scheduledFutures.put(dwSync.getId(), future);
        }

    }

    public void cancelTask(Long taskId) {
        ScheduledFuture<?> future = scheduledFutures.get(taskId);
        if (future != null && !future.isCancelled()) {
            future.cancel(false);
            scheduledFutures.remove(taskId);
        }
    }

    public void updateTaskFrequency(Long taskId, long newPeriod) {
        if (scheduledFutures.containsKey(taskId)) {
            cancelTask(taskId);
        }
        DwSync dwSync = dwSyncService.getSyncById(taskId);
        if (ObjectUtils.isEmpty(dwSync)) {
            return;
        }
        Runnable task = () -> {
            Integer dates = DateUtils.getNowDates();
            dwManager.createTask(dates, dwSync.getTableName());
        };
        PeriodicTrigger trigger = new PeriodicTrigger(Duration.of(newPeriod, ChronoUnit.SECONDS));
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task, trigger);
        scheduledFutures.put(taskId, future);
    }
}
