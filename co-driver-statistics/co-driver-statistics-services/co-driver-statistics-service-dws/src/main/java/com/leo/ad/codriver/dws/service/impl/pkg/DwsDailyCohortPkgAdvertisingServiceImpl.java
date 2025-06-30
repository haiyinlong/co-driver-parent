package com.leo.ad.codriver.dws.service.impl.pkg;

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
import com.leo.ad.codriver.dwd.dao.DwdUserAdRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyCohortPkgAdvertisingMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgAdvertising;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_cohort_pkg_advertising】的数据库操作Service实现
 * @createDate 2025-06-26 14:47:24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyCohortPkgAdvertisingServiceImpl implements DwsService {
    private final DwsDailyCohortPkgAdvertisingMapper dwsDailyCohortPkgAdvertisingMapper;
    private final DwdUserAdRecordMapper dwdUserAdRecordMapper;
    private final DwBatchMapper<DwsDailyCohortPkgAdvertising, DwsDailyCohortPkgAdvertisingMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Lock(paramName = "#dates")
    @ShowExecuteTime(name = "DwsDailyCohortPkgAdvertising")
    public void syncData(Integer dates) {
        // 批量处理，一条一条的从数据库中获取与内存中的数据近汇总
        DwCountDTO dbCount = dwdUserAdRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyCohortPkgAdvertising {} 统计数据为空，跳过处理", dates);
            return;
        }

        try {
            Map<String, DwsDailyCohortPkgAdvertising> cohortPkgUsrcAdMap = handelCohortData(dates, dbCount);
            if (!CollectionUtils.isEmpty(cohortPkgUsrcAdMap)) {
                List<DwsDailyCohortPkgAdvertising> dbActiveList =
                    dwsDailyCohortPkgAdvertisingMapper.queryDbListByDates(dates);
                List<DwsDailyCohortPkgAdvertising> verAdvertisingList =
                    this.setDbIdToNewList(dbActiveList, cohortPkgUsrcAdMap);
                dwBatchMapper.batchInsert(verAdvertisingList, DwsDailyCohortPkgAdvertisingMapper.class);
                // 删除没用的数据
                List<Long> delIds = getDelIds(dbActiveList, verAdvertisingList);
                if (!CollectionUtils.isEmpty(delIds)) {
                    dwsDailyCohortPkgAdvertisingMapper.deleteBatchIds(delIds);
                }
            }
        } catch (Throwable e) {
            log.error("DwsDailyCohortPkgAdvertising " + dates + "异常", e);
            throw new RuntimeException(e);
        }
    }

    private Map<String, DwsDailyCohortPkgAdvertising> handelCohortData(Integer dates, DwCountDTO dbCount) {
        Long minId = dbCount.getMinId();
        Long endId;
        Long maxId = dbCount.getMaxId() + 1;
        Map<String, DwsDailyCohortPkgAdvertising> cohortPkgUsrcAdMap = new HashMap<>();
        List<DwdUserAdRecord> dbList;
        do {
            endId = minId + 10000;
            if (endId > maxId) {
                endId = maxId;
            }
            dbList = dwdUserAdRecordMapper.queryDbActiveListByDateUsrc(dates, minId, endId);
            minId += 10000;
            if (CollectionUtils.isEmpty(dbList)) {
                continue;
            }
            for (DwdUserAdRecord dwdUserAdRecord : dbList) {
                // 获取唯一key
                String uniqueKey = uniqueKey(dwdUserAdRecord);
                DwsDailyCohortPkgAdvertising ad = cohortPkgUsrcAdMap.getOrDefault(uniqueKey,
                    DwsDailyCohortPkgAdvertising.of(dwdUserAdRecord.getDates(), dwdUserAdRecord.getRegisterDates(),
                        dwdUserAdRecord.getRegisterDay(), dwdUserAdRecord.getPkg()));
                // 缓存各个统计维度的用户数量
                ad.calculate(dwdUserAdRecord);
                cohortPkgUsrcAdMap.put(uniqueKey, ad);
            }
        } while (minId <= maxId);
        return cohortPkgUsrcAdMap;
    }

    private List<DwsDailyCohortPkgAdvertising> setDbIdToNewList(List<DwsDailyCohortPkgAdvertising> dbActiveList,
        Map<String, DwsDailyCohortPkgAdvertising> pkgVerAdMap) {
        if (!CollectionUtils.isEmpty(dbActiveList)) {
            Map<String, DwsDailyCohortPkgAdvertising> dbMap =
                dbActiveList.stream().collect(Collectors.toMap(this::uniqueDbKey, Function.identity()));
            dbMap.forEach((key, value) -> {
                if (pkgVerAdMap.containsKey(key)) {
                    DwsDailyCohortPkgAdvertising dwsDailyCohortPkgVerAdvertising = pkgVerAdMap.get(key);
                    dwsDailyCohortPkgVerAdvertising.setId(value.getId());
                    pkgVerAdMap.put(key, dwsDailyCohortPkgVerAdvertising);
                }
            });
        }
        return new ArrayList<>(pkgVerAdMap.values());
    }

    private String uniqueKey(DwdUserAdRecord dwdUserAdRecord) {
        return dwdUserAdRecord.getPkg() + "_" + dwdUserAdRecord.getRegisterDates() + "_"
            + dwdUserAdRecord.getRegisterDay();
    }

    private String uniqueDbKey(DwsDailyCohortPkgAdvertising dwsDailyCohortPkgAdvertising) {
        return dwsDailyCohortPkgAdvertising.getPkg() + "_" + dwsDailyCohortPkgAdvertising.getRegisterDates() + "_"
            + dwsDailyCohortPkgAdvertising.getCohortDay();
    }
}
