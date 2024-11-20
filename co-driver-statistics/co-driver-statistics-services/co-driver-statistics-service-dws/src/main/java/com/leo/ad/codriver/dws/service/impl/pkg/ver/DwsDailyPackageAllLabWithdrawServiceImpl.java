package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabWithdraw;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwsDailyPackageAllLabWithdrawServiceImpl implements DwsService {

    private final DwsDailyPackageAllLabWithdrawMapper dwsDailyPackageAllLabWithdrawMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabWithdraw, DwsDailyPackageAllLabWithdrawMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabWithdraw syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllLabWithdrawMapper.deleteByDates(dates);
        List<DwsDailyPackageAllLabWithdraw> withdrawFullDailies =
            dwsDailyPackageAllLabWithdrawMapper.queryStatisticsActiveList(dates);
        if (!CollectionUtils.isEmpty(withdrawFullDailies)) {
            log.info("{} DwsDailyPackageAllLabWithdraw syncActiveData 更新插入数据{}条", dates, withdrawFullDailies.size());
            dwBatchMapper.batchInsert(withdrawFullDailies, DwsDailyPackageAllLabWithdrawMapper.class);
        }

        withdrawFullDailies = dwsDailyPackageAllLabWithdrawMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(withdrawFullDailies)) {
            log.info("{} DwsDailyPackageAllLabWithdraw syncNewData 更新插入数据{}条", dates, withdrawFullDailies.size());
            dwBatchMapper.batchInsert(withdrawFullDailies, DwsDailyPackageAllLabWithdrawMapper.class);
        }
    }
}
