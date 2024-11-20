package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcWithdraw;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_withdraw】的数据库操作Service实现
 * @createDate 2024-11-20 17:07:05
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcWithdrawServiceImpl implements DwsService {
    private final DwsDailyPkgUsrcWithdrawMapper dwsDailyPkgUsrcWithdrawMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcWithdraw, DwsDailyPkgUsrcWithdrawMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgUsrcWithdraw")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgUsrcWithdraw> dbList = dwsDailyPkgUsrcWithdrawMapper.queryList(dates);
        List<DwsDailyPkgUsrcWithdraw> statisticsActiveList =
            dwsDailyPkgUsrcWithdrawMapper.queryStatisticsActiveList(dates);
        if (CollectionUtils.isEmpty(statisticsActiveList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsActiveList, DwsDailyPkgUsrcWithdrawMapper.class);
        // 新用户
        List<DwsDailyPkgUsrcWithdraw> statisticsNewList = dwsDailyPkgUsrcWithdrawMapper.queryStatisticsNewList(dates);
        if (CollectionUtils.isEmpty(statisticsNewList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsNewList, DwsDailyPkgUsrcWithdrawMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsActiveList, statisticsNewList);

        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgUsrcWithdrawMapper.deleteBatchIds(delIds);
        }
    }
}
