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
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllAdUpdateEvent;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllAdMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllAd;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllAdServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 18:28
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllAdServiceImpl implements DwsService {
    private final DwsDailyPackageAllAdMapper dwsDailyPackageAllAdMapper;
    private final DwBatchMapper<DwsDailyPackageAllAd, DwsDailyPackageAllAdMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllAd")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllAdMapper.deleteByDates(dates);
        List<DwsDailyPackageAllAd> queryStatisticsAll = dwsDailyPackageAllAdMapper.queryStatisticsAll(dates);
        if (CollectionUtils.isEmpty(queryStatisticsAll)) {
            return;
        }
        List<DwsDailyPackageAllAd> queryStatisticsNew = dwsDailyPackageAllAdMapper.queryStatisticsNew(dates);
        queryStatisticsAll = this.mergeAllAndNew(queryStatisticsAll, queryStatisticsNew);
        queryStatisticsAll.forEach(DwsDailyPackageAllAd::initAndCalculateEcpm);

        dwBatchMapper.batchInsert(queryStatisticsAll, DwsDailyPackageAllAdMapper.class);

        applicationEventPublisher.publishEvent(new DwsDailyPackageAllAdUpdateEvent(this, dates));
    }

    private List<DwsDailyPackageAllAd> mergeAllAndNew(List<DwsDailyPackageAllAd> queryStatisticsAll,
        List<DwsDailyPackageAllAd> queryStatisticsNew) {
        if (CollectionUtils.isEmpty(queryStatisticsNew)) {
            return queryStatisticsAll;
        }
        Map<String, DwsDailyPackageAllAd> newAdMap = queryStatisticsNew.stream()
            .collect(Collectors.toMap(DwsDailyPackageAllAd::getUniqueId, dwsDailyPackageAd -> dwsDailyPackageAd));

        for (DwsDailyPackageAllAd dwsDailyPackageAd : queryStatisticsAll) {
            String uniqueId = dwsDailyPackageAd.getUniqueId();
            DwsDailyPackageAllAd newAd = newAdMap.get(uniqueId);
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
