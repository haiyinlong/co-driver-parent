package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.UserTypeConstant;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserAdRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcAdvertisingMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcAdvertising;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import io.reactivex.rxjava3.functions.Function3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_advertising(dws广告汇总统计，有新增的广告商就新增字段)】的数据库操作Service实现
 * @createDate 2024-11-25 09:30:53
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcAdvertisingServiceImpl implements DwsService {
    private final DwsDailyPkgUsrcAdvertisingMapper dwsDailyPkgUsrcAdvertisingMapper;
    private final DwdUserAdRecordMapper dwdUserAdRecordMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcAdvertising, DwsDailyPkgUsrcAdvertisingMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Lock(paramName = "#dates")
    @ShowExecuteTime(name = "DwsDailyPkgUsrcAdvertising")
    public void syncData(Integer dates) {
        // 批量处理，一条一条的从数据库中获取与内存中的数据近汇总
        DwCountDTO dbCount = dwdUserAdRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyPkgUsrcAdvertising {} 统计数据为空，跳过处理", dates);
            return;
        }

        try {
            Function3<Integer, Long, Long, List<DwdUserAdRecord>> queryDbActiveListByDate =
                dwdUserAdRecordMapper::queryDbActiveListByDateUsrc;
            BiFunction<Integer, Integer, List<DwsDailyPkgUsrcAdvertising>> queryDbListByUserType =
                dwsDailyPkgUsrcAdvertisingMapper::queryDbListByUserType;
            this.handle(dates, dbCount, UserTypeConstant.ACTIVE_TYPE, queryDbActiveListByDate, queryDbListByUserType);

            Function3<Integer, Long, Long, List<DwdUserAdRecord>> queryDbNewListByDate =
                dwdUserAdRecordMapper::queryDbNewListByDateUsrc;
            this.handle(dates, dbCount, UserTypeConstant.NEW_TYPE, queryDbNewListByDate, queryDbListByUserType);
        } catch (Throwable e) {
            log.error("DwsDailyPkgUsrcAdvertising " + dates + "异常", e);
            throw new RuntimeException(e);
        }

    }

    private void handle(Integer dates, DwCountDTO dbCount, Integer userType,
        Function3<Integer, Long, Long, List<DwdUserAdRecord>> queryDbActiveListByDate,
        BiFunction<Integer, Integer, List<DwsDailyPkgUsrcAdvertising>> queryDbListByUserType) throws Throwable {
        Long minId = dbCount.getMinId();
        Long endId;
        Long maxId = dbCount.getMaxId() + 1;
        Map<String, DwsDailyPkgUsrcAdvertising> pkgVerAdMap = new HashMap<>();
        List<DwdUserAdRecord> dbList;
        do {
            endId = minId + 10000;
            if (endId > maxId) {
                endId = maxId;
            }
            dbList = queryDbActiveListByDate.apply(dates, minId, endId);
            minId += 10000;
            if (CollectionUtils.isEmpty(dbList)) {
                continue;
            }
            for (DwdUserAdRecord dwdUserAdRecord : dbList) {
                // 创建一个临时对象，用于存储数据. 包、版本
                String adPkgUsrc = getAdPkgUsrc(dwdUserAdRecord);
                DwsDailyPkgUsrcAdvertising ad =
                    pkgVerAdMap.getOrDefault(adPkgUsrc, DwsDailyPkgUsrcAdvertising.of(dwdUserAdRecord.getDates(),
                        dwdUserAdRecord.getPkg(), dwdUserAdRecord.getUserSource(), userType));
                // 缓存各个统计维度的用户数量
                ad.calculate(dwdUserAdRecord);
                pkgVerAdMap.put(adPkgUsrc, ad);
            }
        } while (minId <= maxId);
        // 转集合
        List<DwsDailyPkgUsrcAdvertising> dbActiveList = queryDbListByUserType.apply(dates, userType);
        List<DwsDailyPkgUsrcAdvertising> verAdvertisingList = this.setHistoryIdToList(dbActiveList, pkgVerAdMap);
        if (CollectionUtils.isEmpty(verAdvertisingList)) {
            return;
        }
        dwBatchMapper.batchInsert(verAdvertisingList, DwsDailyPkgUsrcAdvertisingMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(dbActiveList, verAdvertisingList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgUsrcAdvertisingMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyPkgUsrcAdvertising> setHistoryIdToList(List<DwsDailyPkgUsrcAdvertising> dbActiveList,
        Map<String, DwsDailyPkgUsrcAdvertising> pkgVerAdMap) {
        if (!CollectionUtils.isEmpty(dbActiveList)) {
            Map<String, DwsDailyPkgUsrcAdvertising> dbMap = dbActiveList.stream().collect(
                Collectors.toMap(keyItem -> keyItem.getPkg() + "_" + keyItem.getUserSource(), Function.identity()));
            dbMap.forEach((key, value) -> {
                if (pkgVerAdMap.containsKey(key)) {
                    DwsDailyPkgUsrcAdvertising dwsDailyPkgVerAdvertising = pkgVerAdMap.get(key);
                    dwsDailyPkgVerAdvertising.setId(value.getId());
                    pkgVerAdMap.put(key, dwsDailyPkgVerAdvertising);
                }
            });
        }
        return new ArrayList<>(pkgVerAdMap.values());
    }

    private String getAdPkgUsrc(DwdUserAdRecord dwdUserAdRecord) {
        return dwdUserAdRecord.getPkg() + "_" + dwdUserAdRecord.getUserSource();
    }
}
