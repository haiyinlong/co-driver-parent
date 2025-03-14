package com.leo.ad.codriver.ads.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.ads.dao.AdsOeta30DaysAccumulateIncomeMapper;
import com.leo.ad.codriver.ads.entity.AdsOeta30DaysAccumulateIncome;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsOeta30DaysAccumulateIncomeServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/03/13 17:30
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class AdsOeta30DaysAccumulateIncomeServiceImpl implements AdsService {
    private final AdsOeta30DaysAccumulateIncomeMapper adsOeta30DaysAccumulateIncomeMapper;
    private final DwBatchMapper<AdsOeta30DaysAccumulateIncome, AdsOeta30DaysAccumulateIncomeMapper> batchMapper;

    @Override
    public void syncData(Integer dates) {
        // 根据统计日期计算区间
        Integer startDate = DateUtils.getPreviousDate(dates, 30);
        List<AdsOeta30DaysAccumulateIncome> dbList = adsOeta30DaysAccumulateIncomeMapper.queryDbList(startDate, dates);
        Map<String, AdsOeta30DaysAccumulateIncome> dbMap =
            dbList.stream().collect(Collectors.toMap(AdsOeta30DaysAccumulateIncome::getUniqueKey, Function.identity()));
        // 获取包数据，版本ALL,归因ALL
        List<AdsOeta30DaysAccumulateIncome> pkgList =
            adsOeta30DaysAccumulateIncomeMapper.statisticsPkgList(startDate, dates);
        updateId(pkgList, dbMap);
        batchMapper.batchInsert(pkgList, AdsOeta30DaysAccumulateIncomeMapper.class);
        // 获取包数据，版本,归因ALL
        List<AdsOeta30DaysAccumulateIncome> pkgVersionList =
            adsOeta30DaysAccumulateIncomeMapper.statisticsPkgVerList(startDate, dates);
        updateId(pkgVersionList, dbMap);
        batchMapper.batchInsert(pkgVersionList, AdsOeta30DaysAccumulateIncomeMapper.class);
        // 获取包数据，版本ALL,归因
        List<AdsOeta30DaysAccumulateIncome> pkgUsrcList =
            adsOeta30DaysAccumulateIncomeMapper.statisticsPkgUsrcList(startDate, dates);
        updateId(pkgUsrcList, dbMap);
        batchMapper.batchInsert(pkgUsrcList, AdsOeta30DaysAccumulateIncomeMapper.class);
        // 获取包数据，版本,归因
        List<AdsOeta30DaysAccumulateIncome> pkgVersionUsrcList =
            adsOeta30DaysAccumulateIncomeMapper.statisticsPkgVersionUsrcList(startDate, dates);
        updateId(pkgVersionUsrcList, dbMap);
        batchMapper.batchInsert(pkgVersionUsrcList, AdsOeta30DaysAccumulateIncomeMapper.class);
        // 删除不存在的记录
        List<Long> notExistsIds = getNotExistsIds(dbList, pkgList, pkgVersionList, pkgUsrcList, pkgVersionUsrcList);
        if (!CollectionUtils.isEmpty(notExistsIds)) {
            adsOeta30DaysAccumulateIncomeMapper.deleteBatchIds(notExistsIds);
        }
    }

    private void updateId(List<AdsOeta30DaysAccumulateIncome> list, Map<String, AdsOeta30DaysAccumulateIncome> dbMap) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (AdsOeta30DaysAccumulateIncome adsOeta30DaysAccumulateIncome : list) {
            if (dbMap.containsKey(adsOeta30DaysAccumulateIncome.getUniqueKey())) {
                AdsOeta30DaysAccumulateIncome dbAdsOeta30DaysAccumulateIncome =
                    dbMap.get(adsOeta30DaysAccumulateIncome.getUniqueKey());
                adsOeta30DaysAccumulateIncome.setId(dbAdsOeta30DaysAccumulateIncome.getId());
                adsOeta30DaysAccumulateIncome.setCreateTime(dbAdsOeta30DaysAccumulateIncome.getCreateTime());
            }
        }

    }
}
