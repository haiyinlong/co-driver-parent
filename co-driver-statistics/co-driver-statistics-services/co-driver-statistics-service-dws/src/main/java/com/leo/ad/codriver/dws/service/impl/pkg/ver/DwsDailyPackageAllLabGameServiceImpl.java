package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabGameMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabGame;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllLabAdServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 18:28
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllLabGameServiceImpl implements DwsService {
    private final DwsDailyPackageAllLabGameMapper dwsDailyPackageAllLabGameMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabGame, DwsDailyPackageAllLabGameMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabGame")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {

        return;

        /* // TODO 改为数据库获取明细，代码中进行汇总，控制新增，修改和删除；
        dwsDailyPackageAllLabGameMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabGame> queryStatisticsAll = dwsDailyPackageAllLabGameMapper.queryStatisticsAll(dates);
        if (CollectionUtils.isEmpty(queryStatisticsAll)) {
            return;
        }
        dwBatchMapper.batchInsert(queryStatisticsAll, DwsDailyPackageAllLabGameMapper.class);

        List<DwsDailyPackageAllLabGame> queryStatisticsNew = dwsDailyPackageAllLabGameMapper.queryStatisticsNew(dates);
        if (CollectionUtils.isEmpty(queryStatisticsNew)) {
            return;
        }
        dwBatchMapper.batchInsert(queryStatisticsNew, DwsDailyPackageAllLabGameMapper.class);

        applicationEventPublisher.publishEvent(new DwsDailyPackageAllLabGameUpdateDwEvent(this, dates));*/
    }
}
