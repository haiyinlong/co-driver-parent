package com.leo.ad.codriver.common.task;

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
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwSyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 动态调整定时任务执行频率，通过数据配置的进行动态初始化定时任务执行频率
 *
 * @author HaiYinLong
 * @version 2024/11/29 11:47
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwTableSyncDistributionService {
    private final Map<Long, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();
    private final DwSyncService dwSyncService;
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private final DwTaskCreator dwTaskCreator;

    // @PostConstruct
    public void distribution() {
        List<DwSync> syncConfig = dwSyncService.queryList();
        if (CollectionUtils.isEmpty(syncConfig)) {
            return;
        }
        for (DwSync dwSync : syncConfig) {
            Runnable task = getCreateTaskRunnable(dwSync);
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

    public void updateTask(Long taskId, long newPeriod) {
        if (scheduledFutures.containsKey(taskId)) {
            cancelTask(taskId);
        }
        DwSync dwSync = dwSyncService.getSyncById(taskId);
        if (ObjectUtils.isEmpty(dwSync)) {
            return;
        }
        Runnable task = getCreateTaskRunnable(dwSync);
        PeriodicTrigger trigger = new PeriodicTrigger(Duration.of(newPeriod, ChronoUnit.SECONDS));
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task, trigger);
        scheduledFutures.put(taskId, future);
    }

    private Runnable getCreateTaskRunnable(DwSync dwSync) {
        return () -> {
            try {
                DwTaskRecord task = dwTaskCreator.createTask(dwSync.getTableName());
                if (ObjectUtils.isEmpty(task)) {
                    log.info(dwSync.getTableName() + " 定时创建执行任务失败，数据为空");
                } else {
                    log.info(dwSync.getTableName() + " 定时创建执行任务完成, id:{}", task.getId());
                }
            } catch (Exception e) {
                log.error(dwSync.getTableName() + " 定时创建执行任务异常", e);
            }
        };
    }
}
