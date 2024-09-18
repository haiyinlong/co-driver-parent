package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllAdEventMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllAdEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPackageAllAdEventUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllAdEventServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/18 16:24
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllAdEventServiceImpl implements DwsService {
    private final DwsDailyPackageAllAdEventMapper dwsDailyPackageAllAdEventMapper;
    private final DwBatchMapper<DwsDailyPackageAllAdEvent, DwsDailyPackageAllAdEventMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPackageAllAdEvent")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPackageAllAdEvent> dbList = dwsDailyPackageAllAdEventMapper.queryList(dates);

        List<DwsDailyPackageAllAdEvent> activeList = dwsDailyPackageAllAdEventMapper.queryStatisticsActive(dates);
        if (!CollectionUtils.isEmpty(activeList)) {
            activeList.forEach(DwsDailyPackageAllAdEvent::calculate);
            dwBatchMapper.batchInsert(activeList, DwsDailyPackageAllAdEventMapper.class);
        }

        List<DwsDailyPackageAllAdEvent> newList = dwsDailyPackageAllAdEventMapper.queryStatisticsNew(dates);
        if (!CollectionUtils.isEmpty(newList)) {
            newList.forEach(DwsDailyPackageAllAdEvent::calculate);
            dwBatchMapper.batchInsert(newList, DwsDailyPackageAllAdEventMapper.class);
        }
        // 获取展示事件
        List<Long> delIds = getDelIds(dbList, activeList, newList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPackageAllAdEventMapper.deleteBatchIds(delIds);
        }
        applicationEventPublisher.publishEvent(new DwsDailyPackageAllAdEventUpdateDwEvent(this, dates));
    }
}
