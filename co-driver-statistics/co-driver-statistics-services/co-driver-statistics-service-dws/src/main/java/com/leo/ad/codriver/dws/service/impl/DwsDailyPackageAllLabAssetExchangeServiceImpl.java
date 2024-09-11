package com.leo.ad.codriver.dws.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabAssetExchangeMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabAssetExchange;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllLabAssetExchangeServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/11 11:33
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllLabAssetExchangeServiceImpl implements DwsService {
    private final DwsDailyPackageAllLabAssetExchangeMapper dwsDailyPackageAllAssetExchangeMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabAssetExchange,
        DwsDailyPackageAllLabAssetExchangeMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabAssetExchange")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        // 获取库中已经存在的数据
        List<DwsDailyPackageAllLabAssetExchange> dbList = dwsDailyPackageAllAssetExchangeMapper.queryList(dates);
        List<DwsDailyPackageAllLabAssetExchange> activeList =
            dwsDailyPackageAllAssetExchangeMapper.selectActiveList(dates);
        activeList.forEach(DwsDailyPackageAllLabAssetExchange::calculateRateAndInit);
        dwBatchMapper.batchInsert(activeList, DwsDailyPackageAllLabAssetExchangeMapper.class);

        List<DwsDailyPackageAllLabAssetExchange> newList = dwsDailyPackageAllAssetExchangeMapper.selectNewList(dates);
        newList.forEach(DwsDailyPackageAllLabAssetExchange::calculateRateAndInit);
        dwBatchMapper.batchInsert(newList, DwsDailyPackageAllLabAssetExchangeMapper.class);
        List<Long> delIds = getDelIds(dbList, activeList, newList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPackageAllAssetExchangeMapper.deleteBatchIds(delIds);
        }
    }

    private List<Long> getDelIds(List<DwsDailyPackageAllLabAssetExchange> dbList,
        List<DwsDailyPackageAllLabAssetExchange> activeList, List<DwsDailyPackageAllLabAssetExchange> newList) {
        if (CollectionUtils.isEmpty(dbList)) {
            return Collections.emptyList();
        }
        List<Long> dbIds =
            new java.util.ArrayList<>(dbList.stream().map(DwsDailyPackageAllLabAssetExchange::getId).toList());
        if (!CollectionUtils.isEmpty(activeList)) {
            activeList
                .forEach(dwsDailyPackageAllAssetExchange -> dbIds.remove(dwsDailyPackageAllAssetExchange.getId()));
        }
        if (!CollectionUtils.isEmpty(newList)) {
            newList.forEach(dwsDailyPackageAllAssetExchange -> dbIds.remove(dwsDailyPackageAllAssetExchange.getId()));
        }
        return dbIds;
    }
}
