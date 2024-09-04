package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllOnlineMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllOnline;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageOnlineServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/29 15:06
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllOnlineServiceImpl implements DwsService {
    private final DwsDailyPackageAllOnlineMapper dwsDailyPackageAllOnlineMapper;
    private final DwBatchMapper<DwsDailyPackageAllOnline, DwsDailyPackageAllOnlineMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllOnline")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllOnlineMapper.deleteByDates(dates);
        List<DwsDailyPackageAllOnline> dwsDailyPackageOnlineList =
            dwsDailyPackageAllOnlineMapper.queryStatistics(dates);
        dwsDailyPackageOnlineList.forEach(DwsDailyPackageAllOnline::init);
        dwBatchMapper.batchInsert(dwsDailyPackageOnlineList, DwsDailyPackageAllOnlineMapper.class);
    }
}
