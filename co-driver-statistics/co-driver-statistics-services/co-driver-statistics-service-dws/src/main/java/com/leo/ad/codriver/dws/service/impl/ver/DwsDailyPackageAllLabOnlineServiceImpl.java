package com.leo.ad.codriver.dws.service.impl.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabOnlineMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabOnline;
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
public class DwsDailyPackageAllLabOnlineServiceImpl implements DwsService {
    private final DwsDailyPackageAllLabOnlineMapper dwsDailyPackageAllLabOnlineMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabOnline, DwsDailyPackageAllLabOnlineMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabOnline")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabOnlineMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabOnline> dwsDailyPackageOnlineList =
            dwsDailyPackageAllLabOnlineMapper.queryStatistics(dates);
        dwsDailyPackageOnlineList.forEach(DwsDailyPackageAllLabOnline::init);
        dwBatchMapper.batchInsert(dwsDailyPackageOnlineList, DwsDailyPackageAllLabOnlineMapper.class);
    }
}
