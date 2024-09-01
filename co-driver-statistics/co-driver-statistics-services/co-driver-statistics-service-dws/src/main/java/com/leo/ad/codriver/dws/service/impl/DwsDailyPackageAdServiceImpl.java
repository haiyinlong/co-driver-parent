package com.leo.ad.codriver.dws.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAdMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAd;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAdServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 18:28
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAdServiceImpl implements DwsService {
    private final DwsDailyPackageAdMapper dwsDailyPackageAdMapper;
    private final DwBatchMapper<DwsDailyPackageAd, DwsDailyPackageAdMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAd")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAdMapper.deleteByDates(dates);
        List<DwsDailyPackageAd> queryStatisticsAll = dwsDailyPackageAdMapper.queryStatisticsAll(dates);
        if (CollectionUtils.isEmpty(queryStatisticsAll)) {
            return;
        }
        List<DwsDailyPackageAd> queryStatisticsNew = dwsDailyPackageAdMapper.queryStatisticsNew(dates);
        queryStatisticsAll = this.mergeAllAndNew(queryStatisticsAll, queryStatisticsNew);
        queryStatisticsAll.forEach(DwsDailyPackageAd::initAndCalculateEcpm);

        dwBatchMapper.batchInsert(queryStatisticsAll, DwsDailyPackageAdMapper.class);
    }

    private List<DwsDailyPackageAd> mergeAllAndNew(List<DwsDailyPackageAd> queryStatisticsAll,
        List<DwsDailyPackageAd> queryStatisticsNew) {
        if (CollectionUtils.isEmpty(queryStatisticsNew)) {
            return queryStatisticsAll;
        }
        Map<String, DwsDailyPackageAd> newAdMap = queryStatisticsNew.stream()
            .collect(Collectors.toMap(DwsDailyPackageAd::getUniqueId, dwsDailyPackageAd -> dwsDailyPackageAd));

        for (DwsDailyPackageAd dwsDailyPackageAd : queryStatisticsAll) {
            String uniqueId = dwsDailyPackageAd.getUniqueId();
            DwsDailyPackageAd newAd = newAdMap.get(uniqueId);
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
