package com.leo.ad.codriver.common;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.dao.DwSyncHandleMapper;
import com.leo.ad.codriver.common.dao.entity.DwSyncHandle;
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwTaskRecordService;

import lombok.RequiredArgsConstructor;

/**
 * DwExecutor
 *
 * @author HaiYinLong
 * @version 2024/11/29 16:19
 **/
@Component
@RequiredArgsConstructor
public class DwExecutor implements Executor {
    private final DwTaskRecordService dwTaskRecordService;
    private final TaskHandler taskHandler;
    private final DwSyncHandleMapper dwSyncHandleMapper;

    // @PostConstruct
    public void initialize() {
        // 获取执行配置；
        // 根据最新的待执行任务；
        DwTaskRecord dbTaskRecord = null;
        DwSyncHandle dwSyncHandle = null;
        while (true) {
            dbTaskRecord = dwTaskRecordService.getOneTaskRecord();
            if (ObjectUtils.isEmpty(dbTaskRecord)) {
                try {
                    TimeUnit.SECONDS.sleep(60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            dwSyncHandle = dwSyncHandleMapper.queryByTableName(dbTaskRecord.getType());
            taskHandler.execute(dbTaskRecord, dwSyncHandle.getHandleClass());
        }
    }
}
