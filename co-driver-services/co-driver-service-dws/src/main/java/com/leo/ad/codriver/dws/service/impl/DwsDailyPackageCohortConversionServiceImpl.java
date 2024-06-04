package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyCohortConversionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortConversion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 每天同期转化统计
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
@Order
public class DwsDailyPackageCohortConversionServiceImpl implements DwsService {
    private final DwsDailyCohortConversionMapper dwsDailyCohortConversionMapper;
    private final DwBatchMapper<DwsDailyCohortConversion, DwsDailyCohortConversionMapper> dwBatchMapper;

    /**
     * 统计依赖于dws_daily_package_register,需要在dws_daily_package_register同步完成之后执行
     *
     * @param dates 统计日期
     */
    @Override
    @ShowExecuteTime(name = "dwsDailyPackageCohortConversion")
    @Transactional(rollbackFor = Exception.class)
    public void syncData(Integer dates) {
        dwsDailyCohortConversionMapper.delete(dates);
        List<DwsDailyCohortConversion> dwsDailyCohortConversions =
                dwsDailyCohortConversionMapper.statisticsCohortConversion(dates);
        dwBatchMapper.batchInsert(dwsDailyCohortConversions, DwsDailyCohortConversionMapper.class);
    }
}
