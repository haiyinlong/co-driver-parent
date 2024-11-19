package com.leo.ad.codriver.dws.service.impl.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcQpLtvMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcQpLtv;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_qp_ltv】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:31
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcQpLtvServiceImpl implements DwsService {

    private final DwsDailyPkgVerUsrcQpLtvMapper dwsDailyPkgVerUsrcQpLtvMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcQpLtv, DwsDailyPkgVerUsrcQpLtvMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgVerUsrcQpLtv> dbList = dwsDailyPkgVerUsrcQpLtvMapper.queryDbList(dates);
        List<DwsDailyPkgVerUsrcQpLtv> statisticsLit = dwsDailyPkgVerUsrcQpLtvMapper.queryStatisticList(dates);
        if (CollectionUtils.isEmpty(statisticsLit)) {
            return;
        }
        statisticsLit.forEach(DwsDailyPkgVerUsrcQpLtv::init);
        dwBatchMapper.batchInsert(statisticsLit, DwsDailyPkgVerUsrcQpLtvMapper.class);

        List<Long> delIds = getDelIds(dbList, statisticsLit, null);
        dwsDailyPkgVerUsrcQpLtvMapper.deleteBatchIds(delIds);
    }
}
