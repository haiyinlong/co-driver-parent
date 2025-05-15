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
import com.leo.ad.codriver.dwd.dao.DwdUserGameFragmentGoodsRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserGameFragmentGoodsRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgFragmentTransactionSummaryMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgFragmentTransactionSummary;
import com.leo.ad.codriver.dws.event.DwsDailyPkgFragmentTransactionSummaryUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * DwsDailyPkgFragmentTransactionSummaryServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/05/12 17:03
 **/
@Service
@AllArgsConstructor
public class DwsDailyPkgFragmentTransactionSummaryServiceImpl implements DwsService {

    private final DwsDailyPkgFragmentTransactionSummaryMapper dwsDailyPkgFragmentTransactionSummaryMapper;
    private final DwdUserGameFragmentGoodsRecordMapper dwdUserGameFragmentGoodsRecordMapper;
    private final DwBatchMapper<DwsDailyPkgFragmentTransactionSummary,
        DwsDailyPkgFragmentTransactionSummaryMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgTransactionFragmentSummary")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        DwsDailyPkgFragmentTransactionSummary fragmentSummary;
        Map<Integer, DwsDailyPkgFragmentTransactionSummary> fragmentSummaryMap = null;
        Map<String, Map<Integer, DwsDailyPkgFragmentTransactionSummary>> pkgFragmentSummaryMap = new HashMap<>();

        // 分页获取数据，然后进行汇总计算
        dwsDailyPkgFragmentTransactionSummaryMapper.deleteByDates(dates);
        DwCountDTO dwdCountDTO = dwdUserGameFragmentGoodsRecordMapper.getDwdFragmentRecordStatisticsCount(dates);
        int num = dwdCountDTO.loopNum();
        for (int i = 1; i <= num; i++) {
            List<DwdUserGameFragmentGoodsRecord> userGameGoodsList = dwdUserGameFragmentGoodsRecordMapper
                .queryDwdFragmentRecordInterval(dates, dwdCountDTO.loopStartId(i), dwdCountDTO.loopEndId(i));
            if (ObjectUtils.isEmpty(userGameGoodsList)) {
                continue;
            }
            // 汇总计算
            for (DwdUserGameFragmentGoodsRecord dwdUserGameFragmentGoodsRecord : userGameGoodsList) {
                fragmentSummaryMap =
                    pkgFragmentSummaryMap.getOrDefault(dwdUserGameFragmentGoodsRecord.getPkg(), new HashMap<>());
                fragmentSummary = fragmentSummaryMap.getOrDefault(dwdUserGameFragmentGoodsRecord.getGoods(),
                    DwsDailyPkgFragmentTransactionSummary.of(dates, dwdUserGameFragmentGoodsRecord.getPkg(),
                        dwdUserGameFragmentGoodsRecord.getGoods()));
                fragmentSummary.updateStatistics(dwdUserGameFragmentGoodsRecord.getRecordType(),
                    dwdUserGameFragmentGoodsRecord.getNum());
                fragmentSummaryMap.put(dwdUserGameFragmentGoodsRecord.getGoods(), fragmentSummary);
                pkgFragmentSummaryMap.put(dwdUserGameFragmentGoodsRecord.getPkg(), fragmentSummaryMap);
            }
        }
        if (CollectionUtils.isEmpty(pkgFragmentSummaryMap)) {
            return;
        }
        // fragmentSummaryMap 转list
        List<DwsDailyPkgFragmentTransactionSummary> fragmentSummaryList =
            pkgFragmentSummaryMap.values().stream().flatMap(map -> map.values().stream()).collect(Collectors.toList());
        // 批量更新
        dwBatchMapper.batchInsert(fragmentSummaryList, DwsDailyPkgFragmentTransactionSummaryMapper.class);
        applicationEventPublisher.publishEvent(new DwsDailyPkgFragmentTransactionSummaryUpdateDwEvent(this, dates));
    }
}
