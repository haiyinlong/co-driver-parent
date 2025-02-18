package com.leo.ad.codriver.dws.service.impl.pkg;

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

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.UserTypeConstant;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserAdRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgAdvertisingMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgAdvertising;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import io.reactivex.rxjava3.functions.Function3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_advertising(dws广告汇总统计，有新增的广告商就新增字段)】的数据库操作Service实现
 * @createDate 2024-11-25 09:30:53
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgAdvertisingServiceImpl implements DwsService {
    private final DwdUserAdRecordMapper dwdUserAdRecordMapper;
    private final DwsDailyPkgAdvertisingMapper dwsDailyPkgAdvertisingMapper;
    private final DwBatchMapper<DwsDailyPkgAdvertising, DwsDailyPkgAdvertisingMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Lock(paramName = "#dates")
    @ShowExecuteTime(name = "DwsDailyPkgAdvertising")
    public void syncData(Integer dates) {
        // 批量处理，一条一条的从数据库中获取与内存中的数据近汇总
        DwCountDTO dbCount = dwdUserAdRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyPkgAdvertising {} 统计数据为空，跳过处理", dates);
            return;
        }

        try {
            Function3<Integer, Long, Long, List<DwdUserAdRecord>> queryDbActiveListByDate =
                dwdUserAdRecordMapper::queryDbActiveListByDate;
            BiFunction<Integer, Integer, List<DwsDailyPkgAdvertising>> queryDbListByUserType =
                dwsDailyPkgAdvertisingMapper::queryDbListByUserType;
            this.handle(dates, dbCount, UserTypeConstant.ACTIVE_TYPE, queryDbActiveListByDate, queryDbListByUserType);

            Function3<Integer, Long, Long, List<DwdUserAdRecord>> queryDbNewListByDate =
                dwdUserAdRecordMapper::queryDbNewListByDate;
            this.handle(dates, dbCount, UserTypeConstant.NEW_TYPE, queryDbNewListByDate, queryDbListByUserType);
        } catch (Throwable e) {
            log.error("DwsDailyPkgAdvertising " + dates + "异常", e);
            throw new RuntimeException(e);
        }

    }

    private void handle(Integer dates, DwCountDTO dbCount, Integer userType,
        Function3<Integer, Long, Long, List<DwdUserAdRecord>> queryDbActiveListByDate,
        BiFunction<Integer, Integer, List<DwsDailyPkgAdvertising>> queryDbListByUserType) throws Throwable {
        Long minId = dbCount.getMinId();
        Long endId;
        Long maxId = dbCount.getMaxId() + 1;
        Map<String, DwsDailyPkgAdvertising> pkgVerAdMap = new HashMap<>();
        List<DwdUserAdRecord> dbList;
        do {
            endId = minId + BatchConst.BATCH_MAX_NUMBER;
            if (endId > maxId) {
                endId = maxId;
            }
            dbList = queryDbActiveListByDate.apply(dates, minId, endId);
            minId += BatchConst.BATCH_MAX_NUMBER;
            if (CollectionUtils.isEmpty(dbList)) {
                continue;
            }
            for (DwdUserAdRecord dwdUserAdRecord : dbList) {
                // 创建一个临时对象，用于存储数据. 包、版本
                DwsDailyPkgAdvertising ad = pkgVerAdMap.getOrDefault(dwdUserAdRecord.getPkg(),
                    DwsDailyPkgAdvertising.of(dwdUserAdRecord.getDates(), dwdUserAdRecord.getPkg(), userType));
                // 缓存各个统计维度的用户数量
                ad.calculate(dwdUserAdRecord);
                pkgVerAdMap.put(dwdUserAdRecord.getPkg(), ad);
            }
        } while (minId <= maxId);
        // 转集合
        List<DwsDailyPkgAdvertising> dbActiveList = queryDbListByUserType.apply(dates, userType);
        List<DwsDailyPkgAdvertising> verAdvertisingList = this.setHistoryIdToList(dbActiveList, pkgVerAdMap);
        if (CollectionUtils.isEmpty(verAdvertisingList)) {
            return;
        }
        dwBatchMapper.batchInsert(verAdvertisingList, DwsDailyPkgAdvertisingMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(dbActiveList, verAdvertisingList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgAdvertisingMapper.deleteBatchIds(delIds);
        }
    }

    private List<DwsDailyPkgAdvertising> setHistoryIdToList(List<DwsDailyPkgAdvertising> dbActiveList,
        Map<String, DwsDailyPkgAdvertising> pkgVerAdMap) {
        if (!CollectionUtils.isEmpty(dbActiveList)) {
            Map<String, DwsDailyPkgAdvertising> dbMap =
                dbActiveList.stream().collect(Collectors.toMap(DwsDailyPkgAdvertising::getPkg, Function.identity()));
            dbMap.forEach((key, value) -> {
                if (pkgVerAdMap.containsKey(key)) {
                    DwsDailyPkgAdvertising dwsDailyPkgVerAdvertising = pkgVerAdMap.get(key);
                    dwsDailyPkgVerAdvertising.setId(value.getId());
                    pkgVerAdMap.put(key, dwsDailyPkgVerAdvertising);
                }
            });
        }
        return new ArrayList<>(pkgVerAdMap.values());
    }
}
