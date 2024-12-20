package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserConversionMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserConversion;
import com.leo.ad.codriver.dwd.event.DwdUserConversionUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:15
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserConversionServiceImpl implements DwdService {
    private final DwdUserConversionMapper dwdUserConversionMapper;
    private final DwBatchMapper<DwdUserConversion, DwdUserConversionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserConversion syncData")
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserConversionUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Integer delRowNum = dwdUserConversionMapper.deleteByDates(dates);
        // 查询自己的转化记录
        Long totalRecord = dwdUserConversionMapper.countFromOfferRecord(dates);
        if (totalRecord <= 0) {
            return delRowNum > 0;
        }
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        for (int i = 0; i < totalPageNum; i++) {
            List<DwdUserConversion> dwdUserConversionList = dwdUserConversionMapper.statisticsFromOfferRecord(dates,
                BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            dwBatchMapper.batchInsert(dwdUserConversionList, DwdUserConversionMapper.class);
        }

        // 查询河马的转化记录
        totalRecord = dwdUserConversionMapper.countFromHemaOfferRecord(dates);
        long hemaTotalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());

        for (int i = 0; i < hemaTotalPageNum; i++) {
            List<DwdUserConversion> dwdUserConversionList = dwdUserConversionMapper.statisticsFromHemaOfferRecord(dates,
                BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            dwBatchMapper.batchInsert(dwdUserConversionList, DwdUserConversionMapper.class);
        }
        return true;
    }
}
