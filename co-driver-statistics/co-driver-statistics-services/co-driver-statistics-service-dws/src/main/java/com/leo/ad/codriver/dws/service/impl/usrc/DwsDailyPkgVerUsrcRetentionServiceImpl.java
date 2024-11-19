package com.leo.ad.codriver.dws.service.impl.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcRetentionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcRetention;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_retention】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:31
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcRetentionServiceImpl implements DwsService {
    private final DwsDailyPkgVerUsrcRetentionMapper dwsDailyPkgVerUsrcRetentionMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcRetention, DwsDailyPkgVerUsrcRetentionMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgVerUsrcRetention> dbList = dwsDailyPkgVerUsrcRetentionMapper.queryDbList(dates);
        List<DwsDailyPkgVerUsrcRetention> statisticsList = dwsDailyPkgVerUsrcRetentionMapper.queryStatisticsList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgVerUsrcRetentionMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        dwsDailyPkgVerUsrcRetentionMapper.deleteBatchIds(delIds);
    }
}
