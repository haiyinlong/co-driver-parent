package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcPaymentMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcPayment;
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
public class DwsDailyPkgUsrcPaymentServiceImpl implements DwsService {
    private final DwsDailyPkgUsrcPaymentMapper dwsDailyPkgUsrcPaymentMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcPayment, DwsDailyPkgUsrcPaymentMapper> dwBatchMapper;

    @ShowExecuteTime(name = "DwsDailyPkgUsrcPayment")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgUsrcPayment> dbList = dwsDailyPkgUsrcPaymentMapper.queryList(dates);
        List<DwsDailyPkgUsrcPayment> statisticsActiveList =
            dwsDailyPkgUsrcPaymentMapper.queryStatisticsActiveList(dates);
        if (CollectionUtils.isEmpty(statisticsActiveList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsActiveList, DwsDailyPkgUsrcPaymentMapper.class);
        // 新用户
        List<DwsDailyPkgUsrcPayment> statisticsNewList = dwsDailyPkgUsrcPaymentMapper.queryStatisticsNewList(dates);
        if (CollectionUtils.isEmpty(statisticsNewList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsNewList, DwsDailyPkgUsrcPaymentMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsActiveList, statisticsNewList);

        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgUsrcPaymentMapper.deleteBatchIds(delIds);
        }
    }
}




