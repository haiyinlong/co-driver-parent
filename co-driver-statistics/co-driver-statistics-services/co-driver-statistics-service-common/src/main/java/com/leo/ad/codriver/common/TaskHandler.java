package com.leo.ad.codriver.common;

import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwTaskRecordService;

import lombok.RequiredArgsConstructor;

/**
 * TaskHandler
 *
 * @author HaiYinLong
 * @version 2024/11/29 17:07
 **/
@Component
@RequiredArgsConstructor
public class TaskHandler {
    private final DwTaskRecordService dwTaskRecordService;

    public void execute(DwTaskRecord dbTaskRecord, String handleClass) {
        // 执行配置；
        dbTaskRecord.execute();
        dwTaskRecordService.update(dbTaskRecord);
        // handleClass.execute(dbTaskRecord);
        // 更新状态
        dbTaskRecord.done();
        dwTaskRecordService.update(dbTaskRecord);
    }
}
