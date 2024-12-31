package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserAdRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.dwd.event.DwdUserAdRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO 注意，数据会有滞后，目前解决办法是1点的时候再单独执行<br/>
 * applovin 返回的数据gaid有可能为空，关联不到用户id，导致具体版本统计数据有误差，可以看ALL版本的数据是版本gaid为空的。
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
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserAdRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        DwCountDTO statisticsCount = dwdUserAdRecordMapper.getStatisticsCount(dates);
        if (ObjectUtils.isEmpty(statisticsCount) || ObjectUtils.isEmpty(statisticsCount.getMinId())) {
            log.info("{} dwdUserAdRecord 统计对象为空,不执行同步", dates);
            return false;
        }
        try {
            long startSourceId = statisticsCount.getMinId();
            long endSourceId = startSourceId + BatchConst.BATCH_MAX_NUMBER;
            List<DwdUserAdRecord> userAdRecordList;
            do {
                userAdRecordList = dwdUserAdRecordMapper.queryStatisticsByDate(dates, startSourceId, endSourceId);
                startSourceId = endSourceId + 1;
                endSourceId += BatchConst.BATCH_MAX_NUMBER;
                if (endSourceId > statisticsCount.getMaxId()) {
                    endSourceId = statisticsCount.getMaxId();
                }
                if (CollectionUtils.isEmpty(userAdRecordList)) {
                    continue;
                }
                userAdRecordList.forEach(DwdUserAdRecord::init);
                dwBatchMapper.batchInsert(userAdRecordList, DwdUserAdRecordMapper.class);
            } while (startSourceId < statisticsCount.getMaxId());
        } catch (Exception e) {
            log.error("dwdUserAdRecord  syncData error", e);
            throw e;
        }
        return true;
    }

}
