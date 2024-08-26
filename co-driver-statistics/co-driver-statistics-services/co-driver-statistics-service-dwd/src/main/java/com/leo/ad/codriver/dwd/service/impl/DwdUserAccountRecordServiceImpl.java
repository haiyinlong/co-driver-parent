package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserAccountRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserAccountRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HaiYinLong
 * @version 2024/04/18 16:47
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserAccountRecordServiceImpl implements DwdService {
    private final DwdUserAccountRecordMapper dwdUserAccountRecordMapper;
    private final DwBatchMapper<DwdUserAccountRecord, DwdUserAccountRecordMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserAccountRecord syncData")
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 先删除数据
        dwdUserAccountRecordMapper.deleteByDates(dates);
        // 查询统计总数据，然后分页进行获取
        long recordCount = dwdUserAccountRecordMapper.getRecordCount(dates);
        long totalPage = LongUtils.divide(recordCount, BatchConst.BATCH_NUMBER.longValue());
        if (totalPage <= 0) {
            return;
        }
        List<DwdUserAccountRecord> userAccountRecords;
        for (int i = 1; i <= totalPage; i++) {
            userAccountRecords = dwdUserAccountRecordMapper.queryStatistics(dates, BatchConst.BATCH_NUMBER,
                (int)((i - 1) * BatchConst.BATCH_NUMBER));
            batchMapper.batchInsert(userAccountRecords, DwdUserAccountRecordMapper.class);
        }
    }

}
