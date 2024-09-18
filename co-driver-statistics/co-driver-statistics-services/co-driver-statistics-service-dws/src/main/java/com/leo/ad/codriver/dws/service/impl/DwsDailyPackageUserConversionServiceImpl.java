package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageUserConversionMapper;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsDailyPackageUserConversionServiceImpl implements DwsService {

    private final DwsDailyPackageUserConversionMapper dwsDailyPackageUserConversionMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageUserConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageUserConversionMapper.deleteByDates(dates);
        dwsDailyPackageUserConversionMapper.syncActiveList(dates);
        dwsDailyPackageUserConversionMapper.syncNewList(dates);
    }
}
