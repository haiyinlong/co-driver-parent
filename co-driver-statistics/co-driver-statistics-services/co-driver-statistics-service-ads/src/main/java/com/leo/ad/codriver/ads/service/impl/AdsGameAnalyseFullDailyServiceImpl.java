package com.leo.ad.codriver.ads.service.impl;

import com.leo.ad.codriver.ads.dao.AdsGameAnalyseFullDailyMapper;
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
public class AdsGameAnalyseFullDailyServiceImpl implements AdsService {

    private final AdsGameAnalyseFullDailyMapper adsGameAnalyseFullDailyMapper;
    private final DwBatchMapper<AdsGameAnalyseFullDaily, AdsGameAnalyseFullDailyMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsGameAnalyseFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        List<AdsGameAnalyseFullDaily> adsGameAnalyseActiveFullDailies = adsGameAnalyseFullDailyMapper.queryStatisticsActiveList(dates);
        batchMapper.batchInsert(adsGameAnalyseActiveFullDailies, AdsGameAnalyseFullDailyMapper.class);
        List<AdsGameAnalyseFullDaily> adsGameAnalyseNewFullDailies = adsGameAnalyseFullDailyMapper.queryStatisticsNewList(dates);
        batchMapper.batchInsert(adsGameAnalyseNewFullDailies, AdsGameAnalyseFullDailyMapper.class);
    }

}
