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
import com.leo.ad.codriver.dwd.dao.DwdUserAdRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyCohortPkgUsrcAdvertisingMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgUsrcAdvertising;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 同期群广告数据，每天0点
 *
 * @author user
 * @createDate 2025-06-26 14:47:24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyCohortPkgUsrcAdvertisingServiceImpl implements DwsService {
    private final DwsDailyCohortPkgUsrcAdvertisingMapper dwsDailyCohortPkgUsrcAdvertisingMapper;
    private final DwdUserAdRecordMapper dwdUserAdRecordMapper;
    private final DwBatchMapper<DwsDailyCohortPkgUsrcAdvertising, DwsDailyCohortPkgUsrcAdvertisingMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Lock(paramName = "#dates")
    @ShowExecuteTime(name = "DwsDailyCohortPkgUsrcAdvertising")
    public void syncData(Integer dates) {
        // 批量处理，一条一条的从数据库中获取与内存中的数据近汇总
        DwCountDTO dbCount = dwdUserAdRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyCohortPkgUsrcAdvertising {} 统计数据为空，跳过处理", dates);
            return;
        }

        try {
            Map<String, DwsDailyCohortPkgUsrcAdvertising> cohortPkgUsrcAdMap = handelCohortData(dates, dbCount);
            if (!CollectionUtils.isEmpty(cohortPkgUsrcAdMap)) {
                List<DwsDailyCohortPkgUsrcAdvertising> dbActiveList =
                    dwsDailyCohortPkgUsrcAdvertisingMapper.queryDbListByDates(dates);
                List<DwsDailyCohortPkgUsrcAdvertising> verAdvertisingList =
                    this.setDbIdToNewList(dbActiveList, cohortPkgUsrcAdMap);
                dwBatchMapper.batchInsert(verAdvertisingList, DwsDailyCohortPkgUsrcAdvertisingMapper.class);
                // 删除没用的数据
                List<Long> delIds = getDelIds(dbActiveList, verAdvertisingList);
                if (!CollectionUtils.isEmpty(delIds)) {
                    dwsDailyCohortPkgUsrcAdvertisingMapper.deleteBatchIds(delIds);
                }
            }
        } catch (Throwable e) {
            log.error("DwsDailyCohortPkgUsrcAdvertising " + dates + "异常", e);
            throw new RuntimeException(e);
        }
    }

    private Map<String, DwsDailyCohortPkgUsrcAdvertising> handelCohortData(Integer dates, DwCountDTO dbCount) {
        Long minId = dbCount.getMinId();
        Long endId;
        Long maxId = dbCount.getMaxId() + 1;
        Map<String, DwsDailyCohortPkgUsrcAdvertising> cohortPkgUsrcAdMap = new HashMap<>();
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
                DwsDailyCohortPkgUsrcAdvertising ad = cohortPkgUsrcAdMap.getOrDefault(uniqueKey,
                    DwsDailyCohortPkgUsrcAdvertising.ofPkgUsrc(dwdUserAdRecord.getDates(),
                        dwdUserAdRecord.getRegisterDates(), dwdUserAdRecord.getRegisterDay(), dwdUserAdRecord.getPkg(),
                        dwdUserAdRecord.getUserSource()));
                // 缓存各个统计维度的用户数量
                ad.calculate(dwdUserAdRecord);
                cohortPkgUsrcAdMap.put(uniqueKey, ad);
            }
        } while (minId <= maxId);
        return cohortPkgUsrcAdMap;
    }

    private List<DwsDailyCohortPkgUsrcAdvertising> setDbIdToNewList(List<DwsDailyCohortPkgUsrcAdvertising> dbActiveList,
        Map<String, DwsDailyCohortPkgUsrcAdvertising> pkgVerAdMap) {
        if (!CollectionUtils.isEmpty(dbActiveList)) {
            Map<String, DwsDailyCohortPkgUsrcAdvertising> dbMap =
                dbActiveList.stream().collect(Collectors.toMap(this::uniqueDbKey, Function.identity()));
            dbMap.forEach((key, value) -> {
                if (pkgVerAdMap.containsKey(key)) {
                    DwsDailyCohortPkgUsrcAdvertising dwsDailyCohortPkgVerAdvertising = pkgVerAdMap.get(key);
                    dwsDailyCohortPkgVerAdvertising.setId(value.getId());
                    pkgVerAdMap.put(key, dwsDailyCohortPkgVerAdvertising);
                }
            });
        }
        return new ArrayList<>(pkgVerAdMap.values());
    }

    private String uniqueKey(DwdUserAdRecord dwdUserAdRecord) {
        return dwdUserAdRecord.getPkg() + "_" + dwdUserAdRecord.getRegisterDates() + "_"
            + dwdUserAdRecord.getRegisterDay() + "_" + dwdUserAdRecord.getUserSource();
    }

    private String uniqueDbKey(DwsDailyCohortPkgUsrcAdvertising dwsDailyCohortPkgUsrcAdvertising) {
        return dwsDailyCohortPkgUsrcAdvertising.getPkg() + "_" + dwsDailyCohortPkgUsrcAdvertising.getRegisterDates()
            + "_" + dwsDailyCohortPkgUsrcAdvertising.getCohortDay() + "_"
            + dwsDailyCohortPkgUsrcAdvertising.getUserSource();
    }
}
