package com.leo.ad.codriver.ads.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

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
        // 活跃用户
        List<AdsDailyLabOetaBaseReport> activeUserList = adsDailyLabOetaBaseReportMapper.selectActiveUserList(dates);
        if (CollectionUtils.isEmpty(activeUserList)) {
            return;
        }
        activeUserList.forEach(AdsDailyLabOetaBaseReport::init);
        batchMapper.batchInsert(activeUserList, AdsDailyLabOetaBaseReportMapper.class);
        // 删除不存在的记录
        List<AdsDailyLabOetaBaseReport> oetaBaseReportList =
            adsDailyLabOetaBaseReportMapper.queryOetaBaseReportList(dates);
        Map<Long, List<AdsDailyLabOetaBaseReport>> oetaBaseUserTypeMap =
            oetaBaseReportList.stream().collect(Collectors.groupingBy(AdsDailyLabOetaBaseReport::getUserType));

        List<Long> notExistsIds = getNotExistsIds(oetaBaseUserTypeMap.get(0), activeUserList);
        if (!CollectionUtils.isEmpty(notExistsIds)) {
            adsDailyLabOetaBaseReportMapper.deleteBatchIds(notExistsIds);
        }

        // 新用户
        List<AdsDailyLabOetaBaseReport> newUserList = adsDailyLabOetaBaseReportMapper.selectNewUserList(dates);
        if (CollectionUtils.isEmpty(newUserList)) {
            return;
        }
        newUserList.forEach(AdsDailyLabOetaBaseReport::init);
        batchMapper.batchInsert(newUserList, AdsDailyLabOetaBaseReportMapper.class);
        // 删除不存在的记录
        notExistsIds = getNotExistsIds(oetaBaseUserTypeMap.get(1), newUserList);
        if (!CollectionUtils.isEmpty(notExistsIds)) {
            adsDailyLabOetaBaseReportMapper.deleteBatchIds(notExistsIds);
        }
    }

    private List<Long> getNotExistsIds(List<AdsDailyLabOetaBaseReport> labOetaBaseReports,
        List<AdsDailyLabOetaBaseReport> oetaBaseReportList) {
        List<Long> dbIds = new ArrayList<>(labOetaBaseReports.stream().map(AdsDailyLabOetaBaseReport::getId)
            .filter(id -> !ObjectUtils.isEmpty(id)).toList());
        oetaBaseReportList.forEach(item -> dbIds.remove(item.getId()));
        return dbIds;
    }
}
