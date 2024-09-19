package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgAdEventMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgAdEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgAdEventUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgAdEventServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/18 16:24
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgAdEventServiceImpl implements DwsService {
    private final DwsDailyPkgAdEventMapper dwsDailyPkgAdEventMapper;
    private final DwBatchMapper<DwsDailyPkgAdEvent, DwsDailyPkgAdEventMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgAdEvent")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        // TODO 改为数据库获取明细，代码中进行汇总，控制新增，修改和删除；
        List<DwsDailyPkgAdEvent> dbList = dwsDailyPkgAdEventMapper.queryList(dates);

        List<DwsDailyPkgAdEvent> activeList = dwsDailyPkgAdEventMapper.queryStatisticsActive(dates);
        if (!CollectionUtils.isEmpty(activeList)) {
            activeList.forEach(DwsDailyPkgAdEvent::calculate);
            dwBatchMapper.batchInsert(activeList, DwsDailyPkgAdEventMapper.class);
        }

        List<DwsDailyPkgAdEvent> newList = dwsDailyPkgAdEventMapper.queryStatisticsNew(dates);
        if (!CollectionUtils.isEmpty(newList)) {
            newList.forEach(DwsDailyPkgAdEvent::calculate);
            dwBatchMapper.batchInsert(newList, DwsDailyPkgAdEventMapper.class);
        }
        // 获取展示事件
        List<Long> delIds = getDelIds(dbList, activeList, newList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgAdEventMapper.deleteBatchIds(delIds);
        }
        applicationEventPublisher.publishEvent(new DwsDailyPkgAdEventUpdateDwEvent(this, dates));
    }
}
