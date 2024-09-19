package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgAssetExchangeMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgAssetExchange;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgAssetExchangeServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/11 11:33
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgAssetExchangeServiceImpl implements DwsService {
    private final DwsDailyPkgAssetExchangeMapper dwsDailyPkgAssetExchangeMapper;
    private final DwBatchMapper<DwsDailyPkgAssetExchange, DwsDailyPkgAssetExchangeMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgAssetExchange")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 获取库中已经存在的数据
        List<DwsDailyPkgAssetExchange> activeList = dwsDailyPkgAssetExchangeMapper.selectActiveList(dates);
        activeList.forEach(DwsDailyPkgAssetExchange::calculateRateAndInit);
        dwBatchMapper.batchInsert(activeList, DwsDailyPkgAssetExchangeMapper.class);

        List<DwsDailyPkgAssetExchange> newList = dwsDailyPkgAssetExchangeMapper.selectNewList(dates);
        newList.forEach(DwsDailyPkgAssetExchange::calculateRateAndInit);
        dwBatchMapper.batchInsert(newList, DwsDailyPkgAssetExchangeMapper.class);

        List<DwsDailyPkgAssetExchange> dbList = dwsDailyPkgAssetExchangeMapper.queryList(dates);
        List<Long> delIds = getDelIds(dbList, activeList, newList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgAssetExchangeMapper.deleteBatchIds(delIds);
        }
    }

}
