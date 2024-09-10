package com.leo.ad.codriver.dwd.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserGameRecordOetaMapper;
import com.leo.ad.codriver.dwd.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserGameRecordOeta;
import com.leo.ad.codriver.dwd.event.DwdUserGameRecordOetaUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.dwd.service.DwdStreamService;
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
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Integer nowDates = DateUtils.getNowDates();
        if (Objects.equals(dates, nowDates)) {
            // 当天数据不进行统计，跳过
            return false;
        }
        int rowNumInterval = 2000;
        Integer delRowNum = dwdUserGameRecordOetaMapper.deleteByDates(dates);
        Long totalRecord = dwdUserGameRecordOetaMapper.getStatisticsCount(dates);
        if (totalRecord <= 0) {
            return delRowNum > 0;
        }
        long totalPageNum = LongUtils.divide(totalRecord, (long)rowNumInterval);
        for (int i = 0; i < totalPageNum; i++) {
            List<DwdUserGameRecordOeta> statistics =
                dwdUserGameRecordOetaMapper.queryStatistics(dates, rowNumInterval, i * rowNumInterval);
            if (!CollectionUtils.isEmpty(statistics)) {
                statistics.forEach(DwdUserGameRecordOeta::initDate);
            }
            batchMapper.batchInsert(statistics, DwdUserGameRecordOetaMapper.class);
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
        batchMapper.batchInsert(Collections.singletonList(userGameRecord), DwdUserGameRecordOetaMapper.class);
        return true;
    }
}
