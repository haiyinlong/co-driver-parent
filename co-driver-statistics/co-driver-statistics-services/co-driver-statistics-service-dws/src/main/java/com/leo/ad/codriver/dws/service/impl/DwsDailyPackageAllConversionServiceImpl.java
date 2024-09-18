package com.leo.ad.codriver.dws.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllConversionMapper;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsDailyPackageAllConversionServiceImpl implements DwsService {

    private final DwsDailyPackageAllConversionMapper dwsDailyPackageAllConversionMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllConversionMapper.deleteByDates(dates);
        dwsDailyPackageAllConversionMapper.syncActiveList(dates);
        dwsDailyPackageAllConversionMapper.syncNewList(dates);
    }
}
