package com.leo.ad.codriver.dws.service.impl.pkg;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgPaymentMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgPayment;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgPaymentServiceImpl implements DwsService {

    private final DwsDailyPkgPaymentMapper dwsDailyPkgPaymentMapper;
    private final DwBatchMapper<DwsDailyPkgPayment, DwsDailyPkgPaymentMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPkgPayment syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgPaymentMapper.deleteByDates(dates);
        List<DwsDailyPkgPayment> paymentFullDailies = dwsDailyPkgPaymentMapper.queryStatisticsActiveList(dates);
        if (!CollectionUtils.isEmpty(paymentFullDailies)) {
            log.info("{} DwsDailyPkgPayment syncActiveData 更新插入数据{}条", dates, paymentFullDailies.size());
            dwBatchMapper.batchInsert(paymentFullDailies, DwsDailyPkgPaymentMapper.class);
        }

        paymentFullDailies = dwsDailyPkgPaymentMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(paymentFullDailies)) {
            log.info("{} DwsDailyPkgPayment syncNewData 更新插入数据{}条", dates, paymentFullDailies.size());
            dwBatchMapper.batchInsert(paymentFullDailies, DwsDailyPkgPaymentMapper.class);
        }
    }
}




