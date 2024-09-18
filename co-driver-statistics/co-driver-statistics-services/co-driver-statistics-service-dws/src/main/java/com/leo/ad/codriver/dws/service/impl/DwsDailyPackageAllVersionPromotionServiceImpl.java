package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.event.DwsDailyPackageAllVersionPromotionUpdateDwEvent;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllVersionPromotionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllVersionPromotion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllVersionPromotionServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 16:36
 **/
@Order(Integer.MAX_VALUE)
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllVersionPromotionServiceImpl implements DwsService {
    private final DwsDailyPackageAllVersionPromotionMapper dwsDailyPackageAllVersionPromotionMapper;
    private final DwBatchMapper<DwsDailyPackageAllVersionPromotion,
        DwsDailyPackageAllVersionPromotionMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllVersionPromotion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllVersionPromotionMapper.deleteByDates(dates);
        List<DwsDailyPackageAllVersionPromotion> list = dwsDailyPackageAllVersionPromotionMapper.selectByDate(dates);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(DwsDailyPackageAllVersionPromotion::initDate);
        dwBatchMapper.batchInsert(list, DwsDailyPackageAllVersionPromotionMapper.class);
        applicationEventPublisher.publishEvent(new DwsDailyPackageAllVersionPromotionUpdateDwEvent(this, dates));
    }
}
