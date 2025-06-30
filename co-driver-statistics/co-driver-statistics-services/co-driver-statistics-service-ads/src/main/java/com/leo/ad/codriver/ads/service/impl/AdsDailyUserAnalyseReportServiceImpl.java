package com.leo.ad.codriver.ads.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.ads.service.useranalyse.CohortUserAnalysePkgReportService;
import com.leo.ad.codriver.ads.service.useranalyse.CohortUserAnalysePkgUsrcReportService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyUserAnalyseReportServiceImpl<br/>
 * 有统计留存数据，定时服务每日重新统计数据<br>
 * 统计维度：日期，包，版本
 *
 * @author HaiYinLong
 * @version 2024/09/01 11:07
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AdsDailyUserAnalyseReportServiceImpl implements AdsService {
    private final CohortUserAnalysePkgUsrcReportService cohortUserAnalysePkgUsrcReportService;
    private final CohortUserAnalysePkgReportService cohortUserAnalysePkgReportService;

    // TODO 通过业务数据对象进行计算生成报表
    @Override
    @ShowExecuteTime(name = "AdsDailyUserAnalyseReportServiceImpl syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        cohortUserAnalysePkgUsrcReportService.syncData(dates);
        cohortUserAnalysePkgReportService.syncData(dates);
        // 删除不存在的记录
        // List<Long> notExistsIds = getNotExistsIds(oetaDbList, activeUserList, newUserList, activeUserAllList,
        // newUserAllList, usrcActiveList, usrcNewList, usrcAllActiveList, usrcAllNewList);
        // if (!CollectionUtils.isEmpty(notExistsIds)) {
        // adsDailyOetaBaseReportMapper.deleteBatchIds(notExistsIds);
        // }

    }

}
