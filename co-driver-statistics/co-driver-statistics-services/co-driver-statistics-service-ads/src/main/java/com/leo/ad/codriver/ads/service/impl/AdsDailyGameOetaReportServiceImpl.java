package com.leo.ad.codriver.ads.service.impl;

import com.leo.ad.codriver.ads.dao.AdsDailyGameOetaReportMapper;
import com.leo.ad.codriver.ads.dao.AdsGameAnalyseFullDailyMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyGameOetaReport;
import com.leo.ad.codriver.ads.entity.AdsGameAnalyseFullDaily;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 游戏概况统计
 *
 * @author HaiYinLong
 * @version 2024/07/02 16:23
 **/
@Service
@AllArgsConstructor
public class AdsDailyGameOetaReportServiceImpl implements AdsService {

    private final AdsDailyGameOetaReportMapper adsDailyGameOetaReportMapper;
    private final DwBatchMapper<AdsDailyGameOetaReport, AdsDailyGameOetaReportMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsDailyGameOetaReport syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        List<AdsDailyGameOetaReport> adsGameAnalyseActiveFullDailies = adsDailyGameOetaReportMapper.queryStatisticsActiveList(dates);
        batchMapper.batchInsert(adsGameAnalyseActiveFullDailies, AdsDailyGameOetaReportMapper.class);
        List<AdsDailyGameOetaReport> adsGameAnalyseNewFullDailies = adsDailyGameOetaReportMapper.queryStatisticsNewList(dates);
        batchMapper.batchInsert(adsGameAnalyseNewFullDailies, AdsDailyGameOetaReportMapper.class);
    }

}
