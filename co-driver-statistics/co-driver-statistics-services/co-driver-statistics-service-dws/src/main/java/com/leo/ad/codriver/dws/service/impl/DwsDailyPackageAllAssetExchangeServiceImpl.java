package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllAssetExchangeMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllAssetExchange;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllAssetExchangeServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/11 11:33
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllAssetExchangeServiceImpl implements DwsService {
    private final DwsDailyPackageAllAssetExchangeMapper dwsDailyPackageAllAssetExchangeMapper;
    private final DwBatchMapper<DwsDailyPackageAllAssetExchange, DwsDailyPackageAllAssetExchangeMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllAssetExchange")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 获取库中已经存在的数据
        List<DwsDailyPackageAllAssetExchange> activeList =
            dwsDailyPackageAllAssetExchangeMapper.selectActiveList(dates);
        activeList.forEach(DwsDailyPackageAllAssetExchange::calculateRateAndInit);
        dwBatchMapper.batchInsert(activeList, DwsDailyPackageAllAssetExchangeMapper.class);

        List<DwsDailyPackageAllAssetExchange> newList = dwsDailyPackageAllAssetExchangeMapper.selectNewList(dates);
        newList.forEach(DwsDailyPackageAllAssetExchange::calculateRateAndInit);
        dwBatchMapper.batchInsert(newList, DwsDailyPackageAllAssetExchangeMapper.class);

        List<DwsDailyPackageAllAssetExchange> dbList = dwsDailyPackageAllAssetExchangeMapper.queryList(dates);
        List<Long> delIds = getDelIds(dbList, activeList, newList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPackageAllAssetExchangeMapper.deleteBatchIds(delIds);
        }
    }

}
