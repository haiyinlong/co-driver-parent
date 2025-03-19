package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllConversionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllConversion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsDailyPackageAllConversionServiceImpl implements DwsService {

    private final DwsDailyPackageAllConversionMapper dwsDailyPackageAllConversionMapper;
    private final DwBatchMapper<DwsDailyPackageAllConversion, DwsDailyPackageAllConversionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllConversionMapper.deleteByDates(dates);
        List<DwsDailyPackageAllConversion> allConversionActiveList =
            dwsDailyPackageAllConversionMapper.statisticsActiveList(dates);
        dwBatchMapper.batchInsert(allConversionActiveList, DwsDailyPackageAllConversionMapper.class);
        List<DwsDailyPackageAllConversion> allConversionNewList =
            dwsDailyPackageAllConversionMapper.statisticsNewList(dates);
        dwBatchMapper.batchInsert(allConversionNewList, DwsDailyPackageAllConversionMapper.class);
    }
}
