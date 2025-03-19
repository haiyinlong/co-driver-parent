package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackagePaymentMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackagePayment;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * DwsDailyPackagePaymentServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
public class DwsDailyPackagePaymentServiceImpl implements DwsService {

    private final DwsDailyPackagePaymentMapper dwsDailyPackagePaymentMapper;
    private final ExchangeRate exchangeRate;
    private final DwBatchMapper<DwsDailyPackagePayment, DwsDailyPackagePaymentMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackagePayment")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackagePaymentMapper.deleteByDates(dates);
        List<DwsDailyPackagePayment> packagePaymentList =
            dwsDailyPackagePaymentMapper.statisticsPackagePayment(dates, exchangeRate.getIndianToDollar());
        dwBatchMapper.batchInsert(packagePaymentList, DwsDailyPackagePaymentMapper.class);
    }
}
