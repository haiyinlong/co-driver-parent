package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageVersionPromotionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageVersionPromotion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageVersionPromotionServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 16:36
 **/
@Order(Integer.MAX_VALUE)
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageVersionPromotionServiceImpl implements DwsService {
    private final DwsDailyPackageVersionPromotionMapper dwsDailyPackageVersionPromotionMapper;
    private final DwBatchMapper<DwsDailyPackageVersionPromotion, DwsDailyPackageVersionPromotionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageVersionPromotion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageVersionPromotionMapper.deleteByDates(dates);
        List<DwsDailyPackageVersionPromotion> list = dwsDailyPackageVersionPromotionMapper.selectByDate(dates);
        dwBatchMapper.batchInsert(list, DwsDailyPackageVersionPromotionMapper.class);
    }
}
