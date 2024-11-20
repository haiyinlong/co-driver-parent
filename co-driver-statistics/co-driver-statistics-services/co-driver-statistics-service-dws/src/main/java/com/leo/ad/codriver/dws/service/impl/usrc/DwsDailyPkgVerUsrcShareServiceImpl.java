package com.leo.ad.codriver.dws.service.impl.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcShareMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcShare;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_share】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:30
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcShareServiceImpl implements DwsService {
    private final DwsDailyPkgVerUsrcShareMapper dwsDailyPkgVerUsrcShareMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcShare, DwsDailyPkgVerUsrcShareMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgVerUsrcShare> dbList = dwsDailyPkgVerUsrcShareMapper.queryDbList(dates);
        List<DwsDailyPkgVerUsrcShare> statisticsList = dwsDailyPkgVerUsrcShareMapper.queryStatisticsList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        statisticsList.forEach(DwsDailyPkgVerUsrcShare::init);
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgVerUsrcShareMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgVerUsrcShareMapper.deleteBatchIds(delIds);
        }
    }
}
