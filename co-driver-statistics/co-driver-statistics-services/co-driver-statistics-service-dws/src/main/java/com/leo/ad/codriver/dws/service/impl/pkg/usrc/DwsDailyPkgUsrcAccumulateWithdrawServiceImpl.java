package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcAccumulateWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcAccumulateWithdraw;
import com.leo.ad.codriver.dws.service.DwsTestService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgUsrcAccumulateWithdrawServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/03/13 16:05
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcAccumulateWithdrawServiceImpl implements DwsTestService {
    private final DwsDailyPkgUsrcAccumulateWithdrawMapper dwsDailyPkgAccumulateWithdrawMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcAccumulateWithdraw,
        DwsDailyPkgUsrcAccumulateWithdrawMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Lock(paramName = "#dates")
    @ShowExecuteTime(name = "DwsDailyPkgUsrcAccumulateWithdraw")
    public void syncData(Integer dates) {
        // 获得昨天的数据
        Integer previousDate = DateUtils.getPreviousDate(dates, 1);
        List<DwsDailyPkgUsrcAccumulateWithdraw> previousDayList =
            dwsDailyPkgAccumulateWithdrawMapper.queryDates(previousDate);
        previousDayList = previousDayList.stream().filter(DwsDailyPkgUsrcAccumulateWithdraw::validate180Days)
            .map(previousDayWithdraw -> previousDayWithdraw.convertToday(dates)).toList();
        // 今天数据的数据和数据库已经存在的数据进行id合并
        List<DwsDailyPkgUsrcAccumulateWithdraw> todayList = dwsDailyPkgAccumulateWithdrawMapper.statisticsList(dates);
        List<DwsDailyPkgUsrcAccumulateWithdraw> todayHistoryList =
            dwsDailyPkgAccumulateWithdrawMapper.queryDates(dates);
        todayList = this.mergeTodayList(todayList, todayHistoryList);
        // 当日每日查询到数据就把昨天的数据进行保存
        if (CollectionUtils.isEmpty(todayList)) {
            dwBatchMapper.batchInsert(previousDayList, DwsDailyPkgUsrcAccumulateWithdrawMapper.class);
        }
        // 昨天的数据累加当日的数据
        Map<String, DwsDailyPkgUsrcAccumulateWithdraw> previousDayMap = previousDayList.stream()
            .collect(Collectors.toMap(DwsDailyPkgUsrcAccumulateWithdraw::getUniqueKey, Function.identity()));
        for (DwsDailyPkgUsrcAccumulateWithdraw todayWithdraw : todayList) {
            if (previousDayMap.containsKey(todayWithdraw.getUniqueKey())) {
                DwsDailyPkgUsrcAccumulateWithdraw accumulateWithdraw = previousDayMap.get(todayWithdraw.getUniqueKey());
                todayWithdraw = accumulateWithdraw.calculateAccumulate(todayWithdraw);
            } else {
                todayWithdraw.calculateAccumulate();
            }
            previousDayMap.put(todayWithdraw.getUniqueKey(), todayWithdraw);
        }
        List<DwsDailyPkgUsrcAccumulateWithdraw> accumulatePkgAds = previousDayMap.values().stream().toList();
        dwBatchMapper.batchInsert(accumulatePkgAds, DwsDailyPkgUsrcAccumulateWithdrawMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(todayHistoryList, accumulatePkgAds);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgAccumulateWithdrawMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyPkgUsrcAccumulateWithdraw> mergeTodayList(List<DwsDailyPkgUsrcAccumulateWithdraw> todayList,
        List<DwsDailyPkgUsrcAccumulateWithdraw> todayHistoryList) {
        if (!CollectionUtils.isEmpty(todayHistoryList)) {
            Map<String, DwsDailyPkgUsrcAccumulateWithdraw> historyWithdrawMap = todayHistoryList.stream()
                .collect(Collectors.toMap(DwsDailyPkgUsrcAccumulateWithdraw::getUniqueKey, Function.identity()));
            Map<String, DwsDailyPkgUsrcAccumulateWithdraw> todayWithdrawMap = todayList.stream()
                .collect(Collectors.toMap(DwsDailyPkgUsrcAccumulateWithdraw::getUniqueKey, Function.identity()));
            historyWithdrawMap.forEach((key, value) -> {
                if (todayWithdrawMap.containsKey(key)) {
                    DwsDailyPkgUsrcAccumulateWithdraw todayWithdraw = todayWithdrawMap.get(key);
                    todayWithdraw.setId(value.getId());
                    todayWithdrawMap.put(key, todayWithdraw);
                }
            });
            return todayWithdrawMap.values().stream().toList();
        }
        return todayList;
    }
}
