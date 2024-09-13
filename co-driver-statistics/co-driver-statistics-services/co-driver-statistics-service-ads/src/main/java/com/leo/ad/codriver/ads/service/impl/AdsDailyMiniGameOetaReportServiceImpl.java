package com.leo.ad.codriver.ads.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.ads.dao.AdsDailyMiniGameOetaReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyMiniGameOetaReport;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyMinGameOetaReportServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/11 18:34
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AdsDailyMiniGameOetaReportServiceImpl implements AdsService {
    private final AdsDailyMiniGameOetaReportMapper adsDailyMiniGameOetaReportMapper;
    private final DwBatchMapper<AdsDailyMiniGameOetaReport, AdsDailyMiniGameOetaReportMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsDailyMiniGameOetaReportService syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        // 获取活跃用户
        List<AdsDailyMiniGameOetaReport> activeUserList = adsDailyMiniGameOetaReportMapper.queryActiveUserList(dates);
        activeUserList.forEach(AdsDailyMiniGameOetaReport::init);
        batchMapper.batchInsert(activeUserList, AdsDailyMiniGameOetaReportMapper.class);
        // 获取新用户
        List<AdsDailyMiniGameOetaReport> newUserList = adsDailyMiniGameOetaReportMapper.queryNewUserList(dates);
        newUserList.forEach(AdsDailyMiniGameOetaReport::init);
        batchMapper.batchInsert(newUserList, AdsDailyMiniGameOetaReportMapper.class);
        // 删除不存在的记录
        List<AdsDailyMiniGameOetaReport> dbList = adsDailyMiniGameOetaReportMapper.queryList(dates);
        List<Long> delIds = getNotExistsIds(dbList, activeUserList, newUserList);
        if (!CollectionUtils.isEmpty(delIds)) {
            adsDailyMiniGameOetaReportMapper.deleteBatchIds(delIds);
        }
    }

}
