package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserOnlineMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserOnline;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * DwdUserOnlineServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/23 19:42
 **/
@Service
@RequiredArgsConstructor
public class DwdUserOnlineServiceImpl implements DwdService {

    private final DwdUserOnlineMapper dwdUserOnlineMapper;
    private final DwBatchMapper<DwdUserOnline, DwdUserOnlineMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserOnline  syncData")
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Long totalRecord = dwdUserOnlineMapper.getCountByDate(dates);
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        List<DwdUserOnline> userOnlineList;
        for (int i = 0; i < totalPageNum; i++) {
            userOnlineList =
                dwdUserOnlineMapper.queryByDate(dates, BatchConst.BATCH_NUMBER, i * BatchConst.BATCH_NUMBER);
            dwBatchMapper.batchInsert(userOnlineList, DwdUserOnlineMapper.class);
        }
        return true;
    }
}
