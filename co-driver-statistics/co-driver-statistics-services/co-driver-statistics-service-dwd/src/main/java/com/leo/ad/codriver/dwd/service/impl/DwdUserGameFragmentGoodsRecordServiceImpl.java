package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserGameFragmentGoodsRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserGameFragmentGoodsRecord;
import com.leo.ad.codriver.dwd.event.DwdUserGameFragmentGoodsRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserGameFragmentGoodsRecordServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/05/12 16:44
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdUserGameFragmentGoodsRecordServiceImpl implements DwdService {
    private final DwdUserGameFragmentGoodsRecordMapper dwdUserGameFragmentGoodsRecordMapper;
    private final DwBatchMapper<DwdUserGameFragmentGoodsRecord, DwdUserGameFragmentGoodsRecordMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserGameFragmentGoodsRecord syncData")
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserGameFragmentGoodsRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        DwCountDTO statisticsCount = dwdUserGameFragmentGoodsRecordMapper.getOdsStatisticsCount(dates);
        if (ObjectUtils.isEmpty(statisticsCount) || ObjectUtils.isEmpty(statisticsCount.getMinId())) {
            return false;
        }
        int loopNum = statisticsCount.loopNum();
        long startId;
        long endId;
        List<DwdUserGameFragmentGoodsRecord> gameFragmentGoodsRecords;
        for (int i = 1; i <= loopNum; i++) {
            startId = statisticsCount.loopStartId(i);
            endId = statisticsCount.loopEndId(i);
            gameFragmentGoodsRecords =
                dwdUserGameFragmentGoodsRecordMapper.queryOdsStatisticsInterval(dates, startId, endId);
            if (CollectionUtils.isEmpty(gameFragmentGoodsRecords)) {
                continue;
            }
            // 转化数据，入库
            dwBatchMapper.batchInsert(gameFragmentGoodsRecords, DwdUserGameFragmentGoodsRecordMapper.class);
        }
        return true;
    }
}
