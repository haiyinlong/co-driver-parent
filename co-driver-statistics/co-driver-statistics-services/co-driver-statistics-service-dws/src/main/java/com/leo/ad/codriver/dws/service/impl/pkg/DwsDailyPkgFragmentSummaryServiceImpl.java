package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserGameFragmentGoodsMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserGameFragmentGoods;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgFragmentSummaryMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgFragmentSummary;
import com.leo.ad.codriver.dws.event.DwsDailyPkgFragmentSummaryUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgFragmentSummaryServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/05/12 15:05
 **/
@Slf4j
@Service
@AllArgsConstructor
public class DwsDailyPkgFragmentSummaryServiceImpl implements DwsService {

    private final DwsDailyPkgFragmentSummaryMapper dwsDailyPkgFragmentSummaryMapper;
    private final DwdUserGameFragmentGoodsMapper dwdUserGameFragmentGoodsMapper;
    private final DwBatchMapper<DwsDailyPkgFragmentSummary, DwsDailyPkgFragmentSummaryMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgFragmentSummary")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        DwsDailyPkgFragmentSummary fragmentSummary;
        Map<Integer, DwsDailyPkgFragmentSummary> fragmentSummaryMap = null;
        Map<String, Map<Integer, DwsDailyPkgFragmentSummary>> pkgFragmentSummaryMap = new HashMap<>();
        // 分页获取数据，然后进行汇总计算
        dwsDailyPkgFragmentSummaryMapper.deleteByDates(dates);
        DwCountDTO dwdCountDTO = dwdUserGameFragmentGoodsMapper.getDwdFragmentStatisticsCount(dates);
        int num = dwdCountDTO.loopNum();
        for (int i = 1; i <= num; i++) {
            long startId = dwdCountDTO.loopStartId(i);
            long endId = dwdCountDTO.loopEndId(i);
            List<DwdUserGameFragmentGoods> userGameGoodsList =
                dwdUserGameFragmentGoodsMapper.queryDwdFragmentInterval(dates, startId, endId);
            if (ObjectUtils.isEmpty(userGameGoodsList)) {
                continue;
            }
            // 汇总计算
            for (DwdUserGameFragmentGoods dwdUserGameFragmentGoods : userGameGoodsList) {
                fragmentSummaryMap = pkgFragmentSummaryMap.get(dwdUserGameFragmentGoods.getPkg());
                if (CollectionUtils.isEmpty(fragmentSummaryMap)) {
                    fragmentSummaryMap = new HashMap<>();
                }
                fragmentSummary = fragmentSummaryMap.get(dwdUserGameFragmentGoods.getGoods());
                if (ObjectUtils.isEmpty(fragmentSummary)) {
                    fragmentSummary = DwsDailyPkgFragmentSummary.of(dates, dwdUserGameFragmentGoods.getPkg(),
                        dwdUserGameFragmentGoods.getGoods());
                }
                fragmentSummary.updateStatistics(dwdUserGameFragmentGoods.getNum());
                fragmentSummaryMap.put(dwdUserGameFragmentGoods.getGoods(), fragmentSummary);
                pkgFragmentSummaryMap.put(dwdUserGameFragmentGoods.getPkg(), fragmentSummaryMap);
            }
        }
        if (CollectionUtils.isEmpty(pkgFragmentSummaryMap)) {
            return;
        }
        // fragmentSummaryMap 转list
        List<DwsDailyPkgFragmentSummary> fragmentSummaryList =
            pkgFragmentSummaryMap.values().stream().flatMap(map -> map.values().stream()).collect(Collectors.toList());
        // 批量更新
        dwBatchMapper.batchInsert(fragmentSummaryList, DwsDailyPkgFragmentSummaryMapper.class);
        applicationEventPublisher.publishEvent(new DwsDailyPkgFragmentSummaryUpdateDwEvent(this, dates));

    }
}
