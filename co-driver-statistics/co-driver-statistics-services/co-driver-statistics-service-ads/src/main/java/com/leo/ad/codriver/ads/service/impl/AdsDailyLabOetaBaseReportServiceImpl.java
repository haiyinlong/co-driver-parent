package com.leo.ad.codriver.ads.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.ads.dao.AdsDailyLabOetaBaseReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyLabOetaBaseReport;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyLabOetaBaseReportServiceImpl<br/>
 * 有统计留存数据，定时服务每日重新统计数据<br>
 * 统计维度：日期，包，版本
 *
 * @author HaiYinLong
 * @version 2024/09/01 11:07
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AdsDailyLabOetaBaseReportServiceImpl implements AdsService {
    private final AdsDailyLabOetaBaseReportMapper adsDailyLabOetaBaseReportMapper;
    private final DwBatchMapper<AdsDailyLabOetaBaseReport, AdsDailyLabOetaBaseReportMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "AdsDailyLabOetaBaseReportService syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        adsDailyLabOetaBaseReportMapper.deleteByDates(dates);
        // 活跃用户
        List<AdsDailyLabOetaBaseReport> activeUserList = adsDailyLabOetaBaseReportMapper.selectActiveUserList(dates);
        if (CollectionUtils.isEmpty(activeUserList)) {
            return;
        }
        activeUserList.forEach(AdsDailyLabOetaBaseReport::init);
        batchMapper.batchInsert(activeUserList, AdsDailyLabOetaBaseReportMapper.class);
        // 新用户
        List<AdsDailyLabOetaBaseReport> newUserList = adsDailyLabOetaBaseReportMapper.selectNewUserList(dates);
        if (CollectionUtils.isEmpty(newUserList)) {
            return;
        }
        newUserList.forEach(AdsDailyLabOetaBaseReport::init);
        batchMapper.batchInsert(newUserList, AdsDailyLabOetaBaseReportMapper.class);
    }
}
