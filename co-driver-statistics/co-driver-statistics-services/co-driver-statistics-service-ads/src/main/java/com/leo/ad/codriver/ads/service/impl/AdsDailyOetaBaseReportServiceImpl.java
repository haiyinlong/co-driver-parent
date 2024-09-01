package com.leo.ad.codriver.ads.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.ads.dao.AdsDailyOetaBaseReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyOetaBaseReport;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyOetaBaseReportServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 11:07
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AdsDailyOetaBaseReportServiceImpl implements AdsService {
    private final AdsDailyOetaBaseReportMapper adsDailyOetaBaseReportMapper;
    private final DwBatchMapper<AdsDailyOetaBaseReport, AdsDailyOetaBaseReportMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsDailyOetaBaseReportService syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        adsDailyOetaBaseReportMapper.deleteByDates(dates);
        // 活跃用户
        List<AdsDailyOetaBaseReport> activeUserList = adsDailyOetaBaseReportMapper.selectActiveUserList(dates);
        if (CollectionUtils.isEmpty(activeUserList)) {
            return;
        }
        // 新用户
        List<AdsDailyOetaBaseReport> newUserList = adsDailyOetaBaseReportMapper.selectNewUserList(dates);
        activeUserList.addAll(newUserList);
        activeUserList.forEach(AdsDailyOetaBaseReport::init);
        batchMapper.batchInsert(activeUserList, AdsDailyOetaBaseReportMapper.class);
    }
}
