package com.leo.ad.codriver.dws.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgRetentionMapper;
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
public class DwsDailyPkgRetentionServiceImpl implements DwsService {

    private final DwsDailyPkgRetentionMapper dwsDailyPkgRetentionMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPackageAllRetention syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgRetentionMapper.deleteByDates(dates);
        dwsDailyPkgRetentionMapper.syncData(dates);
    }
}
