package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerAccumulateWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerAccumulateWithdraw;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgVerAccumulateWithdrawServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/03/13 15:51
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerAccumulateWithdrawServiceImpl implements DwsService {
    private final DwsDailyPkgVerAccumulateWithdrawMapper dwsDailyPkgAccumulateWithdrawMapper;
    private final DwBatchMapper<DwsDailyPkgVerAccumulateWithdraw, DwsDailyPkgVerAccumulateWithdrawMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Lock(paramName = "#dates")
    @ShowExecuteTime(name = "DwsDailyPkgVerAccumulateWithdraw")
    public void syncData(Integer dates) {
        // 获得昨天的数据
        Integer previousDate = DateUtils.getPreviousDate(dates, 1);
        List<DwsDailyPkgVerAccumulateWithdraw> previousDayList =
            dwsDailyPkgAccumulateWithdrawMapper.queryDates(previousDate);
        previousDayList = previousDayList.stream().filter(DwsDailyPkgVerAccumulateWithdraw::validate180Days)
            .map(previousDayWithdraw -> previousDayWithdraw.convertToday(dates)).toList();
        // 今天数据的数据和数据库已经存在的数据进行id合并
        List<DwsDailyPkgVerAccumulateWithdraw> todayList = dwsDailyPkgAccumulateWithdrawMapper.statisticsList(dates);
        List<DwsDailyPkgVerAccumulateWithdraw> todayHistoryList = dwsDailyPkgAccumulateWithdrawMapper.queryDates(dates);
        todayList = this.mergeTodayList(todayList, todayHistoryList);
        // 当日每日查询到数据就把昨天的数据进行保存
        if (CollectionUtils.isEmpty(todayList)) {
            dwBatchMapper.batchInsert(previousDayList, DwsDailyPkgVerAccumulateWithdrawMapper.class);
        }
        // 昨天的数据累加当日的数据
        Map<String, DwsDailyPkgVerAccumulateWithdraw> previousDayMap = previousDayList.stream()
            .collect(Collectors.toMap(DwsDailyPkgVerAccumulateWithdraw::getUniqueKey, Function.identity()));
        for (DwsDailyPkgVerAccumulateWithdraw todayWithdraw : todayList) {
            if (previousDayMap.containsKey(todayWithdraw.getUniqueKey())) {
                DwsDailyPkgVerAccumulateWithdraw accumulateWithdraw = previousDayMap.get(todayWithdraw.getUniqueKey());
                todayWithdraw = accumulateWithdraw.calculateAccumulate(todayWithdraw);
            } else {
                todayWithdraw.calculateAccumulate();
            }
            previousDayMap.put(todayWithdraw.getUniqueKey(), todayWithdraw);
        }
        List<DwsDailyPkgVerAccumulateWithdraw> accumulatePkgAds = previousDayMap.values().stream().toList();
        dwBatchMapper.batchInsert(accumulatePkgAds, DwsDailyPkgVerAccumulateWithdrawMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(todayHistoryList, accumulatePkgAds);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgAccumulateWithdrawMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyPkgVerAccumulateWithdraw> mergeTodayList(List<DwsDailyPkgVerAccumulateWithdraw> todayList,
        List<DwsDailyPkgVerAccumulateWithdraw> todayHistoryList) {
        if (!CollectionUtils.isEmpty(todayHistoryList)) {
            Map<String, DwsDailyPkgVerAccumulateWithdraw> historyWithdrawMap = todayHistoryList.stream()
                .collect(Collectors.toMap(DwsDailyPkgVerAccumulateWithdraw::getUniqueKey, Function.identity()));
            Map<String, DwsDailyPkgVerAccumulateWithdraw> todayWithdrawMap = todayList.stream()
                .collect(Collectors.toMap(DwsDailyPkgVerAccumulateWithdraw::getUniqueKey, Function.identity()));
            historyWithdrawMap.forEach((key, value) -> {
                if (todayWithdrawMap.containsKey(key)) {
                    DwsDailyPkgVerAccumulateWithdraw todayWithdraw = todayWithdrawMap.get(key);
                    todayWithdraw.setId(value.getId());
                    todayWithdrawMap.put(key, todayWithdraw);
                }
            });
            return todayWithdrawMap.values().stream().toList();
        }
        return todayList;
    }
}
