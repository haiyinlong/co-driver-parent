package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageLoginMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageLogin;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
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
public class DwsDailyPackageLoginServiceImpl implements DwsService {

    private final DwsDailyPackageLoginMapper dwsDailyPackageLoginMapper;
    private final DwBatchMapper<DwsDailyPackageLogin, DwsDailyPackageLoginMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageLogin")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageLoginMapper.deleteByDates(dates);
        List<DwsDailyPackageLogin> packageLoginList = dwsDailyPackageLoginMapper.statisticsPackageLogin(dates);
        dwBatchMapper.batchInsert(packageLoginList, DwsDailyPackageLoginMapper.class);
    }
}
