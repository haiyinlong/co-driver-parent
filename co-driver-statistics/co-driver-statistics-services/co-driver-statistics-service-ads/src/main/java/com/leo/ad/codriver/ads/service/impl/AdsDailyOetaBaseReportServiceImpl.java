package com.leo.ad.codriver.ads.service.impl;

import com.leo.ad.codriver.ads.dao.AdsDailyOetaBaseReportMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyOetaBaseReport;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * AdsDailyOetaBaseReportServiceImpl<br/> 有统计留存数据，定时服务每日重新统计数据<br> 统计维度：日期，包，版本
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
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 修改转化广告用户
        List<AdsDailyOetaBaseReport> oetaDbList = adsDailyOetaBaseReportMapper.queryOetaBaseReportList(dates);
        // 活跃用户
        List<AdsDailyOetaBaseReport> activeUserList = adsDailyOetaBaseReportMapper.selectActivePkgVerList(dates);
        if (!CollectionUtils.isEmpty(activeUserList)) {
            activeUserList.forEach(AdsDailyOetaBaseReport::init);
            batchMapper.batchInsert(activeUserList, AdsDailyOetaBaseReportMapper.class);
        }
        // 新用户
        List<AdsDailyOetaBaseReport> newUserList = adsDailyOetaBaseReportMapper.selectNewPkgVerList(dates);
        if (!CollectionUtils.isEmpty(newUserList)) {
            newUserList.forEach(AdsDailyOetaBaseReport::init);
            batchMapper.batchInsert(newUserList, AdsDailyOetaBaseReportMapper.class);
        }
        // ALL 所有版本
        // 活跃用户
        List<AdsDailyOetaBaseReport> activeUserAllList = adsDailyOetaBaseReportMapper.selectActivePkgList(dates);
        if (!CollectionUtils.isEmpty(activeUserAllList)) {
            activeUserAllList.forEach(AdsDailyOetaBaseReport::init);
            batchMapper.batchInsert(activeUserAllList, AdsDailyOetaBaseReportMapper.class);
        }
        // 新用户
        List<AdsDailyOetaBaseReport> newUserAllList = adsDailyOetaBaseReportMapper.selectNewPkgList(dates);
        if (!CollectionUtils.isEmpty(newUserAllList)) {
            newUserAllList.forEach(AdsDailyOetaBaseReport::init);
            batchMapper.batchInsert(newUserAllList, AdsDailyOetaBaseReportMapper.class);
        }
        // 统计用户来源数据
        List<AdsDailyOetaBaseReport> usrcActiveList = adsDailyOetaBaseReportMapper.selectActivePkgVerUsrcList(dates);
        if (!CollectionUtils.isEmpty(usrcActiveList)) {
            usrcActiveList.forEach(AdsDailyOetaBaseReport::init);
            batchMapper.batchInsert(usrcActiveList, AdsDailyOetaBaseReportMapper.class);
        }
        // 新用户
        List<AdsDailyOetaBaseReport> usrcNewList = adsDailyOetaBaseReportMapper.selectNewPkgVerUsrcList(dates);
        if (!CollectionUtils.isEmpty(usrcNewList)) {
            usrcNewList.forEach(AdsDailyOetaBaseReport::init);
            batchMapper.batchInsert(usrcNewList, AdsDailyOetaBaseReportMapper.class);
        }

        // TODO ALL版本的统计用户来源数据
        List<AdsDailyOetaBaseReport> usrcAllActiveList = adsDailyOetaBaseReportMapper.selectActivePkgUsrcList(dates);
        if (!CollectionUtils.isEmpty(usrcAllActiveList)) {
            usrcAllActiveList.forEach(AdsDailyOetaBaseReport::init);
            batchMapper.batchInsert(usrcAllActiveList, AdsDailyOetaBaseReportMapper.class);
        }
        // 新用户
        List<AdsDailyOetaBaseReport> usrcAllNewList = adsDailyOetaBaseReportMapper.selectNewPkgUsrcList(dates);
        if (!CollectionUtils.isEmpty(usrcAllNewList)) {
            usrcAllNewList.forEach(AdsDailyOetaBaseReport::init);
            batchMapper.batchInsert(usrcAllNewList, AdsDailyOetaBaseReportMapper.class);
        }

        // 删除不存在的记录
        List<Long> notExistsIds =
            getNotExistsIds(oetaDbList, activeUserList, newUserList, activeUserAllList, newUserAllList, usrcActiveList,
                usrcNewList, usrcAllActiveList, usrcAllNewList);
        if (!CollectionUtils.isEmpty(notExistsIds)) {
            adsDailyOetaBaseReportMapper.deleteBatchIds(notExistsIds);
        }

    }

}
