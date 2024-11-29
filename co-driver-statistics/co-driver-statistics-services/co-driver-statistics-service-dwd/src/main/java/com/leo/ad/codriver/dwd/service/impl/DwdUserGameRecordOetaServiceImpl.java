package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.event.DwRestartTaskEvent;
import com.leo.ad.codriver.common.service.DwTaskRecordService;
import com.leo.ad.codriver.dwd.dao.DwdUserGameRecordOetaMapper;
import com.leo.ad.codriver.dwd.entity.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserGameRecordOeta;
import com.leo.ad.codriver.dwd.event.DwdUserGameRecordOetaUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserGameRecordOetaServiceImpl记录
 *
 * @author HaiYinLong
 * @version 2024/07/02 15:15
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class DwdUserGameRecordOetaServiceImpl implements DwdService, ApplicationListener<DwRestartTaskEvent> {
    private final DwdUserGameRecordOetaMapper dwdUserGameRecordOetaMapper;
    private final DwBatchMapper<DwdUserGameRecordOeta, DwdUserGameRecordOetaMapper> batchMapper;
    private final DwTaskRecordService dwTaskRecordService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private volatile boolean isRestart = false;

    @Override
    @ShowExecuteTime(name = "dwdUserGameRecordOeta syncData")
    @AutoPushEventWithTrue(events = {DwdUserGameRecordOetaUpdateDwEvent.class})
    public boolean syncData(Integer dates) {
        restartTerminatedTask(dates);
        DwTaskRecord taskRecord = dwTaskRecordService.getOetaGameRecordLastTaskRecord(dates);
        // 如果taskRecord 是空就从数据库获取最新的区间
        DwCountDTO statisticsCount = getStatisticsCount(dates, taskRecord);
        if (ObjectUtils.isEmpty(statisticsCount) || ObjectUtils.isEmpty(statisticsCount.getMinId())) {
            return false;
        }
        // 插入或更新执行记录
        taskRecord = DwTaskRecord.ofOetaGameRecord(dates, statisticsCount.getMinId(), statisticsCount.getMaxId());
        taskRecord = dwTaskRecordService.add(taskRecord);
        // 同步数据
        handelOdsGameRecordSyncToDwd(dates, taskRecord);
        return true;
    }

    private DwCountDTO getStatisticsCount(Integer dates, DwTaskRecord taskRecord) {
        DwCountDTO statisticsCount = null;
        if (ObjectUtils.isEmpty(taskRecord)) {
            statisticsCount = dwdUserGameRecordOetaMapper.getStatisticsCount(dates, null);
        } else {
            statisticsCount = dwdUserGameRecordOetaMapper.getStatisticsCount(dates, taskRecord.getEndId());
        }
        return statisticsCount;
    }

    private void restartTerminatedTask(Integer dates) {
        if (!isRestart) {
            this.isRestart = true;
            List<DwTaskRecord> taskTerminateRecordList =
                dwTaskRecordService.queryOetaGameRecordTaskRecordOfProcess(dates);
            if (!CollectionUtils.isEmpty(taskTerminateRecordList)) {
                // 异步开启这个任务 taskTerminateRecord
                taskTerminateRecordList.forEach(taskTerminateRecord -> applicationEventPublisher
                    .publishEvent(new DwRestartTaskEvent(this, dates, taskTerminateRecord)));
            }
        }
    }

    private void handelOdsGameRecordSyncToDwd(Integer dates, DwTaskRecord taskRecord) {
        try {
            long startId = 0L;
            Long queryEndId = taskRecord.getStartId();
            Long endId = taskRecord.getEndId();
            List<DwdUserGameRecordOeta> dwdUserGameRecordOetas;
            List<DwdUserGameRecordOeta> newList;
            do {
                startId = queryEndId;
                queryEndId = startId + BatchConst.BATCH_MAX_NUMBER;
                if (queryEndId > endId) {
                    queryEndId = endId;
                }
                dwdUserGameRecordOetas = dwdUserGameRecordOetaMapper.queryStatisticsByDate(dates, startId, queryEndId);

                if (!CollectionUtils.isEmpty(dwdUserGameRecordOetas)) {
                    // 过滤掉已经有id的数据
                    newList = dwdUserGameRecordOetas.stream()
                        .filter(userAdRecordItem -> ObjectUtils.isEmpty(userAdRecordItem.getId()))
                        .peek(DwdUserGameRecordOeta::init).toList();
                    if (!CollectionUtils.isEmpty(newList)) {
                        batchMapper.batchInsert(newList, DwdUserGameRecordOetaMapper.class);
                    }
                    taskRecord.process(queryEndId);
                    dwTaskRecordService.update(taskRecord);
                }
            } while (queryEndId < endId);
        } catch (Exception e) {
            log.error("dwdUserGameRecordOeta  syncData error", e);
            throw e;
        }
    }

    @Override
    @Async
    public void onApplicationEvent(DwRestartTaskEvent event) {
        DwTaskRecord taskRecord = event.getTask();
        log.info("dwdUserGameRecordOeta 重新处理终端任务,id:{}", taskRecord.getId());
        taskRecord.process(taskRecord.getProcessId());
        dwTaskRecordService.update(taskRecord);
        handelOdsGameRecordSyncToDwd(event.getDates(), taskRecord);
    }

}
