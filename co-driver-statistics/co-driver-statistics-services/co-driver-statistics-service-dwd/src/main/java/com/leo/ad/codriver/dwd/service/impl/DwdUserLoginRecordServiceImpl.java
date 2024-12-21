package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.async.AsyncThreadExecutor;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserLoginRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserLoginRecord;
import com.leo.ad.codriver.dwd.event.DwdUserLoginRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 每天凌晨根据登录日志进行统计<br>
 *
 * @author HaiYinLong
 * @version 2024/04/30 15:15
 **/
@Service
@AllArgsConstructor
@Slf4j
@Order(Integer.MIN_VALUE)
public class DwdUserLoginRecordServiceImpl implements DwdService {
    private final DwdUserLoginRecordMapper dwdUserLoginRecordMapper;
    private final DwBatchMapper<DwdUserLoginRecord, DwdUserLoginRecordMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserLoginRecord  syncData")
    @AutoPushEventWithTrue(events = {DwdUserLoginRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Integer delRowNum = dwdUserLoginRecordMapper.deleteByDates(dates);
        Long totalRecord = dwdUserLoginRecordMapper.getStatisticsCount(dates);
        if (totalRecord <= 0) {
            return delRowNum > 0;
        }
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        AsyncThreadExecutor asyncThreadExecutor = AsyncThreadExecutor.of((int)totalPageNum);
        for (int i = 0; i < totalPageNum; i++) {
            int index = i;
            asyncThreadExecutor.execute(() -> {
                List<DwdUserLoginRecord> dwdUserLoginRecords = dwdUserLoginRecordMapper.statistics(dates,
                    BatchConst.BATCH_NUMBER, index * BatchConst.BATCH_NUMBER);
                dwBatchMapper.batchInsert(dwdUserLoginRecords, DwdUserLoginRecordMapper.class);
            });
        }
        try {
            asyncThreadExecutor.await();
        } catch (InterruptedException e) {
            log.error("dwdUserLoginRecord  syncData 执行异常", e);
            throw new RuntimeException(e);
        }
        return true;
    }
}
