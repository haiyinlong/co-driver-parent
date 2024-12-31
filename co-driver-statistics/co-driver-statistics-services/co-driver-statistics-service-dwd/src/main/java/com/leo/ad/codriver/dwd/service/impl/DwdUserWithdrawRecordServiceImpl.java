package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserWithdrawRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserWithdrawRecord;
import com.leo.ad.codriver.dwd.event.DwdUserWithdrawRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
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
public class DwdUserWithdrawRecordServiceImpl implements DwdService {
    private final DwdUserWithdrawRecordMapper dwdUserWithdrawRecordMapper;
    private final DwBatchMapper<DwdUserWithdrawRecord, DwdUserWithdrawRecordMapper> batchMapper;
    private final ExchangeRate exchangeRate;

    @Override
    @ShowExecuteTime(name = "dwdUserWithdrawRecord syncData")
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserWithdrawRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        String dateStr = DateUtils.toDateString(dates);
        DwCountDTO recordCount = dwdUserWithdrawRecordMapper.getWithdrawCountByUpdateDate(dateStr);
        if (recordCount == null || recordCount.getCount() <= 0) {
            return false;
        }
        // 遍历由更新的数据，进行插入或更新；
        int loopNum = recordCount.loopNum();
        long startId;
        long endId;
        List<DwdUserWithdrawRecord> userWithdrawRecords;
        for (int i = 1; i <= loopNum; i++) {
            startId = recordCount.loopStartId(i);
            endId = recordCount.loopEndId(i);
            // 同步数据，根据状态更新字段； 成功、失败；补全数据生命周期数据；
            userWithdrawRecords = dwdUserWithdrawRecordMapper.queryWithdrawList(dates, dateStr,
                exchangeRate.getIndianToDollar(), startId, endId);
            if (CollectionUtils.isEmpty(userWithdrawRecords)) {
                continue;
            }
            batchMapper.batchInsert(userWithdrawRecords, DwdUserWithdrawRecordMapper.class);
        }
        return true;
    }

}
