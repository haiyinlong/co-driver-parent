package com.leo.ad.codriver.dws.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserRegisterMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserRegister;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllRegisterMapper;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgRegisterMapper;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcRegisterMapper;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcRegisterMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllRegister;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgRegister;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcRegister;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcRegister;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyRegister 数据处理<br>
 * 处理每日注册包维度的数据； <br>
 * 处理每日注册包、版本维度的数据；<br>
 * 处理每日注册包、用户来源维度的数据； <br>
 * 处理每日注册包、版本、用户来源维度的数据； <br>
 * // 处理每日注册包、国家维度的数据
 *
 * @author HaiYinLong
 * @version 2024/11/27 12:04
 **/
@Order(1)
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyRegisterServiceImpl implements DwsService {
    private final DwdUserRegisterMapper dwdUserRegisterMapper;
    private final DwsDailyPkgRegisterMapper dwsDailyPkgRegisterMapper;
    private final DwBatchMapper<DwsDailyPkgRegister, DwsDailyPkgRegisterMapper> dwPkgBatchMapper;

    private final DwsDailyPkgUsrcRegisterMapper dwsDailyPkgUsrcRegisterMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcRegister, DwsDailyPkgUsrcRegisterMapper> dwPkgUsrcBatchMapper;

    private final DwsDailyPkgVerUsrcRegisterMapper dwsDailyPkgVerUsrcRegisterMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcRegister, DwsDailyPkgVerUsrcRegisterMapper> dwPkgVerUsrcBatchMapper;

    private final DwsDailyPackageAllRegisterMapper dwsDailyPackageAllRegisterMapper;
    private final DwBatchMapper<DwsDailyPackageAllRegister, DwsDailyPackageAllRegisterMapper> dwPkgVerBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyRegister")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        DwCountDTO dbCount = dwdUserRegisterMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyRegister {} 统计数据为空，跳过处理", dates);
            return;
        }
        Map<String, DwsDailyPkgRegister> pkgRegisterMap = new HashMap<>();
        Map<String, DwsDailyPkgUsrcRegister> pkgUsrcRegisterMap = new HashMap<>();
        Map<String, DwsDailyPkgVerUsrcRegister> pkgVerUsrcRegisterMap = new HashMap<>();
        Map<String, DwsDailyPackageAllRegister> pkgVerRegisterMap = new HashMap<>();
        // 遍历数据给各个维度赋值
        long startId = dbCount.getMinId();
        long endId = dbCount.getMinId();
        do {
            endId += BatchConst.BATCH_MAX_NUMBER;
            if (endId >= dbCount.getMaxId()) {
                endId = dbCount.getMaxId();
            }
            List<DwdUserRegister> userUsrcRegisterList =
                dwdUserRegisterMapper.queryUsrcListWithAndId(dates, startId, endId);
            startId = endId + 1;
            if (CollectionUtils.isEmpty(userUsrcRegisterList)) {
                continue;
            }
            for (DwdUserRegister dwdUserRegister : userUsrcRegisterList) {
                handlePkgRegister(dwdUserRegister, pkgRegisterMap);
                handlePkgUsrcRegister(dwdUserRegister, pkgUsrcRegisterMap);
                handlePkgVerRegister(dwdUserRegister, pkgVerRegisterMap);
                handlePkgVerUsrcRegister(dwdUserRegister, pkgVerUsrcRegisterMap);
            }
        } while (startId < dbCount.getMaxId());
        // 进行版本赋值
        setTotalWithPkgUserRegisterMap(pkgUsrcRegisterMap, pkgRegisterMap);
        setTotalWithPkgVerRegisterMap(pkgVerRegisterMap, pkgRegisterMap);
        setTotalWithPkgVerUserRegisterMap(pkgVerUsrcRegisterMap, pkgVerRegisterMap, pkgUsrcRegisterMap);
        // 删除数据库中不用的记录
        List<DwsDailyPkgRegister> pkgRegisterList = dwsDailyPkgRegisterMapper.queryDbList(dates);
        List<DwsDailyPkgRegister> pkgRegisters = mergePkgRegisterHistoryId(pkgRegisterList, pkgRegisterMap);
        dwPkgBatchMapper.batchInsert(pkgRegisters, DwsDailyPkgRegisterMapper.class);
        List<Long> delPkgRegisterIds = getDelIds(pkgRegisterList, pkgRegisters);
        if (!CollectionUtils.isEmpty(delPkgRegisterIds)) {
            dwsDailyPkgRegisterMapper.deleteBatchIds(delPkgRegisterIds);
        }

        List<DwsDailyPkgUsrcRegister> pkgUsrcRegisterList = dwsDailyPkgUsrcRegisterMapper.queryDbList(dates);
        List<DwsDailyPkgUsrcRegister> pkgUsrcRegisters =
            mergePkgUsrcRegisterHistoryId(pkgUsrcRegisterList, pkgUsrcRegisterMap);
        dwPkgUsrcBatchMapper.batchInsert(pkgUsrcRegisters, DwsDailyPkgUsrcRegisterMapper.class);
        List<Long> delPkgUsrcRegisterIds = getDelIds(pkgRegisterList, pkgUsrcRegisters);
        if (!CollectionUtils.isEmpty(delPkgUsrcRegisterIds)) {
            dwsDailyPkgUsrcRegisterMapper.deleteBatchIds(delPkgUsrcRegisterIds);
        }

        List<DwsDailyPkgVerUsrcRegister> pkgVerUsrcRegisterList = dwsDailyPkgVerUsrcRegisterMapper.queryDbList(dates);
        List<DwsDailyPkgVerUsrcRegister> pkgVerUsrcRegisters =
            mergePkgVerUsrcRegisterHistoryId(pkgVerUsrcRegisterList, pkgVerUsrcRegisterMap);
        dwPkgVerUsrcBatchMapper.batchInsert(pkgVerUsrcRegisters, DwsDailyPkgVerUsrcRegisterMapper.class);
        List<Long> delPkgVerUsrcRegisterIds = getDelIds(pkgVerUsrcRegisterList, pkgVerUsrcRegisters);
        if (!CollectionUtils.isEmpty(delPkgVerUsrcRegisterIds)) {
            dwsDailyPkgVerUsrcRegisterMapper.deleteBatchIds(delPkgVerUsrcRegisterIds);
        }

        List<DwsDailyPackageAllRegister> pkgVerRegisterList = dwsDailyPackageAllRegisterMapper.queryDbList(dates);
        List<DwsDailyPackageAllRegister> pkgVerRegisters =
            mergePkgVerRegisterHistoryId(pkgVerRegisterList, pkgVerRegisterMap);
        dwPkgVerBatchMapper.batchInsert(pkgVerRegisters, DwsDailyPackageAllRegisterMapper.class);
        List<Long> delPkgVerRegisterIds = getDelIds(pkgVerRegisterList, pkgVerRegisters);
        if (!CollectionUtils.isEmpty(delPkgVerRegisterIds)) {
            dwsDailyPackageAllRegisterMapper.deleteBatchIds(delPkgVerRegisterIds);
        }
    }

    private List<DwsDailyPackageAllRegister> mergePkgVerRegisterHistoryId(
        List<DwsDailyPackageAllRegister> pkgVerRegisterList,
        Map<String, DwsDailyPackageAllRegister> pkgVerRegisterMap) {
        if (!CollectionUtils.isEmpty(pkgVerRegisterList)) {
            Map<String, DwsDailyPackageAllRegister> dbPkgVerRegisterMap = pkgVerRegisterList.stream().collect(
                Collectors.toMap(pkgUsrc -> pkgUsrc.getPkg() + "_" + pkgUsrc.getVersion(), Function.identity()));
            pkgVerRegisterMap.forEach((key, value) -> {
                if (dbPkgVerRegisterMap.containsKey(key)) {
                    DwsDailyPackageAllRegister dbPkgVerRegister = dbPkgVerRegisterMap.get(key);
                    value.setId(dbPkgVerRegister.getId());
                }
            });
        }
        return pkgVerRegisterMap.values().stream().toList();
    }

    private List<DwsDailyPkgVerUsrcRegister> mergePkgVerUsrcRegisterHistoryId(
        List<DwsDailyPkgVerUsrcRegister> pkgVerUsrcRegisterList,
        Map<String, DwsDailyPkgVerUsrcRegister> pkgVerUsrcRegisterMap) {
        if (!CollectionUtils.isEmpty(pkgVerUsrcRegisterList)) {
            Map<String,
                DwsDailyPkgVerUsrcRegister> dbPkgVerUsrcRegisterMap = pkgVerUsrcRegisterList.stream()
                    .collect(Collectors.toMap(
                        pkgUsrc -> pkgUsrc.getPkg() + "_" + pkgUsrc.getVersion() + "_" + pkgUsrc.getUserSource(),
                        Function.identity()));
            pkgVerUsrcRegisterMap.forEach((key, value) -> {
                if (dbPkgVerUsrcRegisterMap.containsKey(key)) {
                    DwsDailyPkgVerUsrcRegister dbPkgVerUsrcRegister = dbPkgVerUsrcRegisterMap.get(key);
                    value.setId(dbPkgVerUsrcRegister.getId());
                }
            });
        }
        return pkgVerUsrcRegisterMap.values().stream().toList();
    }

    private List<DwsDailyPkgUsrcRegister> mergePkgUsrcRegisterHistoryId(
        List<DwsDailyPkgUsrcRegister> pkgUsrcRegisterList, Map<String, DwsDailyPkgUsrcRegister> pkgUsrcRegisterMap) {
        if (!CollectionUtils.isEmpty(pkgUsrcRegisterList)) {
            Map<String, DwsDailyPkgUsrcRegister> dbPkgUsrcRegisterMap = pkgUsrcRegisterList.stream().collect(
                Collectors.toMap(pkgUsrc -> pkgUsrc.getPkg() + "_" + pkgUsrc.getUserSource(), Function.identity()));
            pkgUsrcRegisterMap.forEach((key, value) -> {
                if (dbPkgUsrcRegisterMap.containsKey(key)) {
                    DwsDailyPkgUsrcRegister dbPkgRegister = dbPkgUsrcRegisterMap.get(key);
                    value.setId(dbPkgRegister.getId());
                }
            });
        }
        return pkgUsrcRegisterMap.values().stream().toList();
    }

    private static List<DwsDailyPkgRegister> mergePkgRegisterHistoryId(List<DwsDailyPkgRegister> pkgRegisterList,
        Map<String, DwsDailyPkgRegister> pkgRegisterMap) {
        if (!CollectionUtils.isEmpty(pkgRegisterList)) {
            Map<String, DwsDailyPkgRegister> dbPkgRegisterMap =
                pkgRegisterList.stream().collect(Collectors.toMap(DwsDailyPkgRegister::getPkg, Function.identity()));
            pkgRegisterMap.forEach((key, value) -> {
                if (dbPkgRegisterMap.containsKey(key)) {
                    DwsDailyPkgRegister dbPkgRegister = dbPkgRegisterMap.get(key);
                    value.setId(dbPkgRegister.getId());
                }
            });
        }
        return pkgRegisterMap.values().stream().toList();
    }

    private static void setTotalWithPkgVerUserRegisterMap(Map<String, DwsDailyPkgVerUsrcRegister> pkgVerUsrcRegisterMap,
        Map<String, DwsDailyPackageAllRegister> pkgVerRegisterMap,
        Map<String, DwsDailyPkgUsrcRegister> pkgUsrcRegisterMap) {
        for (Map.Entry<String, DwsDailyPkgVerUsrcRegister> pkgVerUsrcRegisterEntry : pkgVerUsrcRegisterMap.entrySet()) {
            String pkgVerKey =
                pkgVerUsrcRegisterEntry.getValue().getPkg() + "_" + pkgVerUsrcRegisterEntry.getValue().getVersion();
            if (pkgVerRegisterMap.containsKey(pkgVerKey)) {
                DwsDailyPackageAllRegister pkgVerRegister = pkgVerRegisterMap.get(pkgVerKey);
                pkgVerUsrcRegisterEntry.getValue().setTotal(pkgVerRegister.getTotalUserNum(),
                    pkgVerRegister.getTotalInvestedUserNum());
            }
            String pkgUsrcKey =
                pkgVerUsrcRegisterEntry.getValue().getPkg() + "_" + pkgVerUsrcRegisterEntry.getValue().getUserSource();
            if (pkgUsrcRegisterMap.containsKey(pkgUsrcKey)) {
                DwsDailyPkgUsrcRegister pkgUsrcRegister = pkgUsrcRegisterMap.get(pkgUsrcKey);
                pkgVerUsrcRegisterEntry.getValue().setVerTotal(pkgUsrcRegister.getUserNum());
            }
        }
    }

    private static void setTotalWithPkgVerRegisterMap(Map<String, DwsDailyPackageAllRegister> pkgVerRegisterMap,
        Map<String, DwsDailyPkgRegister> pkgRegisterMap) {
        for (Map.Entry<String, DwsDailyPackageAllRegister> pkgVerRegisterEntry : pkgVerRegisterMap.entrySet()) {
            if (pkgRegisterMap.containsKey(pkgVerRegisterEntry.getValue().getPkg())) {
                DwsDailyPkgRegister pkgRegister = pkgRegisterMap.get(pkgVerRegisterEntry.getValue().getPkg());
                pkgVerRegisterEntry.getValue().setTotal(pkgRegister.getUserNum(), pkgRegister.getInvestedUserNum());
            }
        }
    }

    private static void setTotalWithPkgUserRegisterMap(Map<String, DwsDailyPkgUsrcRegister> pkgUsrcRegisterMap,
        Map<String, DwsDailyPkgRegister> pkgRegisterMap) {
        for (Map.Entry<String, DwsDailyPkgUsrcRegister> pkgUsrcRegisterEntry : pkgUsrcRegisterMap.entrySet()) {
            if (pkgRegisterMap.containsKey(pkgUsrcRegisterEntry.getValue().getPkg())) {
                DwsDailyPkgRegister pkgRegister = pkgRegisterMap.get(pkgUsrcRegisterEntry.getValue().getPkg());
                pkgUsrcRegisterEntry.getValue().setTotal(pkgRegister.getUserNum(), pkgRegister.getInvestedUserNum());
            }
        }
    }

    private static void handlePkgRegister(DwdUserRegister dwdUserRegister,
        Map<String, DwsDailyPkgRegister> pkgRegisterMap) {
        DwsDailyPkgRegister pkgRegister = pkgRegisterMap.getOrDefault(dwdUserRegister.getPkg(),
            DwsDailyPkgRegister.of(dwdUserRegister.getDates(), dwdUserRegister.getPkg()));
        pkgRegister.calculate(dwdUserRegister);
        pkgRegisterMap.put(dwdUserRegister.getPkg(), pkgRegister);
    }

    private static void handlePkgUsrcRegister(DwdUserRegister dwdUserRegister,
        Map<String, DwsDailyPkgUsrcRegister> pkgUsrcRegisterMap) {
        String pkgUsrcKey = dwdUserRegister.getPkg() + "_" + dwdUserRegister.getUserSource();
        DwsDailyPkgUsrcRegister pkgUsrcRegister = pkgUsrcRegisterMap.getOrDefault(pkgUsrcKey, DwsDailyPkgUsrcRegister
            .of(dwdUserRegister.getDates(), dwdUserRegister.getPkg(), dwdUserRegister.getUserSource()));
        pkgUsrcRegister.calculate(dwdUserRegister);
        pkgUsrcRegisterMap.put(pkgUsrcKey, pkgUsrcRegister);
    }

    private static void handlePkgVerRegister(DwdUserRegister dwdUserRegister,
        Map<String, DwsDailyPackageAllRegister> pkgVerRegisterMap) {
        String pkgVerKey = dwdUserRegister.getPkg() + "_" + dwdUserRegister.getVersion();
        DwsDailyPackageAllRegister pkgVerRegister = pkgVerRegisterMap.getOrDefault(pkgVerKey, DwsDailyPackageAllRegister
            .of(dwdUserRegister.getDates(), dwdUserRegister.getPkg(), dwdUserRegister.getVersion()));
        pkgVerRegister.calculate(dwdUserRegister);
        pkgVerRegisterMap.put(pkgVerKey, pkgVerRegister);
    }

    private static void handlePkgVerUsrcRegister(DwdUserRegister dwdUserRegister,
        Map<String, DwsDailyPkgVerUsrcRegister> pkgVerUsrcRegisterMap) {
        String pkgVerUsrcKey =
            dwdUserRegister.getPkg() + "_" + dwdUserRegister.getVersion() + "_" + dwdUserRegister.getUserSource();
        DwsDailyPkgVerUsrcRegister pkgVerUsrcRegister =
            pkgVerUsrcRegisterMap.getOrDefault(pkgVerUsrcKey, DwsDailyPkgVerUsrcRegister.of(dwdUserRegister.getDates(),
                dwdUserRegister.getPkg(), dwdUserRegister.getVersion(), dwdUserRegister.getUserSource()));
        pkgVerUsrcRegister.calculate(dwdUserRegister);
        pkgVerUsrcRegisterMap.put(pkgVerUsrcKey, pkgVerUsrcRegister);
    }
}
