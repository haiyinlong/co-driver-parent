package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.event.dwd.DwdUserGameRecordOetaUpdateEvent;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserGameRecordOetaMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserGameRecordOeta;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserGameRecordOetaServiceImpl记录
 *
 * @author HaiYinLong
 * @version 2024/07/02 15:15
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserGameRecordOetaServiceImpl implements DwdService {
    private final DwdUserGameRecordOetaMapper dwdUserGameRecordOetaMapper;
    private final DwBatchMapper<DwdUserGameRecordOeta, DwdUserGameRecordOetaMapper> batchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "dwdUserGameRecordOeta syncData")
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwdUserGameRecordOetaMapper.deleteByDates(dates);
        Long totalRecord = dwdUserGameRecordOetaMapper.getStatisticsCount(dates);
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        for (int i = 0; i < totalPageNum; i++) {
            List<DwdUserGameRecordOeta> statistics = dwdUserGameRecordOetaMapper.queryStatistics(dates,
                BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            if (!CollectionUtils.isEmpty(statistics)) {
                statistics.forEach(DwdUserGameRecordOeta::initDate);
            }
            batchMapper.batchInsert(statistics, DwdUserGameRecordOetaMapper.class);
        }
        applicationEventPublisher.publishEvent(new DwdUserGameRecordOetaUpdateEvent(this, dates));
    }

}
