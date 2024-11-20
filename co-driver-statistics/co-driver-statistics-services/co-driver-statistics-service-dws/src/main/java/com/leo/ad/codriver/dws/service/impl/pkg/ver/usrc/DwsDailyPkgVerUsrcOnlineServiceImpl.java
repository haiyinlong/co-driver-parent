package com.leo.ad.codriver.dws.service.impl.pkg.ver.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcOnlineMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcOnline;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_online】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:31
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcOnlineServiceImpl implements DwsService {

    private final DwsDailyPkgVerUsrcOnlineMapper dwsDailyPkgVerUsrcOnlineMapper;

    private final DwBatchMapper<DwsDailyPkgVerUsrcOnline, DwsDailyPkgVerUsrcOnlineMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgVerUsrcOnline")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgVerUsrcOnline> dbList = dwsDailyPkgVerUsrcOnlineMapper.queryDbList(dates);
        List<DwsDailyPkgVerUsrcOnline> statisticsList = dwsDailyPkgVerUsrcOnlineMapper.queryStatisticsList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        statisticsList.forEach(DwsDailyPkgVerUsrcOnline::init);
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgVerUsrcOnlineMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgVerUsrcOnlineMapper.deleteBatchIds(delIds);
        }
    }
}
