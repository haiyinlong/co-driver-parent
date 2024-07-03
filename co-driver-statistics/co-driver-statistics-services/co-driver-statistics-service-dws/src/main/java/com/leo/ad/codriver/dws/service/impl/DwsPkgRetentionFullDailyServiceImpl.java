package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsPkgRetentionFullDailyMapper;
import com.leo.ad.codriver.dws.entity.DwsPkgRetentionFullDaily;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DwsPkgRetentionFullDailyServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsPkgRetentionFullDailyServiceImpl implements DwsService {

    private final DwsPkgRetentionFullDailyMapper dwsPkgRetentionFullDailyMapper;
    private final DwBatchMapper<DwsPkgRetentionFullDaily, DwsPkgRetentionFullDailyMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwsPkgRetentionFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        List<DwsPkgRetentionFullDaily> statistics = dwsPkgRetentionFullDailyMapper.statistics(dates);
        batchMapper.batchInsert(statistics, DwsPkgRetentionFullDailyMapper.class);
    }
}
