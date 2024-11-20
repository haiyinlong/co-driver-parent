package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcRetentionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcRetention;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_retention】的数据库操作Service实现
 * @createDate 2024-11-20 17:07:05
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcRetentionServiceImpl implements DwsService {
    private final DwsDailyPkgUsrcRetentionMapper dwsDailyPkgUsrcRetentionMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcRetention, DwsDailyPkgUsrcRetentionMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgUsrcRetention")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgUsrcRetention> dbList = dwsDailyPkgUsrcRetentionMapper.queryDbList(dates);
        List<DwsDailyPkgUsrcRetention> statisticsList = dwsDailyPkgUsrcRetentionMapper.queryStatisticsList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgUsrcRetentionMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgUsrcRetentionMapper.deleteBatchIds(delIds);
        }
    }
}
