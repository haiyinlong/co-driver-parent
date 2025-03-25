package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserOnlineMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserOnline;
import com.leo.ad.codriver.dwd.event.DwdUserOnlineRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserOnlineServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/23 19:42
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdUserOnlineServiceImpl implements DwdService {

    private final DwdUserOnlineMapper dwdUserOnlineMapper;
    private final DwBatchMapper<DwdUserOnline, DwdUserOnlineMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserOnline  syncData")
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserOnlineRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        // TODO 改为任务同步
        Long totalRecord = dwdUserOnlineMapper.getCountByDate(dates);
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_MAX_NUMBER.longValue());
        List<DwdUserOnline> userOnlineList;
        for (int i = 0; i < totalPageNum; i++) {
            userOnlineList =
                dwdUserOnlineMapper.queryByDate(dates, BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            try {
                // userOnlineList.stream().peek()
                dwBatchMapper.batchInsert(userOnlineList, DwdUserOnlineMapper.class);
            } catch (Exception e) {
                String errorObj =
                    userOnlineList.stream().map(dwdUserOnline -> dwdUserOnline.getId() + dwdUserOnline.getVersion())
                        .collect(Collectors.joining(","));
                log.error("dwdUserOnline  syncData error,errorObj:{}", errorObj);
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
