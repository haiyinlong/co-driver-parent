package com.leo.ad.codriver.dws.service.impl.ver;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabConversionMapper;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsDailyPackageAllLabConversionServiceImpl implements DwsService {

    private final DwsDailyPackageAllLabConversionMapper dwsDailyPackageAllLabConversionMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabConversionMapper.deleteByDates(dates);
        dwsDailyPackageAllLabConversionMapper.syncActiveList(dates);
        dwsDailyPackageAllLabConversionMapper.syncNewList(dates);
    }
}
