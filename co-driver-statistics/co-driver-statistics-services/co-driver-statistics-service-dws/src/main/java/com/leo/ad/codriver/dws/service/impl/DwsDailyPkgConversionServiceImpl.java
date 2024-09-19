package com.leo.ad.codriver.dws.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgConversionMapper;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsDailyPkgConversionServiceImpl implements DwsService {

    private final DwsDailyPkgConversionMapper dwsDailyPkgConversionMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgConversionMapper.deleteByDates(dates);
        dwsDailyPkgConversionMapper.syncActiveList(dates);
        dwsDailyPkgConversionMapper.syncNewList(dates);
    }
}
