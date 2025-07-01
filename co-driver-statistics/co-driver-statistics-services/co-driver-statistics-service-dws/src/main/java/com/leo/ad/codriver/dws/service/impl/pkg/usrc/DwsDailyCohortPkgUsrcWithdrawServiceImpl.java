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
import com.leo.ad.codriver.dwd.dao.DwdUserWithdrawRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserWithdrawRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyCohortPkgUsrcWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgUsrcWithdraw;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_cohort_pkg_usrc_withdraw】的数据库操作Service实现
 * @createDate 2025-06-26 14:47:24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyCohortPkgUsrcWithdrawServiceImpl implements DwsService {
    private final DwdUserWithdrawRecordMapper dwdUserWithdrawRecordMapper;
    private final DwsDailyCohortPkgUsrcWithdrawMapper dwsDailyCohortPkgUsrcWithdrawMapper;
    private final DwBatchMapper<DwsDailyCohortPkgUsrcWithdraw, DwsDailyCohortPkgUsrcWithdrawMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyCohortPkgUsrcWithdrawServiceImpl")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        // 查询统计数据
        DwCountDTO dbCount = dwdUserWithdrawRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyCohortPkgUsrcWithdraw {} 统计数据为空，跳过处理", dates);
            return;
        }

        Map<String, DwsDailyCohortPkgUsrcWithdraw> cohortPkgUsrcAdMap = getStatisticsDataMap(dates, dbCount);
        if (CollectionUtils.isEmpty(cohortPkgUsrcAdMap)) {
            return;
        }
        List<DwsDailyCohortPkgUsrcWithdraw> dbList = dwsDailyCohortPkgUsrcWithdrawMapper.queryDbListByDates(dates);
        List<DwsDailyCohortPkgUsrcWithdraw> newCohortPkgUsrcList = this.setDbIdToNewList(dbList, cohortPkgUsrcAdMap);
        dwBatchMapper.batchInsert(newCohortPkgUsrcList, DwsDailyCohortPkgUsrcWithdrawMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(dbList, newCohortPkgUsrcList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyCohortPkgUsrcWithdrawMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyCohortPkgUsrcWithdraw> setDbIdToNewList(List<DwsDailyCohortPkgUsrcWithdraw> dbActiveList,
        Map<String, DwsDailyCohortPkgUsrcWithdraw> pkgVerAdMap) {
        if (!CollectionUtils.isEmpty(dbActiveList)) {
            Map<String, DwsDailyCohortPkgUsrcWithdraw> dbMap =
                dbActiveList.stream().collect(Collectors.toMap(this::uniqueDbKey, Function.identity()));
            dbMap.forEach((key, value) -> {
                if (pkgVerAdMap.containsKey(key)) {
                    DwsDailyCohortPkgUsrcWithdraw dwsDailyCohortPkgVerAdvertising = pkgVerAdMap.get(key);
                    dwsDailyCohortPkgVerAdvertising.setId(value.getId());
                    pkgVerAdMap.put(key, dwsDailyCohortPkgVerAdvertising);
                }
            });
        }
        return new ArrayList<>(pkgVerAdMap.values());
    }

    private Map<String, DwsDailyCohortPkgUsrcWithdraw> getStatisticsDataMap(Integer dates, DwCountDTO dbCount) {
        Long minId = dbCount.getMinId();
        Long endId;
        Long maxId = dbCount.getMaxId() + 1;
        Map<String, DwsDailyCohortPkgUsrcWithdraw> cohortPkgUsrcAdMap = new HashMap<>();
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
                DwsDailyCohortPkgUsrcWithdraw dwsDailyCohortPkgUsrcWithdraw = cohortPkgUsrcAdMap.getOrDefault(uniqueKey,
                    DwsDailyCohortPkgUsrcWithdraw.of(dwdUserWithdrawRecord.getDates(),
                        dwdUserWithdrawRecord.getRegisterDates(), dwdUserWithdrawRecord.getRegisterDay(),
                        dwdUserWithdrawRecord.getPkg(), dwdUserWithdrawRecord.getUserSource()));
                // 缓存各个统计维度的用户数量
                dwsDailyCohortPkgUsrcWithdraw.calculate(dwdUserWithdrawRecord);
                cohortPkgUsrcAdMap.put(uniqueKey, dwsDailyCohortPkgUsrcWithdraw);
            }
        } while (minId <= maxId);
        return cohortPkgUsrcAdMap;
    }

    private String uniqueKey(DwdUserWithdrawRecord dwdUserWithdrawRecord) {
        return dwdUserWithdrawRecord.getPkg() + "_" + dwdUserWithdrawRecord.getRegisterDates() + "_"
            + dwdUserWithdrawRecord.getRegisterDay() + "_" + dwdUserWithdrawRecord.getUserSource();
    }

    private String uniqueDbKey(DwsDailyCohortPkgUsrcWithdraw dwsDailyCohortPkgUsrcWithdraw) {
        return dwsDailyCohortPkgUsrcWithdraw.getPkg() + "_" + dwsDailyCohortPkgUsrcWithdraw.getRegisterDates() + "_"
            + dwsDailyCohortPkgUsrcWithdraw.getCohortDay() + "_" + dwsDailyCohortPkgUsrcWithdraw.getUserSource();
    }
}
