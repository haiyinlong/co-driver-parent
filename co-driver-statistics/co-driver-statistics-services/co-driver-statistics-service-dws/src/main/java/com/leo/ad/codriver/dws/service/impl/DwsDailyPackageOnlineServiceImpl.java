package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageOnlineMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageOnline;
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
public class DwsDailyPackageOnlineServiceImpl implements DwsService {
    private final DwsDailyPackageOnlineMapper dwsDailyPackageOnlineMapper;
    private final DwBatchMapper<DwsDailyPackageOnline, DwsDailyPackageOnlineMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageOnline")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageOnlineMapper.deleteByDates(dates);
        List<DwsDailyPackageOnline> dwsDailyPackageOnlineList = dwsDailyPackageOnlineMapper.queryStatistics(dates);
        dwsDailyPackageOnlineList.forEach(DwsDailyPackageOnline::init);
        dwBatchMapper.batchInsert(dwsDailyPackageOnlineList, DwsDailyPackageOnlineMapper.class);
    }
}
