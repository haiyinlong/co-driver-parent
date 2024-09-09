package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserLtvRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserLtvRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserLtbRecordServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/26 10:41
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdUserLtvRecordServiceImpl implements DwdService {
    private final DwdUserLtvRecordMapper dwdUserLtvRecordMapper;
    private final DwBatchMapper<DwdUserLtvRecord, DwdUserLtvRecordMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserLtvRecord  syncData")
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Long totalRecord = dwdUserLtvRecordMapper.getCountByDate(dates);
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        List<DwdUserLtvRecord> userLtvRecordList;
        for (int i = 0; i < totalPageNum; i++) {
            userLtvRecordList =
                dwdUserLtvRecordMapper.queryByDate(dates, BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            dwBatchMapper.batchInsert(userLtvRecordList, DwdUserLtvRecordMapper.class);
        }
        return true;
    }
}
