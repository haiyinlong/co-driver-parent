package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabRegisterMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabRegister;
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
public class DwsDailyPackageAllLabRegisterServiceImpl implements DwsService {

    private final DwsDailyPackageAllLabRegisterMapper dwsDailyPackageAllLabRegisterMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabRegister, DwsDailyPackageAllLabRegisterMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPackageAllLabRegister syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabRegisterMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabRegister> statistics = dwsDailyPackageAllLabRegisterMapper.statistics(dates);
        if (CollectionUtils.isEmpty(statistics)) {
            return;
        }
        dwBatchMapper.batchInsert(statistics, DwsDailyPackageAllLabRegisterMapper.class);
    }
}
