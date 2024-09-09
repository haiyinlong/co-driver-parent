package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserShareRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserShareRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
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
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Long totalRecord = dwdUserShareRecordMapper.getCountByDate(dates);
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        List<DwdUserShareRecord> userShareRecordList;
        for (int i = 0; i < totalPageNum; i++) {
            userShareRecordList =
                dwdUserShareRecordMapper.queryByDate(dates, BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            batchMapper.batchInsert(userShareRecordList, DwdUserShareRecordMapper.class);
        }
        return true;
    }
}
