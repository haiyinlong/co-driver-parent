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
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcAccumulateWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAccumulateWithdraw;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgVerUsrcAccumulateWithdrawServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/03/13 16:13
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcAccumulateWithdrawServiceImpl implements DwsService {
    private final DwsDailyPkgVerUsrcAccumulateWithdrawMapper dwsDailyPkgAccumulateWithdrawMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcAccumulateWithdraw,
        DwsDailyPkgVerUsrcAccumulateWithdrawMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Lock(paramName = "#dates")
    @ShowExecuteTime(name = "DwsDailyPkgVerUsrcAccumulateWithdraw")
    public void syncData(Integer dates) {
        // 获得昨天的数据
        Integer previousDate = DateUtils.getPreviousDate(dates, 1);
        List<DwsDailyPkgVerUsrcAccumulateWithdraw> previousDayList =
            dwsDailyPkgAccumulateWithdrawMapper.queryDates(previousDate);
        previousDayList = previousDayList.stream().filter(DwsDailyPkgVerUsrcAccumulateWithdraw::validate180Days)
            .map(previousDayWithdraw -> previousDayWithdraw.convertToday(dates)).toList();
        // 今天数据的数据和数据库已经存在的数据进行id合并
        List<DwsDailyPkgVerUsrcAccumulateWithdraw> todayList =
            dwsDailyPkgAccumulateWithdrawMapper.statisticsList(dates);
        List<DwsDailyPkgVerUsrcAccumulateWithdraw> todayHistoryList =
            dwsDailyPkgAccumulateWithdrawMapper.queryDates(dates);
        todayList = this.mergeTodayList(todayList, todayHistoryList);
        // 当日每日查询到数据就把昨天的数据进行保存
        if (CollectionUtils.isEmpty(todayList)) {
            dwBatchMapper.batchInsert(previousDayList, DwsDailyPkgVerUsrcAccumulateWithdrawMapper.class);
        }
        // 昨天的数据累加当日的数据
        Map<String, DwsDailyPkgVerUsrcAccumulateWithdraw> previousDayMap = previousDayList.stream()
            .collect(Collectors.toMap(DwsDailyPkgVerUsrcAccumulateWithdraw::getUniqueKey, Function.identity()));
        for (DwsDailyPkgVerUsrcAccumulateWithdraw todayWithdraw : todayList) {
            if (previousDayMap.containsKey(todayWithdraw.getUniqueKey())) {
                DwsDailyPkgVerUsrcAccumulateWithdraw accumulateWithdraw =
                    previousDayMap.get(todayWithdraw.getUniqueKey());
                todayWithdraw = accumulateWithdraw.calculateAccumulate(todayWithdraw);
            } else {
                todayWithdraw.calculateAccumulate();
            }
            previousDayMap.put(todayWithdraw.getUniqueKey(), todayWithdraw);
        }
        List<DwsDailyPkgVerUsrcAccumulateWithdraw> accumulatePkgAds = previousDayMap.values().stream().toList();
        dwBatchMapper.batchInsert(accumulatePkgAds, DwsDailyPkgVerUsrcAccumulateWithdrawMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(todayHistoryList, accumulatePkgAds);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgAccumulateWithdrawMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyPkgVerUsrcAccumulateWithdraw> mergeTodayList(
        List<DwsDailyPkgVerUsrcAccumulateWithdraw> todayList,
        List<DwsDailyPkgVerUsrcAccumulateWithdraw> todayHistoryList) {
        if (!CollectionUtils.isEmpty(todayHistoryList)) {
            Map<String, DwsDailyPkgVerUsrcAccumulateWithdraw> historyWithdrawMap = todayHistoryList.stream()
                .collect(Collectors.toMap(DwsDailyPkgVerUsrcAccumulateWithdraw::getUniqueKey, Function.identity()));
            Map<String, DwsDailyPkgVerUsrcAccumulateWithdraw> todayWithdrawMap = todayList.stream()
                .collect(Collectors.toMap(DwsDailyPkgVerUsrcAccumulateWithdraw::getUniqueKey, Function.identity()));
            historyWithdrawMap.forEach((key, value) -> {
                if (todayWithdrawMap.containsKey(key)) {
                    DwsDailyPkgVerUsrcAccumulateWithdraw todayWithdraw = todayWithdrawMap.get(key);
                    todayWithdraw.setId(value.getId());
                    todayWithdrawMap.put(key, todayWithdraw);
                }
            });
            return todayWithdrawMap.values().stream().toList();
        }
        return todayList;
    }
}
