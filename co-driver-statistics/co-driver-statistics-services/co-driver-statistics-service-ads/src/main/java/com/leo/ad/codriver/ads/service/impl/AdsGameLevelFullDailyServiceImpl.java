package com.leo.ad.codriver.ads.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.ads.dao.AdsGameLevelFullDailyMapper;
import com.leo.ad.codriver.ads.entity.AdsGameLevelFullDaily;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * 游戏关卡统计
 *
 * @author HaiYinLong
 * @version 2024/07/02 16:23
 **/
@Service
@AllArgsConstructor
public class AdsGameLevelFullDailyServiceImpl implements AdsService {

    private final AdsGameLevelFullDailyMapper adsGameLevelFullDailyMapper;
    private final DwBatchMapper<AdsGameLevelFullDaily, AdsGameLevelFullDailyMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsGameLevelFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        List<AdsGameLevelFullDaily> adsGameLevelFullDailies = adsGameLevelFullDailyMapper.queryStatistics(dates);
        batchMapper.batchInsert(adsGameLevelFullDailies, AdsGameLevelFullDailyMapper.class);
    }

}
