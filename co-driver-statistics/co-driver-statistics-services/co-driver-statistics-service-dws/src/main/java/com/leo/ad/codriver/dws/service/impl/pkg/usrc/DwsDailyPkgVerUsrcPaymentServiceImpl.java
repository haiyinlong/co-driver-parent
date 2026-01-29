package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcPaymentMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcPayment;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcPaymentServiceImpl implements DwsService {
    private final DwsDailyPkgVerUsrcPaymentMapper dwsDailyPkgVerUsrcPaymentMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcPayment, DwsDailyPkgVerUsrcPaymentMapper> dwBatchMapper;

    @ShowExecuteTime(name = "DwsDailyPkgVerUsrcPayment")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgVerUsrcPayment> dbList = dwsDailyPkgVerUsrcPaymentMapper.queryList(dates);
        List<DwsDailyPkgVerUsrcPayment> statisticsActiveList =
            dwsDailyPkgVerUsrcPaymentMapper.queryStatisticsActiveList(dates);
        if (CollectionUtils.isEmpty(statisticsActiveList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsActiveList, DwsDailyPkgVerUsrcPaymentMapper.class);
        // 新用户
        List<DwsDailyPkgVerUsrcPayment> statisticsNewList =
            dwsDailyPkgVerUsrcPaymentMapper.queryStatisticsNewList(dates);
        if (CollectionUtils.isEmpty(statisticsNewList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsNewList, DwsDailyPkgVerUsrcPaymentMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsActiveList, statisticsNewList);

        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgVerUsrcPaymentMapper.deleteBatchIds(delIds);
        }
    }
}




