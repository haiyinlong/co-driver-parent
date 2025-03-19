package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabConversionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabConversion;
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
public class DwsDailyPackageAllLabConversionServiceImpl implements DwsService {

    private final DwsDailyPackageAllLabConversionMapper dwsDailyPackageAllLabConversionMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabConversion, DwsDailyPackageAllLabConversionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabConversionMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabConversion> labConversionActiveList =
            dwsDailyPackageAllLabConversionMapper.statisticsActiveList(dates);
        dwBatchMapper.batchInsert(labConversionActiveList, DwsDailyPackageAllLabConversionMapper.class);
        List<DwsDailyPackageAllLabConversion> labConversionNewList =
            dwsDailyPackageAllLabConversionMapper.statisticsNewList(dates);
        dwBatchMapper.batchInsert(labConversionNewList, DwsDailyPackageAllLabConversionMapper.class);
    }
}
