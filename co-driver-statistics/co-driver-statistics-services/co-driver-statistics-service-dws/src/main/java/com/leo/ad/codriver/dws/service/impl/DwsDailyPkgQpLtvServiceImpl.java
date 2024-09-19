package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgQpLtvMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgQpLtv;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgQpLtvServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 18:46
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgQpLtvServiceImpl implements DwsService {
    private final DwsDailyPkgQpLtvMapper dwsDailyPkgQpLtvMapper;
    private final DwBatchMapper<DwsDailyPkgQpLtv, DwsDailyPkgQpLtvMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgQpLtv")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgQpLtvMapper.deleteByDates(dates);
        List<DwsDailyPkgQpLtv> dwsDailyPackageAllQpLtvList = dwsDailyPkgQpLtvMapper.selectByDates(dates);
        if (CollectionUtils.isEmpty(dwsDailyPackageAllQpLtvList)) {
            return;
        }
        dwsDailyPackageAllQpLtvList.forEach(DwsDailyPkgQpLtv::init);
        dwBatchMapper.batchInsert(dwsDailyPackageAllQpLtvList, DwsDailyPkgQpLtvMapper.class);
    }
}
