package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserWithdrawRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserWithdrawRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyCohortPkgWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgWithdraw;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_cohort_pkg_withdraw】的数据库操作Service实现
 * @createDate 2025-06-26 14:47:24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyCohortPkgWithdrawServiceImpl implements DwsService {
    private final DwdUserWithdrawRecordMapper dwdUserWithdrawRecordMapper;
    private final DwsDailyCohortPkgWithdrawMapper dwsDailyCohortPkgWithdrawMapper;
    private final DwBatchMapper<DwsDailyCohortPkgWithdraw, DwsDailyCohortPkgWithdrawMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyCohortPkgWithdrawServiceImpl")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        // 查询统计数据
        DwCountDTO dbCount = dwdUserWithdrawRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyCohortPkgWithdraw {} 统计数据为空，跳过处理", dates);
            return;
        }

        Map<String, DwsDailyCohortPkgWithdraw> cohortPkgUsrcAdMap = getStatisticsDataMap(dates, dbCount);
        if (CollectionUtils.isEmpty(cohortPkgUsrcAdMap)) {
            return;
        }

        List<DwsDailyCohortPkgWithdraw> dbList = dwsDailyCohortPkgWithdrawMapper.queryDbListByDates(dates);
        List<DwsDailyCohortPkgWithdraw> newCohortPkgUsrcList = this.setDbIdToNewList(dbList, cohortPkgUsrcAdMap);
        dwBatchMapper.batchInsert(newCohortPkgUsrcList, DwsDailyCohortPkgWithdrawMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(dbList, newCohortPkgUsrcList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyCohortPkgWithdrawMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyCohortPkgWithdraw> setDbIdToNewList(List<DwsDailyCohortPkgWithdraw> dbActiveList,
        Map<String, DwsDailyCohortPkgWithdraw> pkgVerAdMap) {
        if (!CollectionUtils.isEmpty(dbActiveList)) {
            Map<String, DwsDailyCohortPkgWithdraw> dbMap =
                dbActiveList.stream().collect(Collectors.toMap(this::uniqueDbKey, Function.identity()));
            dbMap.forEach((key, value) -> {
                if (pkgVerAdMap.containsKey(key)) {
                    DwsDailyCohortPkgWithdraw dwsDailyCohortPkgVerAdvertising = pkgVerAdMap.get(key);
                    dwsDailyCohortPkgVerAdvertising.setId(value.getId());
                    pkgVerAdMap.put(key, dwsDailyCohortPkgVerAdvertising);
                }
            });
        }
        return new ArrayList<>(pkgVerAdMap.values());
    }

    private Map<String, DwsDailyCohortPkgWithdraw> getStatisticsDataMap(Integer dates, DwCountDTO dbCount) {
        Long minId = dbCount.getMinId();
        Long endId;
        Long maxId = dbCount.getMaxId() + 1;
        Map<String, DwsDailyCohortPkgWithdraw> cohortPkgUsrcAdMap = new HashMap<>();
        List<DwdUserWithdrawRecord> dbList;
        do {
            endId = minId + 10000;
            if (endId > maxId) {
                endId = maxId;
            }
            dbList = dwdUserWithdrawRecordMapper.queryListByDates(dates, minId, endId);
            minId += 10000;
            if (CollectionUtils.isEmpty(dbList)) {
                continue;
            }
            for (DwdUserWithdrawRecord dwdUserWithdrawRecord : dbList) {
                // 获取唯一key
                String uniqueKey = uniqueKey(dwdUserWithdrawRecord);
                DwsDailyCohortPkgWithdraw dwsDailyCohortPkgWithdraw = cohortPkgUsrcAdMap.getOrDefault(uniqueKey,
                    DwsDailyCohortPkgWithdraw.of(dwdUserWithdrawRecord.getDates(),
                        dwdUserWithdrawRecord.getRegisterDates(), dwdUserWithdrawRecord.getRegisterWithdrawDay(),
                        dwdUserWithdrawRecord.getPkg()));
                // 缓存各个统计维度的用户数量
                dwsDailyCohortPkgWithdraw.calculate(dwdUserWithdrawRecord);
                cohortPkgUsrcAdMap.put(uniqueKey, dwsDailyCohortPkgWithdraw);
            }
        } while (minId <= maxId);
        return cohortPkgUsrcAdMap;
    }

    private String uniqueKey(DwdUserWithdrawRecord dwdUserWithdrawRecord) {
        return dwdUserWithdrawRecord.getPkg() + "_" + dwdUserWithdrawRecord.getRegisterDates() + "_"
            + dwdUserWithdrawRecord.getRegisterWithdrawDay();
    }

    private String uniqueDbKey(DwsDailyCohortPkgWithdraw dwsDailyCohortPkgWithdraw) {
        return dwsDailyCohortPkgWithdraw.getPkg() + "_" + dwsDailyCohortPkgWithdraw.getRegisterDates() + "_"
            + dwsDailyCohortPkgWithdraw.getCohortDay();
    }
}
