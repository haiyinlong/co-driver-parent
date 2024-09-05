package com.leo.ad.codriver.dws.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllLabAdUpdateDwEvent;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabAdMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabAd;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllLabAdServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 18:28
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllLabAdServiceImpl implements DwsService {
    private final DwsDailyPackageAllLabAdMapper dwsDailyPackageAllLabAdMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabAd, DwsDailyPackageAllLabAdMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabAd")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabAdMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabAd> queryStatisticsAll = dwsDailyPackageAllLabAdMapper.queryStatisticsAll(dates);
        if (CollectionUtils.isEmpty(queryStatisticsAll)) {
            return;
        }
        List<DwsDailyPackageAllLabAd> queryStatisticsNew = dwsDailyPackageAllLabAdMapper.queryStatisticsNew(dates);
        queryStatisticsAll = this.mergeAllAndNew(queryStatisticsAll, queryStatisticsNew);
        queryStatisticsAll.forEach(DwsDailyPackageAllLabAd::initAndCalculateEcpm);

        dwBatchMapper.batchInsert(queryStatisticsAll, DwsDailyPackageAllLabAdMapper.class);

        applicationEventPublisher.publishEvent(new DwsDailyPackageAllLabAdUpdateDwEvent(this, dates));
    }

    private List<DwsDailyPackageAllLabAd> mergeAllAndNew(List<DwsDailyPackageAllLabAd> queryStatisticsAll,
        List<DwsDailyPackageAllLabAd> queryStatisticsNew) {
        if (CollectionUtils.isEmpty(queryStatisticsNew)) {
            return queryStatisticsAll;
        }
        Map<String, DwsDailyPackageAllLabAd> newAdMap = queryStatisticsNew.stream()
            .collect(Collectors.toMap(DwsDailyPackageAllLabAd::getUniqueId, dwsDailyPackageAd -> dwsDailyPackageAd));

        for (DwsDailyPackageAllLabAd dwsDailyPackageAd : queryStatisticsAll) {
            String uniqueId = dwsDailyPackageAd.getUniqueId();
            DwsDailyPackageAllLabAd newAd = newAdMap.get(uniqueId);
            if (ObjectUtils.isEmpty(newAd)) {
                continue;
            }
            dwsDailyPackageAd.setNewUserTotalUserNum(newAd.getNewUserTotalUserNum());
            dwsDailyPackageAd.setNewUserTotalIncome(newAd.getNewUserTotalIncome());
            dwsDailyPackageAd.setNewUserTotalShowNum(newAd.getNewUserTotalShowNum());
            dwsDailyPackageAd.setNewUserRewardUserNum(newAd.getNewUserRewardUserNum());
            dwsDailyPackageAd.setNewUserRewardIncome(newAd.getNewUserRewardIncome());
            dwsDailyPackageAd.setNewUserRewardShowNum(newAd.getNewUserRewardShowNum());
            dwsDailyPackageAd.setNewUserNoSoldUserNum(newAd.getNewUserNoSoldUserNum());
            dwsDailyPackageAd.setNewUserNoSoldIncome(newAd.getNewUserNoSoldIncome());
            dwsDailyPackageAd.setNewUserNoSoldShowNum(newAd.getNewUserNoSoldShowNum());
            dwsDailyPackageAd.setNewUserSoldUserNum(newAd.getNewUserSoldUserNum());
            dwsDailyPackageAd.setNewUserSoldShowNum(newAd.getNewUserSoldShowNum());
            dwsDailyPackageAd.setNewUserSoldIncome(newAd.getNewUserSoldIncome());
            dwsDailyPackageAd.setNewUserNoBannerUserNum(newAd.getNewUserNoBannerUserNum());
            dwsDailyPackageAd.setNewUserNoBannerIncome(newAd.getNewUserNoBannerIncome());
            dwsDailyPackageAd.setNewUserNoBannerShowNum(newAd.getNewUserNoBannerShowNum());
            dwsDailyPackageAd.setNewUserInterUserNum(newAd.getNewUserInterUserNum());
            dwsDailyPackageAd.setNewUserInterIncome(newAd.getNewUserInterIncome());
            dwsDailyPackageAd.setNewUserInterShowNum(newAd.getNewUserInterShowNum());
            dwsDailyPackageAd.setNewUserMrecUserNum(newAd.getNewUserMrecUserNum());
            dwsDailyPackageAd.setNewUserMrecIncome(newAd.getNewUserMrecIncome());
            dwsDailyPackageAd.setNewUserMrecShowNum(newAd.getNewUserMrecShowNum());
        }
        return queryStatisticsAll;
    }
}
