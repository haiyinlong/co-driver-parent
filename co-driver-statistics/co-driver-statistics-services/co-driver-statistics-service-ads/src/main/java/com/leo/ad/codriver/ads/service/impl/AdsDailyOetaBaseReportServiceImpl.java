package com.leo.ad.codriver.ads.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.ads.dao.AdsDailyOetaBaseReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyOetaBaseReport;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyOetaBaseReportServiceImpl<br/>
 * 有统计留存数据，定时服务每日重新统计数据<br>
 * 统计维度：日期，包，版本
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
        // 活跃用户
        List<AdsDailyOetaBaseReport> activeUserList = adsDailyOetaBaseReportMapper.selectActiveUserList(dates);
        if (CollectionUtils.isEmpty(activeUserList)) {
            return;
        }
        activeUserList.forEach(AdsDailyOetaBaseReport::init);
        batchMapper.batchInsert(activeUserList, AdsDailyOetaBaseReportMapper.class);
        // 删除不存在的记录
        List<AdsDailyOetaBaseReport> oetaBaseReportList = adsDailyOetaBaseReportMapper.queryOetaBaseReportList(dates);
        Map<Long, List<AdsDailyOetaBaseReport>> oetaBaseUserTypeMap =
            oetaBaseReportList.stream().collect(Collectors.groupingBy(AdsDailyOetaBaseReport::getUserType));

        List<Long> notExistsIds = getNotExistsIds(oetaBaseUserTypeMap.get(0), activeUserList);
        if (!CollectionUtils.isEmpty(notExistsIds)) {
            adsDailyOetaBaseReportMapper.deleteBatchIds(notExistsIds);
        }

        // 新用户
        List<AdsDailyOetaBaseReport> newUserList = adsDailyOetaBaseReportMapper.selectNewUserList(dates);
        if (CollectionUtils.isEmpty(newUserList)) {
            return;
        }
        newUserList.forEach(AdsDailyOetaBaseReport::init);
        batchMapper.batchInsert(newUserList, AdsDailyOetaBaseReportMapper.class);
        // 删除不存在的记录
        notExistsIds = getNotExistsIds(oetaBaseUserTypeMap.get(1), newUserList);
        if (!CollectionUtils.isEmpty(notExistsIds)) {
            adsDailyOetaBaseReportMapper.deleteBatchIds(notExistsIds);
        }
    }

    private List<Long> getNotExistsIds(List<AdsDailyOetaBaseReport> oetaBaseReports,
        List<AdsDailyOetaBaseReport> oetaBaseReportList) {
        if (CollectionUtils.isEmpty(oetaBaseReports)) {
            return Collections.emptyList();
        }
        List<Long> dbIds = new ArrayList<>(oetaBaseReports.stream().map(AdsDailyOetaBaseReport::getId)
            .filter(id -> !ObjectUtils.isEmpty(id)).toList());
        oetaBaseReportList.forEach(item -> dbIds.remove(item.getId()));
        return dbIds;
    }
}
