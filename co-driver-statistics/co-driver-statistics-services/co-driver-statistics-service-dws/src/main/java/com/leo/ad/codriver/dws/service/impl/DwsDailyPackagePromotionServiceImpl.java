package com.leo.ad.codriver.dws.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.event.DwsDailyPackagePromotionUpdateEvent;
import com.leo.ad.codriver.dws.dao.DwsDailyPackagePromotionMapper;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * DwsServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsDailyPackagePromotionServiceImpl implements DwsService {

    private final DwsDailyPackagePromotionMapper dwsDailyPackagePromotionMapper;
    private final ExchangeRate exchangeRate;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackagePromotion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackagePromotionMapper.deleteDailyPackagePromotion(dates);
        dwsDailyPackagePromotionMapper.syncDailyPackagePromotion(dates, exchangeRate.getIndianToDollar());
        applicationEventPublisher.publishEvent(new DwsDailyPackagePromotionUpdateEvent(this, dates));
    }
}
