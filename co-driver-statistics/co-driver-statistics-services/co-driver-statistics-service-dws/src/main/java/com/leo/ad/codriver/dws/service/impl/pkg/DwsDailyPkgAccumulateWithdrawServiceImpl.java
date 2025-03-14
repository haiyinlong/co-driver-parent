package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgAccumulateWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgAccumulateWithdraw;
import com.leo.ad.codriver.dws.service.DwsTestService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgAccumulateWithdrawServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/03/13 14:07
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgAccumulateWithdrawServiceImpl implements DwsTestService {
    private final DwsDailyPkgAccumulateWithdrawMapper dwsDailyPkgAccumulateWithdrawMapper;
    private final DwBatchMapper<DwsDailyPkgAccumulateWithdraw, DwsDailyPkgAccumulateWithdrawMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Lock(paramName = "#dates")
    @ShowExecuteTime(name = "DwsDailyPkgAccumulateWithdraw")
    public void syncData(Integer dates) {
        // 获得昨天的数据
        Integer previousDate = DateUtils.getPreviousDate(dates, 1);
        List<DwsDailyPkgAccumulateWithdraw> previousDayList =
            dwsDailyPkgAccumulateWithdrawMapper.queryDates(previousDate);
        previousDayList = previousDayList.stream().filter(DwsDailyPkgAccumulateWithdraw::validate180Days)
            .map(previousDayWithdraw -> previousDayWithdraw.convertToday(dates)).toList();
        // 今天数据的数据和数据库已经存在的数据进行id合并
        List<DwsDailyPkgAccumulateWithdraw> todayList = dwsDailyPkgAccumulateWithdrawMapper.statisticsList(dates);
        List<DwsDailyPkgAccumulateWithdraw> todayHistoryList = dwsDailyPkgAccumulateWithdrawMapper.queryDates(dates);
        todayList = this.mergeTodayList(todayList, todayHistoryList);
        // 当日每日查询到数据就把昨天的数据进行保存
        if (CollectionUtils.isEmpty(todayList)) {
            dwBatchMapper.batchInsert(previousDayList, DwsDailyPkgAccumulateWithdrawMapper.class);
        }
        // 昨天的数据累加当日的数据
        Map<String, DwsDailyPkgAccumulateWithdraw> previousDayMap = previousDayList.stream()
            .collect(Collectors.toMap(DwsDailyPkgAccumulateWithdraw::getUniqueKey, Function.identity()));
        for (DwsDailyPkgAccumulateWithdraw todayWithdraw : todayList) {
            if (previousDayMap.containsKey(todayWithdraw.getUniqueKey())) {
                DwsDailyPkgAccumulateWithdraw accumulateWithdraw = previousDayMap.get(todayWithdraw.getUniqueKey());
                todayWithdraw = accumulateWithdraw.calculateAccumulate(todayWithdraw);
            } else {
                todayWithdraw.calculateAccumulate();
            }
            previousDayMap.put(todayWithdraw.getUniqueKey(), todayWithdraw);
        }
        List<DwsDailyPkgAccumulateWithdraw> accumulatePkgAds = previousDayMap.values().stream().toList();
        dwBatchMapper.batchInsert(accumulatePkgAds, DwsDailyPkgAccumulateWithdrawMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(todayHistoryList, accumulatePkgAds);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgAccumulateWithdrawMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyPkgAccumulateWithdraw> mergeTodayList(List<DwsDailyPkgAccumulateWithdraw> todayList,
        List<DwsDailyPkgAccumulateWithdraw> todayHistoryList) {
        if (!CollectionUtils.isEmpty(todayHistoryList)) {
            Map<String, DwsDailyPkgAccumulateWithdraw> historyWithdrawMap = todayHistoryList.stream()
                .collect(Collectors.toMap(DwsDailyPkgAccumulateWithdraw::getUniqueKey, Function.identity()));
            Map<String, DwsDailyPkgAccumulateWithdraw> todayWithdrawMap = todayList.stream()
                .collect(Collectors.toMap(DwsDailyPkgAccumulateWithdraw::getUniqueKey, Function.identity()));
            historyWithdrawMap.forEach((key, value) -> {
                if (todayWithdrawMap.containsKey(key)) {
                    DwsDailyPkgAccumulateWithdraw todayWithdraw = todayWithdrawMap.get(key);
                    todayWithdraw.setId(value.getId());
                    todayWithdrawMap.put(key, todayWithdraw);
                }
            });
            return todayWithdrawMap.values().stream().toList();
        }
        return todayList;
    }
}
