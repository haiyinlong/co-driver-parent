package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdQpLtvRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdQpLtvRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdQpLtvRecordServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 18:11
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdQpLtvRecordServiceImpl implements DwdService {
    private final DwdQpLtvRecordMapper dwdQpLtvRecordMapper;
    private final DwBatchMapper<DwdQpLtvRecord, DwdQpLtvRecordMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdQpLtvRecord syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        Integer delRowNum = dwdQpLtvRecordMapper.deleteByDate(dates);
        List<DwdQpLtvRecord> dwdQpLtvRecordList = dwdQpLtvRecordMapper.queryByDate(dates);
        if (CollectionUtils.isEmpty(dwdQpLtvRecordList)) {
            return delRowNum > 0;
        }
        batchMapper.batchInsert(dwdQpLtvRecordList, DwdQpLtvRecordMapper.class);
        return true;
    }
}
