package com.leo.ad.codriver.dws.service.impl.pkg;

import java.math.BigDecimal;
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
import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdQpLtvRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdQpLtvRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyCohortPkgQpLtvMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgQpLtv;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_cohort_pkg_qp_ltv】的数据库操作Service实现
 * @createDate 2025-06-26 14:47:24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyCohortPkgQpLtvServiceImpl implements DwsService {
    private final ExchangeRate exchangeRate;
    private final DwdQpLtvRecordMapper dwdQpLtvRecordMapper;
    private final DwsDailyCohortPkgQpLtvMapper dwsDailyCohortPkgQpLtvMapper;
    private final DwBatchMapper<DwsDailyCohortPkgQpLtv, DwsDailyCohortPkgQpLtvMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyCohortPkgQpLtvServiceImpl")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        // 查询统计数据
        DwCountDTO dbCount = dwdQpLtvRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyCohortPkgQpLtv {} 统计数据为空，跳过处理", dates);
            return;
        }

        BigDecimal indianToDollar = exchangeRate.getIndianToDollar();
        Map<String, DwsDailyCohortPkgQpLtv> cohortPkgUsrcAdMap = getStatisticsDataMap(dates, dbCount, indianToDollar);
        if (CollectionUtils.isEmpty(cohortPkgUsrcAdMap)) {
            return;
        }

        List<DwsDailyCohortPkgQpLtv> dbList = dwsDailyCohortPkgQpLtvMapper.queryDbListByDates(dates);
        List<DwsDailyCohortPkgQpLtv> newCohortPkgUsrcList = this.setDbIdToNewList(dbList, cohortPkgUsrcAdMap);
        dwBatchMapper.batchInsert(newCohortPkgUsrcList, DwsDailyCohortPkgQpLtvMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(dbList, newCohortPkgUsrcList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyCohortPkgQpLtvMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyCohortPkgQpLtv> setDbIdToNewList(List<DwsDailyCohortPkgQpLtv> dbActiveList,
        Map<String, DwsDailyCohortPkgQpLtv> pkgVerAdMap) {
        if (!CollectionUtils.isEmpty(dbActiveList)) {
            Map<String, DwsDailyCohortPkgQpLtv> dbMap =
                dbActiveList.stream().collect(Collectors.toMap(this::uniqueDbKey, Function.identity()));
            dbMap.forEach((key, value) -> {
                if (pkgVerAdMap.containsKey(key)) {
                    DwsDailyCohortPkgQpLtv dwsDailyCohortPkgVerAdvertising = pkgVerAdMap.get(key);
                    dwsDailyCohortPkgVerAdvertising.setId(value.getId());
                    pkgVerAdMap.put(key, dwsDailyCohortPkgVerAdvertising);
                }
            });
        }
        return new ArrayList<>(pkgVerAdMap.values());
    }

    private Map<String, DwsDailyCohortPkgQpLtv> getStatisticsDataMap(Integer dates, DwCountDTO dbCount,
        BigDecimal indianToDollar) {
        Long minId = dbCount.getMinId();
        Long endId;
        Long maxId = dbCount.getMaxId() + 1;
        Map<String, DwsDailyCohortPkgQpLtv> cohortPkgUsrcAdMap = new HashMap<>();
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
                DwsDailyCohortPkgQpLtv dwsDailyCohortPkgQpLtv = cohortPkgUsrcAdMap.getOrDefault(uniqueKey,
                    DwsDailyCohortPkgQpLtv.of(dwdQpLtvRecord.getDates().intValue(), dwdQpLtvRecord.getRegisterDates(),
                        dwdQpLtvRecord.getPkg(), dwdQpLtvRecord.getCohortDay()));
                // 缓存各个统计维度的用户数量
                dwsDailyCohortPkgQpLtv.calculate(dwdQpLtvRecord, indianToDollar);
                cohortPkgUsrcAdMap.put(uniqueKey, dwsDailyCohortPkgQpLtv);
            }
        } while (minId <= maxId);
        return cohortPkgUsrcAdMap;
    }

    private String uniqueKey(DwdQpLtvRecord dwdQpLtvRecord) {
        return dwdQpLtvRecord.getPkg() + "_" + dwdQpLtvRecord.getRegisterDates() + "_" + dwdQpLtvRecord.getCohortDay();
    }

    private String uniqueDbKey(DwsDailyCohortPkgQpLtv dwsDailyCohortPkgQpLtv) {
        return dwsDailyCohortPkgQpLtv.getPkg() + "_" + dwsDailyCohortPkgQpLtv.getRegisterDates() + "_"
            + dwsDailyCohortPkgQpLtv.getCohortDay();
    }
}
