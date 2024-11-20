package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcAdMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcAd;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_ad】的数据库操作Service实现
 * @createDate 2024-11-20 17:07:05
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcAdServiceImpl implements DwsService {

    private final DwsDailyPkgUsrcAdMapper dwsDailyPkgUsrcAdMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcAd, DwsDailyPkgUsrcAdMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgUsrcAd")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        // 不删除，每次更新;
        List<DwsDailyPkgUsrcAd> dbAdList = dwsDailyPkgUsrcAdMapper.queryDbList(dates);
        List<DwsDailyPkgUsrcAd> queryStatisticsActive = dwsDailyPkgUsrcAdMapper.queryStatisticsActive(dates);
        if (CollectionUtils.isEmpty(queryStatisticsActive)) {
            return;
        }

        List<DwsDailyPkgUsrcAd> queryStatisticsNew = dwsDailyPkgUsrcAdMapper.queryStatisticsNew(dates);

        List<DwsDailyPkgUsrcAd> queryStatisticsAll = this.mergeAllAndNew(queryStatisticsActive, queryStatisticsNew);
        queryStatisticsAll.forEach(DwsDailyPkgUsrcAd::initAndCalculateEcpm);
        dwBatchMapper.batchInsert(queryStatisticsAll, DwsDailyPkgUsrcAdMapper.class);
        // 删除不用的
        List<Long> dbNewAdDelIds = this.getDelIds(dbAdList, queryStatisticsAll, null);
        if (!CollectionUtils.isEmpty(dbNewAdDelIds)) {
            dwsDailyPkgUsrcAdMapper.deleteBatchIds(dbNewAdDelIds);
        }
        // 发布事件
        // applicationEventPublisher.publishEvent(new DwsDailyPkgUsrcAdUpdateDwEvent(this, dates));
    }

    private List<DwsDailyPkgUsrcAd> mergeAllAndNew(List<DwsDailyPkgUsrcAd> queryStatisticsAll,
        List<DwsDailyPkgUsrcAd> queryStatisticsNew) {
        if (CollectionUtils.isEmpty(queryStatisticsNew)) {
            return queryStatisticsAll;
        }
        Map<String, DwsDailyPkgUsrcAd> newAdMap = queryStatisticsNew.stream()
            .collect(Collectors.toMap(DwsDailyPkgUsrcAd::getUniqueId, dwsDailyPackageAd -> dwsDailyPackageAd));

        for (DwsDailyPkgUsrcAd dwsDailyPackageAd : queryStatisticsAll) {
            String uniqueId = dwsDailyPackageAd.getUniqueId();
            DwsDailyPkgUsrcAd newAd = newAdMap.get(uniqueId);
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
