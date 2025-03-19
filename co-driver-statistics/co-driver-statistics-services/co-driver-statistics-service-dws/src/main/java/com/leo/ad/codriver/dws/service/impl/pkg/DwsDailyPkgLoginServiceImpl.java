package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgLoginMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgLogin;
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
@Order(Integer.MIN_VALUE)
public class DwsDailyPkgLoginServiceImpl implements DwsService {

    private final DwsDailyPkgLoginMapper dwsDailyPkgLoginMapper;
    private final DwBatchMapper<DwsDailyPkgLogin, DwsDailyPkgLoginMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgLogin")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgLoginMapper.deleteByDates(dates);
        List<DwsDailyPkgLogin> pkgLoginList = dwsDailyPkgLoginMapper.statisticsPkgLogin(dates);
        dwBatchMapper.batchInsert(pkgLoginList, DwsDailyPkgLoginMapper.class);
    }
}
