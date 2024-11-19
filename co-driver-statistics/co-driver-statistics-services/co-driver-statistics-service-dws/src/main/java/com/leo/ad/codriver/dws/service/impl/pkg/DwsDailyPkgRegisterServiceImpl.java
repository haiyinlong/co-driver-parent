package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgRegisterMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgRegister;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * DwsServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
@Order(1)
public class DwsDailyPkgRegisterServiceImpl implements DwsService {

    private final DwsDailyPkgRegisterMapper dwsDailyPkgRegisterMapper;
    private final DwBatchMapper<DwsDailyPkgRegister, DwsDailyPkgRegisterMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPackageAllRegister syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgRegisterMapper.deleteByDates(dates);
        List<DwsDailyPkgRegister> statistics = dwsDailyPkgRegisterMapper.statistics(dates);
        if (CollectionUtils.isEmpty(statistics)) {
            return;
        }
        dwBatchMapper.batchInsert(statistics, DwsDailyPkgRegisterMapper.class);
    }
}
