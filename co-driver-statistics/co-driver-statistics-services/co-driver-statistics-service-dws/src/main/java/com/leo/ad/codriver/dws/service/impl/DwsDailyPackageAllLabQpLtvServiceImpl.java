package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabQpLtvMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabQpLtv;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllLabQpLtvServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 18:46
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllLabQpLtvServiceImpl implements DwsService {
    private final DwsDailyPackageAllLabQpLtvMapper dwsDailyPackageAllLabQpLtvMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabQpLtv, DwsDailyPackageAllLabQpLtvMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabQpLtv")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabQpLtvMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabQpLtv> dwsDailyPackageAllLabQpLtvList =
            dwsDailyPackageAllLabQpLtvMapper.selectByDates(dates);
        if (CollectionUtils.isEmpty(dwsDailyPackageAllLabQpLtvList)) {
            return;
        }
        dwsDailyPackageAllLabQpLtvList.forEach(DwsDailyPackageAllLabQpLtv::init);
        dwBatchMapper.batchInsert(dwsDailyPackageAllLabQpLtvList, DwsDailyPackageAllLabQpLtvMapper.class);
    }
}
