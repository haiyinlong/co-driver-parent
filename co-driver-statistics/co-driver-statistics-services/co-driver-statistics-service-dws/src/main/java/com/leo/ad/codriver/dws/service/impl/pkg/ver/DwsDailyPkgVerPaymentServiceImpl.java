package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerPaymentMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerPayment;
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
public class DwsDailyPkgVerPaymentServiceImpl implements DwsService {

    private final DwsDailyPkgVerPaymentMapper dwsDailyPkgVerPaymentMapper;
    private final DwBatchMapper<DwsDailyPkgVerPayment, DwsDailyPkgVerPaymentMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgVerPayment syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgVerPaymentMapper.deleteByDates(dates);
        List<DwsDailyPkgVerPayment> dailyPkgVerPaymentList =
            dwsDailyPkgVerPaymentMapper.queryStatisticsActiveList(dates);
        if (!CollectionUtils.isEmpty(dailyPkgVerPaymentList)) {
            log.info("{} DwsDailyPkgVerPayment syncActiveData 更新插入数据{}条", dates, dailyPkgVerPaymentList.size());
            dwBatchMapper.batchInsert(dailyPkgVerPaymentList, DwsDailyPkgVerPaymentMapper.class);
        }

        dailyPkgVerPaymentList = dwsDailyPkgVerPaymentMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(dailyPkgVerPaymentList)) {
            log.info("{} DwsDailyPkgVerPayment syncNewData 更新插入数据{}条", dates, dailyPkgVerPaymentList.size());
            dwBatchMapper.batchInsert(dailyPkgVerPaymentList, DwsDailyPkgVerPaymentMapper.class);
        }
    }
}
