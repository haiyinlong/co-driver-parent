package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabLoginMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabLogin;
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
public class DwsDailyPackageAllLabLoginServiceImpl implements DwsService {

    private final DwsDailyPackageAllLabLoginMapper dwsDailyPackageAllLabLoginMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabLogin, DwsDailyPackageAllLabLoginMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabLogin")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabLoginMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabLogin> labLoginList =
            dwsDailyPackageAllLabLoginMapper.statisticsPackageAllLabLogin(dates);
        dwBatchMapper.batchInsert(labLoginList, DwsDailyPackageAllLabLoginMapper.class);
    }
}
