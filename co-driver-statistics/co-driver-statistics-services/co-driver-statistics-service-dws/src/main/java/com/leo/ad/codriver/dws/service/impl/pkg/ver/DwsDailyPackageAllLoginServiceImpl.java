package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLoginMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLogin;
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
public class DwsDailyPackageAllLoginServiceImpl implements DwsService {

    private final DwsDailyPackageAllLoginMapper dwsDailyPackageAllLoginMapper;
    private final DwBatchMapper<DwsDailyPackageAllLogin, DwsDailyPackageAllLoginMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLogin")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLoginMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLogin> allLoginList = dwsDailyPackageAllLoginMapper.statisticsPackageAllLogin(dates);
        dwBatchMapper.batchInsert(allLoginList, DwsDailyPackageAllLoginMapper.class);
    }
}
