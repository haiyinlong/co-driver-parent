package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.DwTaskTypeConstant;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwTaskRecordService;
import com.leo.ad.codriver.common.task.event.DwTaskRecordExecuteEvent;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserLoginRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserLoginRecord;
import com.leo.ad.codriver.dwd.event.DwdUserLoginRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import com.leo.ad.codriver.starter.redis.util.RedisUtils;

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
public class DwdUserLoginRecordServiceImpl implements DwdService, ApplicationListener<DwTaskRecordExecuteEvent> {
    private final DwTaskRecordService dwTaskRecordService;
    private final DwdUserLoginRecordMapper dwdUserLoginRecordMapper;
    private final DwBatchMapper<DwdUserLoginRecord, DwdUserLoginRecordMapper> dwBatchMapper;
    private final RedisUtils redisUtils;

    @Override
    @ShowExecuteTime(name = "dwdUserLoginRecord  syncData")
    @AutoPushEventWithTrue(events = {DwdUserLoginRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        if (Objects.equals(dates, DateUtils.getNowDates())) {
            return false;
        }
        // 创建task
        DwCountDTO statisticsCount = getStatisticsCount(dates);
        if (ObjectUtils.isEmpty(statisticsCount) || ObjectUtils.isEmpty(statisticsCount.getMinId())) {
            return false;
        }
        // 插入执行记录
        DwTaskRecord newTaskRecord = DwTaskRecord.of(dates, DwTaskTypeConstant.ODS_USER_LOGIN.getType(),
            statisticsCount.getMinId(), statisticsCount.getMaxId());
        dwTaskRecordService.save(newTaskRecord);
        return true;
    }

    @Override
    public void onApplicationEvent(DwTaskRecordExecuteEvent event) {
        DwTaskRecord taskRecord = event.getDbTaskRecord();
        if (!DwTaskTypeConstant.ODS_USER_LOGIN.getType().equalsIgnoreCase(taskRecord.getType())) {
            return;
        }
        try {
            log.info("dwdUserLoginRecordOeta 重新处理终端任务,id:{}", taskRecord.getId());
            handelOdsUserLoginRecordSyncToDwd(taskRecord);
        } catch (Exception e) {
            log.error("dwdUserLoginRecordOeta 重新处理终端任务,id:" + taskRecord.getId(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 只统计当日用户的一条记录，避免重复同步数据<br>
     * 通过set 记录数据如果不存在就插入，存在就不插入
     *
     * @param taskRecord
     */
    private void handelOdsUserLoginRecordSyncToDwd(DwTaskRecord taskRecord) {
        try {
            String key = "coDriver:dwdUserLoginRecord:" + taskRecord.getDates();
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
                userLoginRecordList =
                    userLoginRecordList.stream().filter(userLoginRecord -> ObjectUtils.isEmpty(userLoginRecord.getId()))
                        .filter(userLoginRecord -> !isHasUserLoginRecord(key, userLoginRecord.getUserId())).toList();
                if (!CollectionUtils.isEmpty(userLoginRecordList)) {
                    userLoginRecordList = userLoginRecordList.stream()
                        .collect(Collectors.toMap(DwdUserLoginRecord::getUserId, Function.identity(), (v1, v2) -> v1))
                        .values().stream().toList();
                    this.updateUserLoginRecordSet(key, userLoginRecordList);
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

    private void updateUserLoginRecordSet(String key, List<DwdUserLoginRecord> userLoginRecordList) {
        for (DwdUserLoginRecord dwdUserLoginRecord : userLoginRecordList) {
            redisUtils.getRedisTemplate().opsForValue().setBit(key, dwdUserLoginRecord.getUserId(), true);
        }
        redisUtils.getRedisTemplate().expire(key, 1, TimeUnit.DAYS);
    }

    private boolean isHasUserLoginRecord(String key, Long userId) {
        return Boolean.TRUE.equals(redisUtils.getRedisTemplate().opsForValue().getBit(key, userId));
    }

    private DwCountDTO getStatisticsCount(Integer dates) {
        DwTaskRecord lastTaskRecord = dwTaskRecordService.getLastTaskRecord(dates, DwTaskTypeConstant.USER_LOGIN_OETA);
        if (ObjectUtils.isEmpty(lastTaskRecord)) {
            return dwdUserLoginRecordMapper.getStatisticsCount(dates, null);
        }
        return dwdUserLoginRecordMapper.getStatisticsCount(dates, lastTaskRecord.getEndId());
    }
}
