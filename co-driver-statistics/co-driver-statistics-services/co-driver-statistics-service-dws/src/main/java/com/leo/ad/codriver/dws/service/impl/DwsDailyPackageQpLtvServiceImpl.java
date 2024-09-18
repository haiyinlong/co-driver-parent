package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageQpLtvMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageQpLtv;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageQpLtvServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 18:46
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageQpLtvServiceImpl implements DwsService {
    private final DwsDailyPackageQpLtvMapper dwsDailyPackageQpLtvMapper;
    private final DwBatchMapper<DwsDailyPackageQpLtv, DwsDailyPackageQpLtvMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageQpLtv")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageQpLtvMapper.deleteByDates(dates);
        List<DwsDailyPackageQpLtv> dwsDailyPackageQpLtvList = dwsDailyPackageQpLtvMapper.selectByDates(dates);
        if (CollectionUtils.isEmpty(dwsDailyPackageQpLtvList)) {
            return;
        }
        dwsDailyPackageQpLtvList.forEach(DwsDailyPackageQpLtv::init);
        dwBatchMapper.batchInsert(dwsDailyPackageQpLtvList, DwsDailyPackageQpLtvMapper.class);
    }
}
