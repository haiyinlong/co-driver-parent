package com.leo.ad.codriver.dws.service.impl.ver;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabRetentionMapper;
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
public class DwsDailyPackageAllLabRetentionServiceImpl implements DwsService {

    private final DwsDailyPackageAllLabRetentionMapper dwsDailyPackageAllLabRetentionMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPackageAllLabRetention syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabRetentionMapper.deleteByDates(dates);
        dwsDailyPackageAllLabRetentionMapper.syncData(dates);
    }
}
