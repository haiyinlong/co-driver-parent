package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllWithdraw;
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
public class DwsDailyPackageAllWithdrawServiceImpl implements DwsService {

    private final DwsDailyPackageAllWithdrawMapper dwsDailyPackageAllWithdrawMapper;
    private final DwBatchMapper<DwsDailyPackageAllWithdraw, DwsDailyPackageAllWithdrawMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllWithdraw syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllWithdrawMapper.deleteByDates(dates);
        List<DwsDailyPackageAllWithdraw> withdrawFullDailies =
            dwsDailyPackageAllWithdrawMapper.queryStatisticsActiveList(dates);
        if (!CollectionUtils.isEmpty(withdrawFullDailies)) {
            log.info("{} DwsDailyPackageAllWithdraw syncActiveData 更新插入数据{}条", dates, withdrawFullDailies.size());
            dwBatchMapper.batchInsert(withdrawFullDailies, DwsDailyPackageAllWithdrawMapper.class);
        }

        withdrawFullDailies = dwsDailyPackageAllWithdrawMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(withdrawFullDailies)) {
            log.info("{} DwsDailyPackageAllWithdraw syncNewData 更新插入数据{}条", dates, withdrawFullDailies.size());
            dwBatchMapper.batchInsert(withdrawFullDailies, DwsDailyPackageAllWithdrawMapper.class);
        }
    }
}
