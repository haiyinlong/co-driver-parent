package com.leo.ad.codriver.dws.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLoginMapper;
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
public class DwsDailyPackageAllLoginServiceImpl implements DwsService {

    private final DwsDailyPackageAllLoginMapper dwsDailyPackageAllLoginMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLogin")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLoginMapper.deleteByDates(dates);
        dwsDailyPackageAllLoginMapper.syncDailyPackageAllLogin(dates);
    }
}
