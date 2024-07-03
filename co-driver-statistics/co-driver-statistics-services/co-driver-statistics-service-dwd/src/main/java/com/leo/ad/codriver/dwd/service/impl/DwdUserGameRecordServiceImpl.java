package com.leo.ad.codriver.dwd.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserGameRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserGameRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户游戏记录
 *
 * @author HaiYinLong
 * @version 2024/07/02 15:15
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserGameRecordServiceImpl implements DwdService {
    private final DwdUserGameRecordMapper dwdUserGameRecordMapper;
    private final DwBatchMapper<DwdUserGameRecord, DwdUserGameRecordMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserGameRecord syncData")
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwdUserGameRecordMapper.deleteByDates(dates);
        Long totalRecord = dwdUserGameRecordMapper.getStatisticsCount(dates);
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        for (int i = 0; i < totalPageNum; i++) {
            List<DwdUserGameRecord> statistics = dwdUserGameRecordMapper.queryStatistics(dates,
                    BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            batchMapper.batchInsert(statistics, DwdUserGameRecordMapper.class);
        }
    }

}
