package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgMiniGameMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgMiniGame;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgMiniGameServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/11 16:39
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgMiniGameServiceImpl implements DwsService {
    private final DwsDailyPkgMiniGameMapper dwsDailyPkgMiniGameMapper;
    private final DwBatchMapper<DwsDailyPkgMiniGame, DwsDailyPkgMiniGameMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgMiniGame")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // TODO 改为数据库获取明细，代码中进行汇总，控制新增，修改和删除；
        List<DwsDailyPkgMiniGame> activeList = dwsDailyPkgMiniGameMapper.queryActiveList(dates);
        activeList.forEach(DwsDailyPkgMiniGame::calculate);
        dwBatchMapper.batchInsert(activeList, DwsDailyPkgMiniGameMapper.class);

        List<DwsDailyPkgMiniGame> newList = dwsDailyPkgMiniGameMapper.queryNewList(dates);
        newList.forEach(DwsDailyPkgMiniGame::calculate);
        dwBatchMapper.batchInsert(newList, DwsDailyPkgMiniGameMapper.class);

        List<DwsDailyPkgMiniGame> dwsDailyPackageAllGameSingleList = dwsDailyPkgMiniGameMapper.queryList(dates);
        List<Long> delIds = getDelIds(dwsDailyPackageAllGameSingleList, activeList, newList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgMiniGameMapper.deleteBatchIds(delIds);
        }
    }

}
