package com.leo.ad.codriver.dws.service.impl.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcWithdraw;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_withdraw】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:31
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcWithdrawServiceImpl implements DwsService {
    private final DwsDailyPkgVerUsrcWithdrawMapper dwsDailyPkgVerUsrcWithdrawMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcWithdraw, DwsDailyPkgVerUsrcWithdrawMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgVerUsrcWithdraw> dbList = dwsDailyPkgVerUsrcWithdrawMapper.queryList(dates);
        List<DwsDailyPkgVerUsrcWithdraw> statisticsActiveList =
            dwsDailyPkgVerUsrcWithdrawMapper.queryStatisticsActiveList(dates);
        if (CollectionUtils.isEmpty(statisticsActiveList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsActiveList, DwsDailyPkgVerUsrcWithdrawMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsActiveList, null);
        dwsDailyPkgVerUsrcWithdrawMapper.deleteBatchIds(delIds);
        // 新用户
        List<DwsDailyPkgVerUsrcWithdraw> statisticsNewList =
            dwsDailyPkgVerUsrcWithdrawMapper.queryStatisticsNewList(dates);
        if (CollectionUtils.isEmpty(statisticsActiveList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsNewList, DwsDailyPkgVerUsrcWithdrawMapper.class);
        delIds = getDelIds(dbList, statisticsNewList, null);

        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgVerUsrcWithdrawMapper.deleteBatchIds(delIds);
        }
    }
}
