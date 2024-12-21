package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserBalanceRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserBalanceRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 河马使用到的数据，暂时没有应用场景就不做优化，还是每天定时处理
 *
 * @author HaiYinLong
 * @version 2024/04/18 16:47
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserBalanceRecordServiceImpl implements DwdService {
    private final DwdUserBalanceRecordMapper dwdUserBalanceRecordMapper;
    private final DwBatchMapper<DwdUserBalanceRecord, DwdUserBalanceRecordMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserBalanceRecord syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        // 查询统计总数据，然后分页进行获取
        long recordCount = dwdUserBalanceRecordMapper.getRecordCount();
        long totalPage = LongUtils.divide(recordCount, BatchConst.BATCH_NUMBER.longValue());
        if (totalPage <= 0) {
            return false;
        }
        List<DwdUserBalanceRecord> userAccountRecords;
        for (int i = 1; i <= totalPage; i++) {
            userAccountRecords = dwdUserBalanceRecordMapper.queryStatistics(BatchConst.BATCH_NUMBER.intValue(),
                ((i - 1) * BatchConst.BATCH_NUMBER));
            batchMapper.batchInsert(userAccountRecords, DwdUserBalanceRecordMapper.class);
        }
        return true;
    }

}
