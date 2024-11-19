package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgOnlineMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgOnline;
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
public class DwsDailyPkgOnlineServiceImpl implements DwsService {
    private final DwsDailyPkgOnlineMapper dwsDailyPkgOnlineMapper;
    private final DwBatchMapper<DwsDailyPkgOnline, DwsDailyPkgOnlineMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgOnline")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgOnlineMapper.deleteByDates(dates);
        List<DwsDailyPkgOnline> dwsDailyPackageOnlineList = dwsDailyPkgOnlineMapper.queryStatistics(dates);
        dwsDailyPackageOnlineList.forEach(DwsDailyPkgOnline::init);
        dwBatchMapper.batchInsert(dwsDailyPackageOnlineList, DwsDailyPkgOnlineMapper.class);
    }
}
