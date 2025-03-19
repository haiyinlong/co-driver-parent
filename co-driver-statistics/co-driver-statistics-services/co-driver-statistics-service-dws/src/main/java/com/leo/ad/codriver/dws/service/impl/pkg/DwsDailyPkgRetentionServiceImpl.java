package com.leo.ad.codriver.dws.service.impl.pkg;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgRetentionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgRetention;
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
public class DwsDailyPkgRetentionServiceImpl implements DwsService {

    private final DwsDailyPkgRetentionMapper dwsDailyPkgRetentionMapper;
    private final DwBatchMapper<DwsDailyPkgRetention, DwsDailyPkgRetentionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPackageAllRetention syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgRetentionMapper.deleteByDates(dates);
        List<DwsDailyPkgRetention> pkgRetentionList = dwsDailyPkgRetentionMapper.statisticsPkgRetention(dates);
        dwBatchMapper.batchInsert(pkgRetentionList, DwsDailyPkgRetentionMapper.class);
    }
}
