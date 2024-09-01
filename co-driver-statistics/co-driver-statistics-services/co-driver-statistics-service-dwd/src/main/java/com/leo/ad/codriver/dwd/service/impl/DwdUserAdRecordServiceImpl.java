package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserAdRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserAdRecordService
 *
 * @author HaiYinLong
 * @version 2024/08/26 15:18
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdUserAdRecordServiceImpl implements DwdService {
    private final DwdUserAdRecordMapper dwdUserAdRecordMapper;
    private final DwBatchMapper<DwdUserAdRecord, DwdUserAdRecordMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserAdRecord  syncData")
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        Long totalRecord = dwdUserAdRecordMapper.getCountByDate(dates);
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        List<DwdUserAdRecord> userAdRecordList;
        for (int i = 0; i < totalPageNum; i++) {
            userAdRecordList =
                dwdUserAdRecordMapper.queryByDate(dates, BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            userAdRecordList.forEach(DwdUserAdRecord::init);
            dwBatchMapper.batchInsert(userAdRecordList, DwdUserAdRecordMapper.class);
        }
    }
}
