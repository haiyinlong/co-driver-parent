package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserGameRecordOetaMapper;
import com.leo.ad.codriver.dwd.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.entity.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserGameRecordOeta;
import com.leo.ad.codriver.dwd.event.DwdUserGameRecordOetaUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.dwd.service.DwdStreamService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserGameRecordOetaServiceImpl记录
 *
 * @author HaiYinLong
 * @version 2024/07/02 15:15
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserGameRecordOetaServiceImpl implements DwdService, DwdStreamService {
    private final DwdUserGameRecordOetaMapper dwdUserGameRecordOetaMapper;
    private final DwBatchMapper<DwdUserGameRecordOeta, DwdUserGameRecordOetaMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserGameRecordOeta syncData")
    @AutoPushEventWithTrue(events = {DwdUserGameRecordOetaUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Integer nowDates = DateUtils.getNowDates();
        if (Objects.equals(dates, nowDates)) {
            // 当天数据不进行统计，跳过
            return false;
        }
        // 数据库中如果不存在就不处理
        DwCountDTO statisticsCount = dwdUserGameRecordOetaMapper.getStatisticsCount(dates);
        if (ObjectUtils.isEmpty(statisticsCount) || ObjectUtils.isEmpty(statisticsCount.getMinId())) {
            return false;
        }
        try {
            long startSourceId = statisticsCount.getMinId();
            List<DwdUserGameRecordOeta> dwdUserGameRecordOetas;
            List<DwdUserGameRecordOeta> newList;
            do {
                dwdUserGameRecordOetas = dwdUserGameRecordOetaMapper.queryStatisticsByDate(dates,
                    BatchConst.BATCH_MAX_NUMBER, startSourceId);
                if (CollectionUtils.isEmpty(dwdUserGameRecordOetas)) {
                    startSourceId = startSourceId + BatchConst.BATCH_MAX_NUMBER;
                } else {
                    startSourceId = dwdUserGameRecordOetas.stream().map(DwdUserGameRecordOeta::getSourceId).sorted()
                        .toList().get(dwdUserGameRecordOetas.size() - 1);
                    // 过滤掉已经有id的数据
                    newList = dwdUserGameRecordOetas.stream()
                        .filter(userAdRecordItem -> ObjectUtils.isEmpty(userAdRecordItem.getId()))
                        .peek(DwdUserGameRecordOeta::init).toList();
                    if (!CollectionUtils.isEmpty(newList)) {
                        batchMapper.batchInsert(newList, DwdUserGameRecordOetaMapper.class);
                    }
                }
            } while (startSourceId < statisticsCount.getMaxId());
        } catch (Exception e) {
            log.error("dwdUserGameRecordOeta  syncData error", e);
            throw e;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean syncChangeData(DataChangeDTO dataChangeDTO) {
        DwdUserGameRecordOeta userGameRecord = dwdUserGameRecordOetaMapper.getStatistics(dataChangeDTO.getSourceId());
        if (ObjectUtils.isEmpty(userGameRecord)) {
            dwdUserGameRecordOetaMapper.deleteBySourceId(dataChangeDTO.getSourceId());
            return true;
        }
        if (ObjectUtils.isEmpty(userGameRecord.getId())) {
            dwdUserGameRecordOetaMapper.insert(userGameRecord);
        } else {
            dwdUserGameRecordOetaMapper.updateById(userGameRecord);
        }
        return true;
    }
}
