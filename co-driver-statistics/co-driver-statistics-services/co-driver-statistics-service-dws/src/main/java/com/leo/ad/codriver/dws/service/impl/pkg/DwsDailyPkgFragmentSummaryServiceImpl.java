package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserGameGoodsMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserGameGoods;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgFragmentSummaryMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgFragmentSummary;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * DwsDailyPkgFragmentSummaryServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/05/12 15:05
 **/
@Service
@AllArgsConstructor
public class DwsDailyPkgFragmentSummaryServiceImpl implements DwsService {

    private final DwsDailyPkgFragmentSummaryMapper dwsDailyPkgFragmentSummaryMapper;
    private final DwdUserGameGoodsMapper dwdUserGameGoodsMapper;
    private final DwBatchMapper<DwsDailyPkgFragmentSummary, DwsDailyPkgFragmentSummaryMapper> dwBatchMapper;

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
        DwCountDTO dwdCountDTO = dwdUserGameGoodsMapper.getDwdFragmentStatisticsCount(dates);
        int num = dwdCountDTO.loopNum();
        for (int i = 0; i < num; i++) {
            List<DwdUserGameGoods> userGameGoodsList =
                dwdUserGameGoodsMapper.queryDwdFragmentInterval(dwdCountDTO.loopStartId(i), dwdCountDTO.loopEndId(i));
            if (ObjectUtils.isEmpty(userGameGoodsList)) {
                continue;
            }
            // 汇总计算
            for (DwdUserGameGoods dwdUserGameGoods : userGameGoodsList) {
                fragmentSummaryMap = pkgFragmentSummaryMap.getOrDefault(dwdUserGameGoods.getPkg(), new HashMap<>());
                fragmentSummary = fragmentSummaryMap.getOrDefault(dwdUserGameGoods.getGoods(),
                    DwsDailyPkgFragmentSummary.of(dates, dwdUserGameGoods.getGoods()));
                fragmentSummary.updateStatistics(dwdUserGameGoods.getNum());
                fragmentSummaryMap.put(dwdUserGameGoods.getGoods(), fragmentSummary);
                pkgFragmentSummaryMap.put(dwdUserGameGoods.getPkg(), fragmentSummaryMap);
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
    }
}
