package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsUserRegisterPkgFullDailyMapper;
import com.leo.ad.codriver.dws.entity.DwsUserRegisterPkgFullDaily;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * DwsServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
@Order(1)
public class DwsUserRegisterPkgFullDailyServiceImpl implements DwsService {

    private final DwsUserRegisterPkgFullDailyMapper dwsUserRegisterPkgFullDailyMapper;
    private final DwBatchMapper<DwsUserRegisterPkgFullDaily, DwsUserRegisterPkgFullDailyMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwsUserRegisterPkgFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsUserRegisterPkgFullDailyMapper.deleteByDates(dates);
        List<DwsUserRegisterPkgFullDaily> statistics = dwsUserRegisterPkgFullDailyMapper.statistics(dates);
        if (CollectionUtils.isEmpty(statistics)) {
            return;
        }
        dwBatchMapper.batchInsert(statistics, DwsUserRegisterPkgFullDailyMapper.class);
    }
}
