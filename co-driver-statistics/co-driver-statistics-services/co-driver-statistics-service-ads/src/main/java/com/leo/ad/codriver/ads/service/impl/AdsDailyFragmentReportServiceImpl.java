package com.leo.ad.codriver.ads.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.ads.dao.AdsDailyFragmentReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyFragmentReport;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * AdsDailyFragmentReportServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/05/12 17:29
 **/
@Service
@AllArgsConstructor
public class AdsDailyFragmentReportServiceImpl implements AdsService {
    private final AdsDailyFragmentReportMapper adsDailyFragmentReportMapper;
    private final DwBatchMapper<AdsDailyFragmentReport, AdsDailyFragmentReportMapper> batchMapper;

    @ShowExecuteTime(name = "AdsDailyFragmentReport syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        adsDailyFragmentReportMapper.deleteByDates(dates);
        List<AdsDailyFragmentReport> adsDailyFragmentReportList = adsDailyFragmentReportMapper.queryStatistics(dates);
        batchMapper.batchInsert(adsDailyFragmentReportList, AdsDailyFragmentReportMapper.class);
    }
}
