package com.leo.ad.codriver.ads.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.ads.dao.AdsDailyLabMinGameOetaReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyLabMinGameOetaReport;
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
public class AdsDailyLabMinGameOetaReportServiceImpl implements AdsService {
    private final AdsDailyLabMinGameOetaReportMapper adsDailyMinGameOetaReportMapper;
    private final DwBatchMapper<AdsDailyLabMinGameOetaReport, AdsDailyLabMinGameOetaReportMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsDailyLabMinGameOetaReportService syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        // 获取活跃用户
        List<AdsDailyLabMinGameOetaReport> activeUserList = adsDailyMinGameOetaReportMapper.queryActiveUserList(dates);
        activeUserList.forEach(AdsDailyLabMinGameOetaReport::init);
        batchMapper.batchInsert(activeUserList, AdsDailyLabMinGameOetaReportMapper.class);
        // 获取新用户
        List<AdsDailyLabMinGameOetaReport> newUserList = adsDailyMinGameOetaReportMapper.queryNewUserList(dates);
        newUserList.forEach(AdsDailyLabMinGameOetaReport::init);
        batchMapper.batchInsert(newUserList, AdsDailyLabMinGameOetaReportMapper.class);
        // 删除不存在的记录
        List<AdsDailyLabMinGameOetaReport> dbList = adsDailyMinGameOetaReportMapper.queryList(dates);
        List<Long> delIds = getNotExistsIds(dbList, activeUserList, newUserList);
        if (!CollectionUtils.isEmpty(delIds)) {
            adsDailyMinGameOetaReportMapper.deleteBatchIds(delIds);
        }
    }

}
