package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserAdRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.dwd.event.DwdUserAdRecordUpdateDwEvent;
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
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserAdRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        DwCountDTO dbCount = dwdUserAdRecordMapper.getDbCount(dates);
        DwCountDTO statisticsCount = dwdUserAdRecordMapper.getStatisticsCount(dates);
        if (!isExistsDiff(dbCount, statisticsCount)) {
            return false;
        }
        try {
            long startSourceId = getStartSourceId(dbCount);
            List<DwdUserAdRecord> userAdRecordList;
            List<DwdUserAdRecord> newList;
            do {
                userAdRecordList =
                    dwdUserAdRecordMapper.queryStatisticsByDate(dates, BatchConst.BATCH_NUMBER, startSourceId);
                // 过滤掉已经有id的数据
                newList =
                    userAdRecordList.stream().filter(userAdRecordItem -> ObjectUtils.isEmpty(userAdRecordItem.getId()))
                        .peek(DwdUserAdRecord::init).toList();
                if(!CollectionUtils.isEmpty(newList)){
                    dwBatchMapper.batchInsert(newList, DwdUserAdRecordMapper.class);
                    startSourceId = newList.stream().map(DwdUserAdRecord::getSourceId).sorted().toList().get(newList.size() - 1);
                }
            } while (!CollectionUtils.isEmpty(userAdRecordList));
        } catch (Exception e) {
            log.error("dwdUserAdRecord  syncData error", e);
            throw e;
        }
        return true;
    }

    private long getStartSourceId(DwCountDTO dbCount) {
        if (!ObjectUtils.isEmpty(dbCount) && !ObjectUtils.isEmpty(dbCount.getMaxId())) {
            return dbCount.getMaxId();
        }
        return 0L;
    }

    private boolean isExistsDiff(DwCountDTO dbCount, DwCountDTO statisticsCount) {
        if (ObjectUtils.isEmpty(statisticsCount) || ObjectUtils.isEmpty(statisticsCount.getMinId())) {
            log.info("dwdUserAdRecord 统计对象为空,不执行同步");
            return false;
        }
        if (!ObjectUtils.isEmpty(dbCount) && !ObjectUtils.isEmpty(dbCount.getMaxId()) && Objects.equals(dbCount.getMaxId(), statisticsCount.getMaxId())) {
            log.info("dwdUserAdRecord 数据对象和统计对象的最大记录Id相同，不执行同步");
            return false;
        }
        return true;
    }

}
