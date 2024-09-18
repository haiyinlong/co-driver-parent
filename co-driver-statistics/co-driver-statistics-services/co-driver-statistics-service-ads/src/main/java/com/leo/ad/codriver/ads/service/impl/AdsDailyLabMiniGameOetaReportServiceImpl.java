package com.leo.ad.codriver.ads.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.ads.dao.AdsDailyLabMiniGameOetaReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyLabMiniGameOetaReport;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyLabMinGameOetaReportServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/11 18:34
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AdsDailyLabMiniGameOetaReportServiceImpl implements AdsService {
    private final AdsDailyLabMiniGameOetaReportMapper adsDailyMiniGameOetaReportMapper;
    private final DwBatchMapper<AdsDailyLabMiniGameOetaReport, AdsDailyLabMiniGameOetaReportMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsDailyLabMiniGameOetaReportService syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 获取活跃用户
        List<AdsDailyLabMiniGameOetaReport> activeUserList =
            adsDailyMiniGameOetaReportMapper.queryActiveUserList(dates);
        activeUserList.forEach(AdsDailyLabMiniGameOetaReport::init);
        batchMapper.batchInsert(activeUserList, AdsDailyLabMiniGameOetaReportMapper.class);
        // 获取新用户
        List<AdsDailyLabMiniGameOetaReport> newUserList = adsDailyMiniGameOetaReportMapper.queryNewUserList(dates);
        newUserList.forEach(AdsDailyLabMiniGameOetaReport::init);
        batchMapper.batchInsert(newUserList, AdsDailyLabMiniGameOetaReportMapper.class);
        // 删除不存在的记录
        List<AdsDailyLabMiniGameOetaReport> dbList = adsDailyMiniGameOetaReportMapper.queryList(dates);
        List<Long> delIds = getNotExistsIds(dbList, activeUserList, newUserList);
        if (!CollectionUtils.isEmpty(delIds)) {
            adsDailyMiniGameOetaReportMapper.deleteBatchIds(delIds);
        }
    }

}
