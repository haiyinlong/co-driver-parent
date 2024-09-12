package com.leo.ad.codriver.ads.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.ads.dao.AdsDailyMinGameOetaReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyMinGameOetaReport;
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
public class AdsDailyMinGameOetaReportServiceImpl implements AdsService {
    private final AdsDailyMinGameOetaReportMapper adsDailyMinGameOetaReportMapper;
    private final DwBatchMapper<AdsDailyMinGameOetaReport, AdsDailyMinGameOetaReportMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsDailyMinGameOetaReportService syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        // 获取活跃用户
        List<AdsDailyMinGameOetaReport> activeUserList = adsDailyMinGameOetaReportMapper.queryActiveUserList(dates);
        activeUserList.forEach(AdsDailyMinGameOetaReport::init);
        batchMapper.batchInsert(activeUserList, AdsDailyMinGameOetaReportMapper.class);
        // 获取新用户
        List<AdsDailyMinGameOetaReport> newUserList = adsDailyMinGameOetaReportMapper.queryNewUserList(dates);
        newUserList.forEach(AdsDailyMinGameOetaReport::init);
        batchMapper.batchInsert(newUserList, AdsDailyMinGameOetaReportMapper.class);
        // 删除不存在的记录
        List<AdsDailyMinGameOetaReport> dbList = adsDailyMinGameOetaReportMapper.queryList(dates);
        List<Long> delIds = getNotExistsIds(dbList, activeUserList, newUserList);
        if (!CollectionUtils.isEmpty(delIds)) {
            adsDailyMinGameOetaReportMapper.deleteBatchIds(delIds);
        }
    }

}
