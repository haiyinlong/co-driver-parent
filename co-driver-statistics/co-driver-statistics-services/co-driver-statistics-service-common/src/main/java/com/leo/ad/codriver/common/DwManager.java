package com.leo.ad.codriver.common;

import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.dao.DynamicTableMapper;
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwTaskRecordService;

import lombok.RequiredArgsConstructor;

/**
 * DwManager
 *
 * @author HaiYinLong
 * @version 2024/11/29 10:01
 **/
@Component
@RequiredArgsConstructor
public class DwManager implements Manager {
    private final DynamicTableMapper dynamicTableMapper;
    private final DwTaskRecordService dwTaskRecordService;

    @Override
    public void createTask(Integer dates, String tableName) {
        Long startId = getStartId(dates, tableName);
        DwCountDTO dbCount = dynamicTableMapper.getDbCount(dates, tableName, startId);
        DwTaskRecord taskRecord = DwTaskRecord.ofOetaGameRecord(dates, dbCount.getMinId(), dbCount.getMaxId());
        dwTaskRecordService.save(taskRecord);
    }

    private Long getStartId(Integer dates, String tableName) {
        DwTaskRecord latestRecord = dwTaskRecordService.getLastTaskRecord(dates, tableName);
        if (latestRecord == null) {
            return null;
        }
        return latestRecord.getEndId() + 1;
    }
}
