package com.leo.ad.codriver.common.task;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.dao.DynamicTableRepository;
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwTaskRecordService;
import com.leo.ad.codriver.common.util.DateUtils;

import lombok.RequiredArgsConstructor;

/**
 * DwManager
 *
 * @author HaiYinLong
 * @version 2024/11/29 10:01
 **/
@Component
@RequiredArgsConstructor
public class DwTaskCreator implements TaskCreator {
    private final DynamicTableRepository dynamicTableRepository;
    private final DwTaskRecordService dwTaskRecordService;

    @Override
    public DwTaskRecord createTask(String tableName) {
        Integer dates = DateUtils.getNowDates();
        Long startId = getStartId(dates, tableName);
        DwCountDTO dbCount = dynamicTableRepository.getDbCount(dates, tableName, startId);
        if (ObjectUtils.isEmpty(dbCount)) {
            return null;
        }
        DwTaskRecord taskRecord = DwTaskRecord.of(dates, tableName, dbCount.getMinId(), dbCount.getMaxId());
        dwTaskRecordService.save(taskRecord);
        return taskRecord;
    }

    private Long getStartId(Integer dates, String tableName) {
        DwTaskRecord latestRecord = dwTaskRecordService.getLastTaskRecord(dates, tableName);
        if (latestRecord == null) {
            return null;
        }
        return latestRecord.getEndId() + 1;
    }
}
