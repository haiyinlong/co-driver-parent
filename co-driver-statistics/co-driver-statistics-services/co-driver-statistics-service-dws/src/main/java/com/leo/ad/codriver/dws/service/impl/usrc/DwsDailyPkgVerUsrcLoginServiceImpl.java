package com.leo.ad.codriver.dws.service.impl.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcLoginMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcLogin;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_login】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:31
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcLoginServiceImpl implements DwsService {

    private final DwsDailyPkgVerUsrcLoginMapper dwsDailyPkgVerUsrcLoginMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcLogin, DwsDailyPkgVerUsrcLoginMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgVerUsrcLogin> dbList = dwsDailyPkgVerUsrcLoginMapper.queryDbList(dates);
        List<DwsDailyPkgVerUsrcLogin> statisticsList = dwsDailyPkgVerUsrcLoginMapper.queryStatisticsList(dates);
        if (!CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgVerUsrcLoginMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        dwsDailyPkgVerUsrcLoginMapper.deleteBatchIds(delIds);
    }
}
