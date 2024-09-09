package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserRegisterMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserRegister;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:15
 **/
@Service
@AllArgsConstructor
@Slf4j
@Order(Integer.MIN_VALUE)
public class DwdUserRegisterServiceImpl implements DwdService {
    private final DwdUserRegisterMapper dwdUserRegisterMapper;
    private final DwBatchMapper<DwdUserRegister, DwdUserRegisterMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserRegister syncData")
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Integer delRowNum = dwdUserRegisterMapper.deleteByDates(dates);
        Long totalRecord = dwdUserRegisterMapper.getStatisticsCount(dates);
        if (totalRecord <= 0) {
            return delRowNum > 0;
        }
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        for (int i = 0; i < totalPageNum; i++) {
            List<DwdUserRegister> statistics = dwdUserRegisterMapper.statistics(dates,
                BatchConst.BATCH_NUMBER.intValue(), i * BatchConst.BATCH_NUMBER.intValue());
            batchMapper.batchInsert(statistics, DwdUserRegisterMapper.class);
        }
        return true;
    }
}
