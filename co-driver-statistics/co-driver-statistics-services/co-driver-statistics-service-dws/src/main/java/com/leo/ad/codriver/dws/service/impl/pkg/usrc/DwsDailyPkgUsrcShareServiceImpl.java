package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcShareMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcShare;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_share】的数据库操作Service实现
 * @createDate 2024-11-20 17:07:05
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcShareServiceImpl implements DwsService {
    private final DwsDailyPkgUsrcShareMapper dwsDailyPkgUsrcShareMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcShare, DwsDailyPkgUsrcShareMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgUsrcShare")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgUsrcShare> dbList = dwsDailyPkgUsrcShareMapper.queryDbList(dates);
        List<DwsDailyPkgUsrcShare> statisticsList = dwsDailyPkgUsrcShareMapper.queryStatisticsList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        statisticsList.forEach(DwsDailyPkgUsrcShare::init);
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgUsrcShareMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgUsrcShareMapper.deleteBatchIds(delIds);
        }
    }
}
