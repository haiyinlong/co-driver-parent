package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgConversionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgConversion;
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
public class DwsDailyPkgConversionServiceImpl implements DwsService {

    private final DwsDailyPkgConversionMapper dwsDailyPkgConversionMapper;
    private final DwBatchMapper<DwsDailyPkgConversion, DwsDailyPkgConversionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgConversionMapper.deleteByDates(dates);
        List<DwsDailyPkgConversion> activeList = dwsDailyPkgConversionMapper.statisticsActiveList(dates);
        dwBatchMapper.batchInsert(activeList, DwsDailyPkgConversionMapper.class);
        List<DwsDailyPkgConversion> newList = dwsDailyPkgConversionMapper.statisticsNewList(dates);
        dwBatchMapper.batchInsert(newList, DwsDailyPkgConversionMapper.class);
    }
}
