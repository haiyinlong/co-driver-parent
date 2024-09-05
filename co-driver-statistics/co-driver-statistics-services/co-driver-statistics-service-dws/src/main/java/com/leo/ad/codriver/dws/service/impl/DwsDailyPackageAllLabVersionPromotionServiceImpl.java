package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllLabVersionPromotionUpdateDwEvent;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabVersionPromotionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabVersionPromotion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllLabVersionPromotionServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 16:36
 **/
@Order(Integer.MAX_VALUE)
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllLabVersionPromotionServiceImpl implements DwsService {
    private final DwsDailyPackageAllLabVersionPromotionMapper dwsDailyPackageAllLabVersionPromotionMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabVersionPromotion,
        DwsDailyPackageAllLabVersionPromotionMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabVersionPromotion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabVersionPromotionMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabVersionPromotion> list =
            dwsDailyPackageAllLabVersionPromotionMapper.selectByDate(dates);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(DwsDailyPackageAllLabVersionPromotion::initDate);
        dwBatchMapper.batchInsert(list, DwsDailyPackageAllLabVersionPromotionMapper.class);
        applicationEventPublisher.publishEvent(new DwsDailyPackageAllLabVersionPromotionUpdateDwEvent(this, dates));
    }
}
