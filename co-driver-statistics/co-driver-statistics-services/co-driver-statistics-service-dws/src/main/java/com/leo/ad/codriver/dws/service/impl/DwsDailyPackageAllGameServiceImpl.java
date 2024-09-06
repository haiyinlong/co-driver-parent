package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllAdUpdateDwEvent;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllGameUpdateDwEvent;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllAdMapper;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllGameMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllAd;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllGame;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DwsDailyPackageAllAdServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 18:28
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllGameServiceImpl implements DwsService {
    private final DwsDailyPackageAllGameMapper dwsDailyPackageAllGameMapper;
    private final DwBatchMapper<DwsDailyPackageAllGame, DwsDailyPackageAllGameMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllGame")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllGameMapper.deleteByDates(dates);
        List<DwsDailyPackageAllGame> queryStatisticsAll = dwsDailyPackageAllGameMapper.queryStatisticsAll(dates);
        if (CollectionUtils.isEmpty(queryStatisticsAll)) {
            return;
        }
        dwBatchMapper.batchInsert(queryStatisticsAll, DwsDailyPackageAllGameMapper.class);

        List<DwsDailyPackageAllGame> queryStatisticsNew = dwsDailyPackageAllGameMapper.queryStatisticsNew(dates);
        if (CollectionUtils.isEmpty(queryStatisticsNew)) {
            return;
        }
        dwBatchMapper.batchInsert(queryStatisticsNew, DwsDailyPackageAllGameMapper.class);

        applicationEventPublisher.publishEvent(new DwsDailyPackageAllGameUpdateDwEvent(this, dates));
    }


}
