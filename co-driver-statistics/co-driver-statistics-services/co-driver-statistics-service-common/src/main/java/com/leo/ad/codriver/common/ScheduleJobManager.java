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
    private final DwdTaskCreator dwdTaskCreator;

    // @PostConstruct
    public void initialize() {
        List<DwSync> syncConfig = dwSyncService.queryList();
        if (CollectionUtils.isEmpty(syncConfig)) {
            return;
        }
        for (DwSync dwSync : syncConfig) {
            // 创建线程对象
            Runnable task = () -> {
                Integer dates = DateUtils.getNowDates();
                dwdTaskCreator.createTask(dates, dwSync.getTableName());
            };
            PeriodicTrigger trigger = new PeriodicTrigger(Duration.of(dwSync.getPeriod(), ChronoUnit.SECONDS));
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task, trigger);
            scheduledFutures.put(dwSync.getId(), future);
        }

    }

}
