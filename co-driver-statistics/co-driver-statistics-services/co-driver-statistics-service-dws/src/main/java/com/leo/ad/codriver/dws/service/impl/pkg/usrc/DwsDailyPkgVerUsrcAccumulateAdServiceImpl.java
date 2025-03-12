package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserAdRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcAccumulateAdMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAccumulateAd;
import com.leo.ad.codriver.dws.service.DwsTestService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description DwsRegister90DaysAccumulatePkgVerUsrcAdServiceImpl 处理注册90天累计用户广告数据
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcAccumulateAdServiceImpl implements DwsTestService {
    private final DwdUserAdRecordMapper dwdUserAdRecordMapper;
    private final DwsDailyPkgVerUsrcAccumulateAdMapper dwsRegister90DaysAccumulatePkgUsrcAdMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcAccumulateAd, DwsDailyPkgVerUsrcAccumulateAdMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * 1) 针对当前的数据进行汇总 <br>
     * 2) 获取前一天的所有数据，生成当日的数据<br>
     * 3) 基于1生成的数据 在2 的基础上进行累加；<br>
     * 4) 入库当天数据
     *
     * @param dates 20241010 日期
     */
    @Override
    @Lock(paramName = "#dates")
    @ShowExecuteTime(name = "DwsRegister90DaysAccumulatePkgVerUsrcAd")
    public void syncData(Integer dates) {
        // 批量处理，一条一条的从数据库中获取与内存中的数据近汇总
        DwCountDTO dbCount = dwdUserAdRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyPkgAdvertising {} 统计数据为空，跳过处理", dates);
            return;
        }

        try {
            Long minId = dbCount.getMinId();
            Long endId;
            Long maxId = dbCount.getMaxId() + 1;
            Map<String, DwsDailyPkgVerUsrcAccumulateAd> todayPkgVersionAdMap = new HashMap<>();
            List<DwdUserAdRecord> dbList;
            do {
                endId = minId + BatchConst.BATCH_MAX_NUMBER;
                if (endId > maxId) {
                    endId = maxId;
                }
                dbList = dwdUserAdRecordMapper.queryDbActiveListByDate(dates, minId, endId);
                minId += BatchConst.BATCH_MAX_NUMBER;
                if (CollectionUtils.isEmpty(dbList)) {
                    continue;
                }
                String uniqueKey;
                for (DwdUserAdRecord dwdUserAdRecord : dbList) {
                    uniqueKey = DwsDailyPkgVerUsrcAccumulateAd.getPkgAdUniqueKey(dwdUserAdRecord);
                    // 创建一个临时对象，用于存储数据. 包、版本
                    DwsDailyPkgVerUsrcAccumulateAd ad = todayPkgVersionAdMap.getOrDefault(uniqueKey,
                        DwsDailyPkgVerUsrcAccumulateAd.of(dwdUserAdRecord.getDates(), dwdUserAdRecord.getPkg(),
                            dwdUserAdRecord.getVersion(), dwdUserAdRecord.getUserSource(),
                            dwdUserAdRecord.getRegisterDates(), dwdUserAdRecord.getRegisterDay()));
                    // 缓存各个统计维度的用户数量
                    ad.calculateToday(dwdUserAdRecord);
                    todayPkgVersionAdMap.put(uniqueKey, ad);
                }
            } while (minId <= maxId);
            // 获取前一天的所有数据，生成当日的数据
            Integer previousDate = DateUtils.getPreviousDate(dates, 1);
            List<DwsDailyPkgVerUsrcAccumulateAd> previousDayList =
                dwsRegister90DaysAccumulatePkgUsrcAdMapper.queryDbList(previousDate);
            // 转化为当天的数据，清空id;
            List<DwsDailyPkgVerUsrcAccumulateAd> yesterdayToTodayList =
                previousDayList.stream().map(previousPkgAd -> previousPkgAd.convertToday(dates)).toList();
            // 转集合
            List<DwsDailyPkgVerUsrcAccumulateAd> todayHistroyList =
                dwsRegister90DaysAccumulatePkgUsrcAdMapper.queryDbList(dates);
            List<DwsDailyPkgVerUsrcAccumulateAd> datesPkgAdList =
                this.mergeTodayList(todayHistroyList, todayPkgVersionAdMap);
            if (CollectionUtils.isEmpty(datesPkgAdList)) {
                // 当天没有数据也要保存记录
                dwBatchMapper.batchInsert(yesterdayToTodayList, DwsDailyPkgVerUsrcAccumulateAdMapper.class);
                return;
            }
            Map<String, DwsDailyPkgVerUsrcAccumulateAd> todayMap = yesterdayToTodayList.stream()
                .collect(Collectors.toMap(DwsDailyPkgVerUsrcAccumulateAd::getPkgAdUniqueKey, Function.identity()));

            for (DwsDailyPkgVerUsrcAccumulateAd dwsRegister90DaysAccumulatePkgAd : datesPkgAdList) {
                if (todayMap.containsKey(dwsRegister90DaysAccumulatePkgAd.getPkgAdUniqueKey())) {
                    DwsDailyPkgVerUsrcAccumulateAd aDefault = todayMap.getOrDefault(
                        dwsRegister90DaysAccumulatePkgAd.getPkgAdUniqueKey(), dwsRegister90DaysAccumulatePkgAd);
                    dwsRegister90DaysAccumulatePkgAd = aDefault.calculateAccumulate(dwsRegister90DaysAccumulatePkgAd);
                } else {
                    dwsRegister90DaysAccumulatePkgAd.calculateAccumulate(dwsRegister90DaysAccumulatePkgAd);
                }
                todayMap.put(dwsRegister90DaysAccumulatePkgAd.getPkgAdUniqueKey(), dwsRegister90DaysAccumulatePkgAd);
            }
            List<DwsDailyPkgVerUsrcAccumulateAd> accumulatePkgAds = todayMap.values().stream().toList();
            dwBatchMapper.batchInsert(accumulatePkgAds, DwsDailyPkgVerUsrcAccumulateAdMapper.class);
            // 删除没用的数据
            List<Long> delIds = getDelIds(todayHistroyList, datesPkgAdList);
            if (!CollectionUtils.isEmpty(delIds)) {
                dwsRegister90DaysAccumulatePkgUsrcAdMapper.deleteBatchIds(delIds);
            }
        } catch (Throwable e) {
            log.error("DwsDailyPkgAdvertising " + dates + "异常", e);
            throw new RuntimeException(e);
        }

    }

    private List<DwsDailyPkgVerUsrcAccumulateAd> mergeTodayList(List<DwsDailyPkgVerUsrcAccumulateAd> dbActiveList,
        Map<String, DwsDailyPkgVerUsrcAccumulateAd> pkgVerAdMap) {
        if (!CollectionUtils.isEmpty(dbActiveList)) {
            Map<String, DwsDailyPkgVerUsrcAccumulateAd> dbMap = dbActiveList.stream()
                .collect(Collectors.toMap(DwsDailyPkgVerUsrcAccumulateAd::getPkgAdUniqueKey, Function.identity()));
            dbMap.forEach((key, value) -> {
                if (pkgVerAdMap.containsKey(key)) {
                    DwsDailyPkgVerUsrcAccumulateAd dwsDailyPkgVerAdvertising = pkgVerAdMap.get(key);
                    dwsDailyPkgVerAdvertising.setId(value.getId());
                    pkgVerAdMap.put(key, dwsDailyPkgVerAdvertising);
                }
            });
        }
        return new ArrayList<>(pkgVerAdMap.values());
    }
}
