package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.event.dwd.DwdPromotionRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.dao.DwdPromotionRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdPromotionRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdPromotionRecordServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 15:48
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdPromotionRecordServiceImpl implements DwdService {
    private final DwdPromotionRecordMapper dwdPromotionRecordMapper;
    private final ExchangeRate exchangeRate;
    private final DwBatchMapper<DwdPromotionRecord, DwdPromotionRecordMapper> batchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "dwdPromotionRecord syncData")
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwdPromotionRecordMapper.deleteByDate(dates);
        List<DwdPromotionRecord> dwdPromotionRecordList =
            dwdPromotionRecordMapper.queryByDate(dates, exchangeRate.getIndianToDollar());
        if (CollectionUtils.isEmpty(dwdPromotionRecordList)) {
            return;
        }
        dwdPromotionRecordList.forEach(dwdPromotionRecord -> {
            if (ObjectUtils.isEmpty(dwdPromotionRecord.getId())) {
                dwdPromotionRecord.init();
            }
        });
        batchMapper.batchInsert(dwdPromotionRecordList, DwdPromotionRecordMapper.class);
        // 发送事件
        applicationEventPublisher.publishEvent(new DwdPromotionRecordUpdateDwEvent(this, dates));
    }
}
