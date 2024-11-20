package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcLoginMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcLogin;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_login】的数据库操作Service实现
 * @createDate 2024-11-20 17:07:05
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcLoginServiceImpl implements DwsService {
    private final DwsDailyPkgUsrcLoginMapper dwsDailyPkgUsrcLoginMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcLogin, DwsDailyPkgUsrcLoginMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgUsrcLogin")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgUsrcLogin> dbList = dwsDailyPkgUsrcLoginMapper.queryDbList(dates);
        List<DwsDailyPkgUsrcLogin> statisticsList = dwsDailyPkgUsrcLoginMapper.queryStatisticsList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgUsrcLoginMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgUsrcLoginMapper.deleteBatchIds(delIds);
        }
    }
}
