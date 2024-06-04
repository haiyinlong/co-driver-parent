package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageLoginMapper;
import com.leo.ad.codriver.dws.service.DwsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageLogin")
    @Transactional(rollbackFor = Exception.class)
    public void syncData(Integer dates) {
        dwsDailyPackageLoginMapper.delete(dates);
        dwsDailyPackageLoginMapper.syncDailyPackageLogin(dates);
    }
}
