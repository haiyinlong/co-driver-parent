package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageRegisterMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageRegister;
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
public class DwsDailyPackageRegisterServiceImpl implements DwsService {

    private final DwsDailyPackageRegisterMapper dwsDailyPackageRegisterMapper;
    private final DwBatchMapper<DwsDailyPackageRegister, DwsDailyPackageRegisterMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPackageRegister syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackageRegisterMapper.delete(dates);
        List<DwsDailyPackageRegister> statistics = dwsDailyPackageRegisterMapper.statistics(dates);
        if (CollectionUtils.isEmpty(statistics)) {
            return;
        }
        dwBatchMapper.batchInsert(statistics, DwsDailyPackageRegisterMapper.class);
    }
}
