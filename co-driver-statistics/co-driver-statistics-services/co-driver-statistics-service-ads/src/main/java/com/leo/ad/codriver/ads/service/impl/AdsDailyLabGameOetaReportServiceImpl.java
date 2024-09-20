package com.leo.ad.codriver.ads.service.impl;

import com.leo.ad.codriver.ads.dao.AdsDailyGameOetaReportMapper;
import com.leo.ad.codriver.ads.dao.AdsDailyLabGameOetaReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyGameOetaReport;
import com.leo.ad.codriver.ads.entity.AdsDailyLabGameOetaReport;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 游戏概况统计
 *
 * @author HaiYinLong
 * @version 2024/07/02 16:23
 **/
@Service
@AllArgsConstructor
public class AdsDailyLabGameOetaReportServiceImpl implements AdsService {

    private final AdsDailyLabGameOetaReportMapper adsDailyLabGameOetaReportMapper;
    private final DwBatchMapper<AdsDailyLabGameOetaReport, AdsDailyLabGameOetaReportMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsDailyLabGameOetaReport syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {


        // 获取活跃用户
        List<AdsDailyLabGameOetaReport> adsLabGameAnalyseActiveFullDailies = adsDailyLabGameOetaReportMapper.queryStatisticsActiveList(dates);
        adsLabGameAnalyseActiveFullDailies.forEach(AdsDailyLabGameOetaReport::init);
        batchMapper.batchInsert(adsLabGameAnalyseActiveFullDailies, AdsDailyLabGameOetaReportMapper.class);
        // 获取新用户
        List<AdsDailyLabGameOetaReport> adsLabGameAnalyseNewFullDailies = adsDailyLabGameOetaReportMapper.queryStatisticsNewList(dates);
        adsLabGameAnalyseNewFullDailies.forEach(AdsDailyLabGameOetaReport::init);
        batchMapper.batchInsert(adsLabGameAnalyseNewFullDailies, AdsDailyLabGameOetaReportMapper.class);
        // 删除不存在的记录
        List<AdsDailyLabGameOetaReport> dbList = adsDailyLabGameOetaReportMapper.queryList(dates);
        List<Long> delIds = getNotExistsIds(dbList, adsLabGameAnalyseActiveFullDailies, adsLabGameAnalyseNewFullDailies);
        if (!CollectionUtils.isEmpty(delIds)) {
            adsDailyLabGameOetaReportMapper.deleteBatchIds(delIds);
        }

    }

}
