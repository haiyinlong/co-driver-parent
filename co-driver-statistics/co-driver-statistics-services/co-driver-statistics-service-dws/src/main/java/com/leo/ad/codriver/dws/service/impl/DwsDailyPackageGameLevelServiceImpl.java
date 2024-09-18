package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageGameLevelMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageGameLevel;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * 每日活跃用户游戏关卡数据统计
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsDailyPackageGameLevelServiceImpl implements DwsService {
    private final DwsDailyPackageGameLevelMapper dwsDailyPackageGameLevelMapper;
    private final DwBatchMapper<DwsDailyPackageGameLevel, DwsDailyPackageGameLevelMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageGameLevel")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageGameLevelMapper.deleteByDates(dates);
        // 统计活跃用户
        List<DwsDailyPackageGameLevel> dailyPackageGameLevels = dwsDailyPackageGameLevelMapper.statisticsActive(dates);
        batchMapper.batchInsert(dailyPackageGameLevels, DwsDailyPackageGameLevelMapper.class);
        // 统计新增用户
        List<DwsDailyPackageGameLevel> dailyPackageGameLevelsNew = dwsDailyPackageGameLevelMapper.statisticsNew(dates);
        batchMapper.batchInsert(dailyPackageGameLevelsNew, DwsDailyPackageGameLevelMapper.class);
    }
}
