package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

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
import com.leo.ad.codriver.dwd.dao.DwdQpLtvRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdQpLtvRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyCohortPkgUsrcQpLtvMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgUsrcQpLtv;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_cohort_pkg_usrc_qp_ltv】的数据库操作Service实现
 * @createDate 2025-06-26 14:47:24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyCohortPkgUsrcQpLtvServiceImpl implements DwsService {
    private final DwdQpLtvRecordMapper dwdQpLtvRecordMapper;
    private final DwsDailyCohortPkgUsrcQpLtvMapper dwsDailyCohortPkgUsrcQpLtvMapper;
    private final DwBatchMapper<DwsDailyCohortPkgUsrcQpLtv, DwsDailyCohortPkgUsrcQpLtvMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyCohortPkgUsrcQpLtvServiceImpl")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        // 查询统计数据
        DwCountDTO dbCount = dwdQpLtvRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyCohortPkgUsrcQpLtv {} 统计数据为空，跳过处理", dates);
            return;
        }

        Map<String, DwsDailyCohortPkgUsrcQpLtv> cohortPkgUsrcAdMap = getStatisticsDataMap(dates, dbCount);
        if (CollectionUtils.isEmpty(cohortPkgUsrcAdMap)) {
            return;
        }

        List<DwsDailyCohortPkgUsrcQpLtv> dbList = dwsDailyCohortPkgUsrcQpLtvMapper.queryDbListByDates(dates);
        List<DwsDailyCohortPkgUsrcQpLtv> newCohortPkgUsrcList = this.setDbIdToNewList(dbList, cohortPkgUsrcAdMap);
        dwBatchMapper.batchInsert(newCohortPkgUsrcList, DwsDailyCohortPkgUsrcQpLtvMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(dbList, newCohortPkgUsrcList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyCohortPkgUsrcQpLtvMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyCohortPkgUsrcQpLtv> setDbIdToNewList(List<DwsDailyCohortPkgUsrcQpLtv> dbActiveList,
        Map<String, DwsDailyCohortPkgUsrcQpLtv> pkgVerAdMap) {
        if (!CollectionUtils.isEmpty(dbActiveList)) {
            Map<String, DwsDailyCohortPkgUsrcQpLtv> dbMap =
                dbActiveList.stream().collect(Collectors.toMap(this::uniqueDbKey, Function.identity()));
            dbMap.forEach((key, value) -> {
                if (pkgVerAdMap.containsKey(key)) {
                    DwsDailyCohortPkgUsrcQpLtv dwsDailyCohortPkgVerAdvertising = pkgVerAdMap.get(key);
                    dwsDailyCohortPkgVerAdvertising.setId(value.getId());
                    pkgVerAdMap.put(key, dwsDailyCohortPkgVerAdvertising);
                }
            });
        }
        return new ArrayList<>(pkgVerAdMap.values());
    }

    private Map<String, DwsDailyCohortPkgUsrcQpLtv> getStatisticsDataMap(Integer dates, DwCountDTO dbCount) {
        Long minId = dbCount.getMinId();
        Long endId;
        Long maxId = dbCount.getMaxId() + 1;
        Map<String, DwsDailyCohortPkgUsrcQpLtv> cohortPkgUsrcAdMap = new HashMap<>();
        List<DwdQpLtvRecord> dbList;
        do {
            endId = minId + 10000;
            if (endId > maxId) {
                endId = maxId;
            }
            dbList = dwdQpLtvRecordMapper.queryListByDates(dates, minId, endId);
            minId += 10000;
            if (CollectionUtils.isEmpty(dbList)) {
                continue;
            }
            for (DwdQpLtvRecord dwdQpLtvRecord : dbList) {
                // 获取唯一key
                String uniqueKey = uniqueKey(dwdQpLtvRecord);
                DwsDailyCohortPkgUsrcQpLtv dwsDailyCohortPkgUsrcQpLtv = cohortPkgUsrcAdMap.getOrDefault(uniqueKey,
                    DwsDailyCohortPkgUsrcQpLtv.of(dwdQpLtvRecord.getDates().intValue(),
                        dwdQpLtvRecord.getRegisterDates(), dwdQpLtvRecord.getCohortDay(), dwdQpLtvRecord.getPkg(),
                        dwdQpLtvRecord.getUserSource()));
                // 缓存各个统计维度的用户数量
                dwsDailyCohortPkgUsrcQpLtv.calculate(dwdQpLtvRecord);
                cohortPkgUsrcAdMap.put(uniqueKey, dwsDailyCohortPkgUsrcQpLtv);
            }
        } while (minId <= maxId);
        return cohortPkgUsrcAdMap;
    }

    private String uniqueKey(DwdQpLtvRecord dwdQpLtvRecord) {
        return dwdQpLtvRecord.getPkg() + "_" + dwdQpLtvRecord.getRegisterDates() + "_" + dwdQpLtvRecord.getCohortDay()
            + "_" + dwdQpLtvRecord.getUserSource();
    }

    private String uniqueDbKey(DwsDailyCohortPkgUsrcQpLtv dwsDailyCohortPkgUsrcQpLtv) {
        return dwsDailyCohortPkgUsrcQpLtv.getPkg() + "_" + dwsDailyCohortPkgUsrcQpLtv.getRegisterDates() + "_"
            + dwsDailyCohortPkgUsrcQpLtv.getCohortDay() + "_" + dwsDailyCohortPkgUsrcQpLtv.getUserSource();
    }
}
