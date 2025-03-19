package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageUserConversionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageUserConversion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsDailyPackageUserConversionServiceImpl implements DwsService {

    private final DwsDailyPackageUserConversionMapper dwsDailyPackageUserConversionMapper;
    private final DwBatchMapper<DwsDailyPackageUserConversion, DwsDailyPackageUserConversionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageUserConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageUserConversionMapper.deleteByDates(dates);
        List<DwsDailyPackageUserConversion> userConversionActiveList =
            dwsDailyPackageUserConversionMapper.statisticsActiveList(dates);
        dwBatchMapper.batchInsert(userConversionActiveList, DwsDailyPackageUserConversionMapper.class);
        List<DwsDailyPackageUserConversion> userConversionNewList =
            dwsDailyPackageUserConversionMapper.statisticsNewList(dates);
        dwBatchMapper.batchInsert(userConversionNewList, DwsDailyPackageUserConversionMapper.class);
    }
}
