package com.leo.ad.codriver.ads.service.useranalyse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.ads.dao.AdsDailyUserQualityAnalyseMapper;
import com.leo.ad.codriver.ads.dao.TempDailyUserQualityAnalyseMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyUserQualityAnalyse;
import com.leo.ad.codriver.ads.entity.TempDailyUserQualityAnalyse;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * CohortUserAnalysePkgUsrcReportService
 *
 * @author HaiYinLong
 * @version 2025/06/27 18:35
 **/
@Service
@RequiredArgsConstructor
public class CohortUserAnalysePkgUsrcReportService {
    private final AdsDailyUserQualityAnalyseMapper adsDailyUserQualityAnalyseMapper;
    private final TempDailyUserQualityAnalyseMapper tmpDailyUserQualityAnalyseMapper;
    private final DwBatchMapper<AdsDailyUserQualityAnalyse, AdsDailyUserQualityAnalyseMapper> batchMapper;
    private final DwBatchMapper<TempDailyUserQualityAnalyse, TempDailyUserQualityAnalyseMapper> tempBatchMapper;

    public void syncData(Integer dates) {
        // 创建和更新数据
        List<AdsDailyUserQualityAnalyse> list =
            adsDailyUserQualityAnalyseMapper.queryCohortUserAnalysePkgUsrcReportList(dates);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list = resetCohortData(list);
        batchMapper.batchInsert(list, AdsDailyUserQualityAnalyseMapper.class);
        // 写入到临时表，先删除临时表数据
        tmpDailyUserQualityAnalyseMapper.cleanTempTable(dates);
        // 计算同期群1-7的数据
        List<TempDailyUserQualityAnalyse> day1To7List =
            adsDailyUserQualityAnalyseMapper.queryPkgUsrcCohortD1ToD7(dates);
        tempBatchMapper.batchInsert(day1To7List, TempDailyUserQualityAnalyseMapper.class);
        // 计算同期群8-14的数据
        List<TempDailyUserQualityAnalyse> day8To14List =
            adsDailyUserQualityAnalyseMapper.queryPkgUsrcCohortD8ToD14(dates);
        tempBatchMapper.batchInsert(day8To14List, TempDailyUserQualityAnalyseMapper.class);
        // 计算同期群15-30的数据
        List<TempDailyUserQualityAnalyse> day15To30List =
            adsDailyUserQualityAnalyseMapper.queryPkgUsrcCohortD15ToD30(dates);
        tempBatchMapper.batchInsert(day15To30List, TempDailyUserQualityAnalyseMapper.class);
        Map<String, TempDailyUserQualityAnalyse> cohortUserQualityDtoMap = getCohortUserQualityDtoMap(day15To30List);
        // 装载数据
        list.forEach(item -> {
            String uniqueKey = getUniqueKey(item);
            if (cohortUserQualityDtoMap.containsKey(uniqueKey)) {
                item.updateDay1Day3Day7(cohortUserQualityDtoMap.get(uniqueKey));
                item.updateDay14(cohortUserQualityDtoMap.get(uniqueKey));
                item.updateDay30(cohortUserQualityDtoMap.get(uniqueKey));
            }
        });
        batchMapper.batchInsert(list, AdsDailyUserQualityAnalyseMapper.class);
    }

    private Map<String, TempDailyUserQualityAnalyse>
        getCohortUserQualityDtoMap(List<TempDailyUserQualityAnalyse> day1To7List) {
        return day1To7List.stream().collect(Collectors.toMap(this::getUniqueKey, Function.identity()));
    }

    private List<AdsDailyUserQualityAnalyse> resetCohortData(List<AdsDailyUserQualityAnalyse> list) {
        LocalDateTime now = LocalDateTime.now();
        list.forEach(item -> {
            item.resetCohortData(now);
        });
        return list;
    }

    private String getUniqueKey(TempDailyUserQualityAnalyse tempDailyUserQualityAnalyse) {
        return tempDailyUserQualityAnalyse.getPkg() + "_" + tempDailyUserQualityAnalyse.getUserSource();
    }

    private String getUniqueKey(AdsDailyUserQualityAnalyse userQualityAnalyse) {
        return userQualityAnalyse.getPkg() + "_" + userQualityAnalyse.getUserSource();
    }
}
