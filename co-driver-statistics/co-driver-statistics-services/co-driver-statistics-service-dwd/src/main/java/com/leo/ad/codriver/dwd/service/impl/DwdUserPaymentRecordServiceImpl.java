package com.leo.ad.codriver.dwd.service.impl;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserPaymentRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserPaymentRecord;
import com.leo.ad.codriver.dwd.event.DwdUserPaymentRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DwdUserPaymentRecordServiceImpl implements DwdService {
    private final DwdUserPaymentRecordMapper dwdUserPaymentRecordMapper;
    private final DwBatchMapper<DwdUserPaymentRecord, DwdUserPaymentRecordMapper> batchMapper;
    private final ExchangeRate exchangeRate;

    @Override
    @ShowExecuteTime(name = "dwdUserPaymentRecord syncData")
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserPaymentRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        DwCountDTO recordCount = dwdUserPaymentRecordMapper.getCountByDates(dates);
        if (recordCount == null || recordCount.getCount() <= 0) {
            return false;
        }
        // 遍历由更新的数据，进行插入或更新；
        int loopNum = recordCount.loopNum();
        long startId;
        long endId;
        List<DwdUserPaymentRecord> userPaymentRecords;
        for (int i = 1; i <= loopNum; i++) {
            startId = recordCount.loopStartId(i);
            endId = recordCount.loopEndId(i);
            userPaymentRecords =
                dwdUserPaymentRecordMapper.queryList(dates, exchangeRate.getIndianToDollar(), startId, endId);
            if (CollectionUtils.isEmpty(userPaymentRecords)) {
                continue;
            }
            batchMapper.batchInsert(userPaymentRecords, DwdUserPaymentRecordMapper.class);
        }
        return true;
    }

}




