package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackagePaymentMapper;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @ShowExecuteTime(name = "DwsDailyPackagePayment")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsDailyPackagePaymentMapper.delete(dates);
        dwsDailyPackagePaymentMapper.syncDailyPackagePayment(dates, exchangeRate.getIndianToDollar());
    }
}
