package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabRetentionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabRetention;
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
public class DwsDailyPackageAllLabRetentionServiceImpl implements DwsService {

    private final DwsDailyPackageAllLabRetentionMapper dwsDailyPackageAllLabRetentionMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabRetention, DwsDailyPackageAllLabRetentionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPackageAllLabRetention syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabRetentionMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabRetention> labRetentionList =
            dwsDailyPackageAllLabRetentionMapper.statisticsData(dates);
        dwBatchMapper.batchInsert(labRetentionList, DwsDailyPackageAllLabRetentionMapper.class);
    }
}
