package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserExchangeRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserExchangeRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserExchangeRecordServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/26 16:15
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdUserExchangeRecordServiceImpl implements DwdService {
    private final DwdUserExchangeRecordMapper dwdUserExchangeRecordMapper;
    private final DwBatchMapper<DwdUserExchangeRecord, DwdUserExchangeRecordMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserExchangeRecord  syncData")
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Long totalRecord = dwdUserExchangeRecordMapper.getCountByDate(dates);
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        List<DwdUserExchangeRecord> userExchangeRecordList;
        for (int i = 0; i < totalPageNum; i++) {
            userExchangeRecordList =
                dwdUserExchangeRecordMapper.queryByDate(dates, BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            dwBatchMapper.batchInsert(userExchangeRecordList, DwdUserExchangeRecordMapper.class);
        }
        return true;
    }
}
