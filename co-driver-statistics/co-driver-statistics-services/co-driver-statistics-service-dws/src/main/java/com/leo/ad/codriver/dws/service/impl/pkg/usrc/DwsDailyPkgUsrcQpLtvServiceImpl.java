package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcQpLtvMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcQpLtv;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_qp_ltv】的数据库操作Service实现
 * @createDate 2024-11-20 17:07:05
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcQpLtvServiceImpl implements DwsService {

    private final DwsDailyPkgUsrcQpLtvMapper dwsDailyPkgUsrcQpLtvMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcQpLtv, DwsDailyPkgUsrcQpLtvMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgUsrcQpLtv")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgUsrcQpLtv> dbList = dwsDailyPkgUsrcQpLtvMapper.queryDbList(dates);
        List<DwsDailyPkgUsrcQpLtv> statisticsList = dwsDailyPkgUsrcQpLtvMapper.queryStatisticList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        statisticsList.forEach(DwsDailyPkgUsrcQpLtv::init);
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgUsrcQpLtvMapper.class);

        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgUsrcQpLtvMapper.deleteBatchIds(delIds);
        }
    }
}
