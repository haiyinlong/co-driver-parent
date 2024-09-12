package com.leo.ad.codriver.dws.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabGameSingleMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabGameSingle;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllLabGameSingleServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/11 16:39
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllLabGameSingleServiceImpl implements DwsService {
    private final DwsDailyPackageAllLabGameSingleMapper dwsDailyPackageAllLabGameSingleMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabGameSingle, DwsDailyPackageAllLabGameSingleMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabGameSingle")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        // TODO 根据游戏逐个获取数据
        // List<DwsDailyPackageAllLabGameSingle> activeList =
        // dwsDailyPackageAllLabGameSingleMapper.queryActiveList(dates);
        // activeList.forEach(DwsDailyPackageAllLabGameSingle::calculate);
        // dwBatchMapper.batchInsert(activeList, DwsDailyPackageAllLabGameSingleMapper.class);
        //
        // List<DwsDailyPackageAllLabGameSingle> newList = dwsDailyPackageAllLabGameSingleMapper.queryNewList(dates);
        // newList.forEach(DwsDailyPackageAllLabGameSingle::calculate);
        // dwBatchMapper.batchInsert(newList, DwsDailyPackageAllLabGameSingleMapper.class);
        //
        // List<DwsDailyPackageAllLabGameSingle> dwsDailyPackageAllGameSingleList =
        // dwsDailyPackageAllLabGameSingleMapper.queryList(dates);
        // List<Long> delIds = getDelIds(dwsDailyPackageAllGameSingleList, activeList, newList);
        // if (!CollectionUtils.isEmpty(delIds)) {
        // dwsDailyPackageAllLabGameSingleMapper.deleteBatchIds(delIds);
        // }
    }

}
