package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdPromotionRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdPromotionRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgInvestedMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgInvested;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_invested(dws推广花费)】的数据库操作Service实现
 * @createDate 2024-11-26 17:28:36
 */
@Order(Integer.MAX_VALUE)
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgInvestedServiceImpl implements DwsService {
    private final DwdPromotionRecordMapper dwdPromotionRecordMapper;
    private final DwsDailyPkgInvestedMapper dwsDailyPkgInvestedMapper;
    private final DwBatchMapper<DwsDailyPkgInvested, DwsDailyPkgInvestedMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgInvested")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        DwCountDTO dbCount = dwdPromotionRecordMapper.getDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("DwsDailyPkgInvested {} 统计数据为空，跳过处理", dates);
            return;
        }
        long startId = dbCount.getMinId();
        long endId = dbCount.getMinId();
        List<DwdPromotionRecord> promotionRecordList;
        Map<String, DwsDailyPkgInvested> pkgInvestedMap = new HashMap<>();
        DwsDailyPkgInvested pkgInvested;
        do {
            endId += BatchConst.BATCH_MAX_NUMBER;
            if (endId >= dbCount.getMaxId()) {
                endId = dbCount.getMaxId();
            }
            promotionRecordList = dwdPromotionRecordMapper.queryByDateAndId(dates, startId, endId);
            startId = endId + 1;
            if (CollectionUtils.isEmpty(promotionRecordList)) {
                continue;
            }
            // 业务处理
            for (DwdPromotionRecord promotionRecord : promotionRecordList) {
                pkgInvested = pkgInvestedMap.getOrDefault(promotionRecord.getPkg(),
                    DwsDailyPkgInvested.of(dates, promotionRecord.getPkg()));
                pkgInvested.calculate(promotionRecord);
                pkgInvestedMap.put(promotionRecord.getPkg(), pkgInvested);
            }
        } while (startId < dbCount.getMaxId());
        // 获取数据库记录
        List<DwsDailyPkgInvested> dbList = dwsDailyPkgInvestedMapper.queryDbList(dates);
        List<DwsDailyPkgInvested> insertOrUpdateList = mergeDbListToNewList(dbList, pkgInvestedMap);
        dwBatchMapper.batchInsert(insertOrUpdateList, DwsDailyPkgInvestedMapper.class);
        // 删除没用的数据
        List<Long> delIds = getDelIds(dbList, insertOrUpdateList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgInvestedMapper.deleteBatchIds(delIds);
        }
    }

    private static List<DwsDailyPkgInvested> mergeDbListToNewList(List<DwsDailyPkgInvested> dbList,
        Map<String, DwsDailyPkgInvested> pkgInvestedMap) {
        if (!CollectionUtils.isEmpty(dbList)) {
            // 组装id
            Map<String, DwsDailyPkgInvested> dbMap =
                dbList.stream().collect(Collectors.toMap(DwsDailyPkgInvested::getPkg, Function.identity()));
            pkgInvestedMap.forEach((key, value) -> {
                if (dbMap.containsKey(key)) {
                    value.setId(dbMap.get(key).getId());
                }
            });
        }
        return pkgInvestedMap.values().stream().toList();
    }
}
