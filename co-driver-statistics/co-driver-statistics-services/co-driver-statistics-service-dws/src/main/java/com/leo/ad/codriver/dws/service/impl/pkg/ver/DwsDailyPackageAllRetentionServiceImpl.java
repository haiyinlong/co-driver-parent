package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllRetentionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllRetention;
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
public class DwsDailyPackageAllRetentionServiceImpl implements DwsService {

    private final DwsDailyPackageAllRetentionMapper dwsDailyPackageAllRetentionMapper;
    private final DwBatchMapper<DwsDailyPackageAllRetention, DwsDailyPackageAllRetentionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPackageAllRetention syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllRetentionMapper.deleteByDates(dates);
        List<DwsDailyPackageAllRetention> allRetentionList =
            dwsDailyPackageAllRetentionMapper.statisticsPkgLogin(dates);
        dwBatchMapper.batchInsert(allRetentionList, DwsDailyPackageAllRetentionMapper.class);
    }
}
