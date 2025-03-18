package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.DwTaskTypeConstant;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.event.DwRestartTaskEvent;
import com.leo.ad.codriver.common.service.DwTaskRecordService;
import com.leo.ad.codriver.dwd.dao.DwdUserLoginRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserLoginRecord;
import com.leo.ad.codriver.dwd.event.DwdUserLoginRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 每天凌晨根据登录日志进行统计<br>
 *
 * @author HaiYinLong
 * @version 2024/04/30 15:15
 **/
@Service
@RequiredArgsConstructor
@Slf4j
@Order(Integer.MIN_VALUE)
public class DwdUserLoginRecordServiceImpl implements DwdService, ApplicationListener<DwRestartTaskEvent> {
    private final DwTaskRecordService dwTaskRecordService;
    private final DwdUserLoginRecordMapper dwdUserLoginRecordMapper;
    private final DwBatchMapper<DwdUserLoginRecord, DwdUserLoginRecordMapper> dwBatchMapper;
    private volatile boolean isRestart = false;
    private final RedissonClient redissonClient;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "dwdUserLoginRecord  syncData")
    @AutoPushEventWithTrue(events = {DwdUserLoginRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        restartTerminatedTask(dates);
        // 修改每次创建一个同步任务，避免多次执行
        DwCountDTO statisticsCount = getStatisticsCount(dates);
        if (ObjectUtils.isEmpty(statisticsCount) || ObjectUtils.isEmpty(statisticsCount.getMinId())) {
            return false;
        }
        // 插入执行记录
        DwTaskRecord newTaskRecord =
            DwTaskRecord.ofUserLoginRecord(dates, statisticsCount.getMinId(), statisticsCount.getMaxId());
        dwTaskRecordService.save(newTaskRecord);
        // 同步数据
        handelOdsUserLoginRecordSyncToDwd(newTaskRecord);
        return true;
    }

    private void handelOdsUserLoginRecordSyncToDwd(DwTaskRecord taskRecord) {
        Integer delRowNum = dwdUserLoginRecordMapper.deleteByDates(taskRecord.getDates());
        try {
            long startSourceId = taskRecord.getStartId();
            long endSourceId = startSourceId + BatchConst.BATCH_MAX_NUMBER;
            List<DwdUserLoginRecord> userLoginRecordList;
            do {
                userLoginRecordList =
                    dwdUserLoginRecordMapper.queryStatisticsByDate(taskRecord.getDates(), startSourceId, endSourceId);
                startSourceId = endSourceId + 1;
                endSourceId += BatchConst.BATCH_MAX_NUMBER;
                if (endSourceId > taskRecord.getEndId()) {
                    endSourceId = taskRecord.getEndId();
                }
                if (CollectionUtils.isEmpty(userLoginRecordList)) {
                    continue;
                }
                userLoginRecordList = userLoginRecordList.stream()
                    .filter(userLoginRecord -> ObjectUtils.isEmpty(userLoginRecord.getId())).toList();
                if (!CollectionUtils.isEmpty(userLoginRecordList)) {
                    dwBatchMapper.batchInsert(userLoginRecordList, DwdUserLoginRecordMapper.class);
                }
                taskRecord.process(endSourceId);
                dwTaskRecordService.update(taskRecord);
            } while (startSourceId < taskRecord.getEndId());
        } catch (Exception e) {
            log.error("dwdUserLoginRecord  syncData error", e);
            throw e;
        }
    }

    private DwCountDTO getStatisticsCount(Integer dates) {
        DwTaskRecord lastTaskRecord = dwTaskRecordService.getLastTaskRecord(dates, DwTaskTypeConstant.USER_LOGIN_OETA);
        if (ObjectUtils.isEmpty(lastTaskRecord)) {
            return dwdUserLoginRecordMapper.getStatisticsCount(dates, null);
        }
        return dwdUserLoginRecordMapper.getStatisticsCount(dates, lastTaskRecord.getEndId());
    }

    private void restartTerminatedTask(Integer dates) {
        if (!isRestart) {
            this.isRestart = true;
            List<DwTaskRecord> taskTerminateRecordList =
                dwTaskRecordService.queryProcessTaskRecord(dates, DwTaskTypeConstant.USER_LOGIN_OETA);
            if (!CollectionUtils.isEmpty(taskTerminateRecordList)) {
                // 异步开启这个任务 taskTerminateRecord
                taskTerminateRecordList.forEach(taskTerminateRecord -> applicationEventPublisher
                    .publishEvent(new DwRestartTaskEvent(this, dates, taskTerminateRecord)));
            }
        }
    }

    @Override
    @Async
    public void onApplicationEvent(DwRestartTaskEvent event) {
        DwTaskRecord taskRecord = event.getTask();
        if (!taskRecord.validIsUserLoginRecord()) {
            return;
        }
        RLock rLock = redissonClient.getLock("coDriver:lock:userLoginRecord" + taskRecord.getId());
        if (!rLock.tryLock()) {
            return;
        }
        try {
            log.info("dwdUserLoginRecordOeta 重新处理终端任务,id:{}", taskRecord.getId());
            taskRecord.process(taskRecord.getProcessId());
            dwTaskRecordService.update(taskRecord);
            handelOdsUserLoginRecordSyncToDwd(taskRecord);
        } catch (Exception e) {
            log.error("dwdUserLoginRecordOeta 重新处理终端任务,id:" + taskRecord.getId(), e);
            throw new RuntimeException(e);
        } finally {
            if (rLock.isLocked()) {
                rLock.unlock();
            }
        }
    }
}
