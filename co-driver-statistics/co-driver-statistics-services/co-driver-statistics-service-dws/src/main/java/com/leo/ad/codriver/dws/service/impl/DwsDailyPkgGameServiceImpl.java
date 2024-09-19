package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgGameMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgGame;
import com.leo.ad.codriver.dws.event.DwsDailyPkgGameUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgAdServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 18:28
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgGameServiceImpl implements DwsService {
    private final DwsDailyPkgGameMapper dwsDailyPkgGameMapper;
    private final DwBatchMapper<DwsDailyPkgGame, DwsDailyPkgGameMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgGame")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // TODO 改为数据库获取明细，代码中进行汇总，控制新增，修改和删除；
        dwsDailyPkgGameMapper.deleteByDates(dates);
        List<DwsDailyPkgGame> queryStatisticsAll = dwsDailyPkgGameMapper.queryStatisticsAll(dates);
        if (CollectionUtils.isEmpty(queryStatisticsAll)) {
            return;
        }
        dwBatchMapper.batchInsert(queryStatisticsAll, DwsDailyPkgGameMapper.class);

        List<DwsDailyPkgGame> queryStatisticsNew = dwsDailyPkgGameMapper.queryStatisticsNew(dates);
        if (CollectionUtils.isEmpty(queryStatisticsNew)) {
            return;
        }
        dwBatchMapper.batchInsert(queryStatisticsNew, DwsDailyPkgGameMapper.class);

        applicationEventPublisher.publishEvent(new DwsDailyPkgGameUpdateDwEvent(this, dates));
    }

}
