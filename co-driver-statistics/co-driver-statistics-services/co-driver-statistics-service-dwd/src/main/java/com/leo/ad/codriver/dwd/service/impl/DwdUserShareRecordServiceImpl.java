package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserShareRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserShareRecord;
import com.leo.ad.codriver.dwd.event.DwdUserShareRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserShareRecordServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/26 14:21
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdUserShareRecordServiceImpl implements DwdService {
    private final DwdUserShareRecordMapper dwdUserShareRecordMapper;
    private final DwBatchMapper<DwdUserShareRecord, DwdUserShareRecordMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserShareRecord  syncData")
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserShareRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        DwCountDTO recordCount = dwdUserShareRecordMapper.getCountByDate(dates);
        if (recordCount == null || recordCount.getCount() <= 0) {
            log.info("DwdUserShareRecordServiceImpl {} 没有需要同步的数据", recordCount);
            return false;
        }
        // 遍历由更新的数据，进行插入或更新；
        int loopNum = recordCount.loopNum();
        long startId;
        long endId;
        List<DwdUserShareRecord> userShareRecordList;
        for (int i = 1; i <= loopNum; i++) {
            startId = recordCount.loopStartId(i);
            endId = recordCount.loopEndId(i);
            userShareRecordList = dwdUserShareRecordMapper.queryByDate(dates, startId, endId);
            batchMapper.batchInsert(userShareRecordList, DwdUserShareRecordMapper.class);
            if (CollectionUtils.isEmpty(userShareRecordList)) {
                continue;
            }
            batchMapper.batchInsert(userShareRecordList, DwdUserShareRecordMapper.class);
        }
        return true;

    }
}
