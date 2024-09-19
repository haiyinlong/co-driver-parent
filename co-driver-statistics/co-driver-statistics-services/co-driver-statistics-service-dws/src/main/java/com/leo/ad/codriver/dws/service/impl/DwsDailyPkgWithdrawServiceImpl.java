package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgWithdrawMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgWithdraw;
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
public class DwsDailyPkgWithdrawServiceImpl implements DwsService {

    private final DwsDailyPkgWithdrawMapper dwsDailyPkgWithdrawMapper;
    private final DwBatchMapper<DwsDailyPkgWithdraw, DwsDailyPkgWithdrawMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgWithdraw syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPkgWithdrawMapper.deleteByDates(dates);
        List<DwsDailyPkgWithdraw> withdrawFullDailies = dwsDailyPkgWithdrawMapper.queryStatisticsActiveList(dates);
        if (!CollectionUtils.isEmpty(withdrawFullDailies)) {
            log.info("{} DwsDailyPkgWithdraw syncActiveData 更新插入数据{}条", dates, withdrawFullDailies.size());
            dwBatchMapper.batchInsert(withdrawFullDailies, DwsDailyPkgWithdrawMapper.class);
        }

        withdrawFullDailies = dwsDailyPkgWithdrawMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(withdrawFullDailies)) {
            log.info("{} DwsDailyPkgWithdraw syncNewData 更新插入数据{}条", dates, withdrawFullDailies.size());
            dwBatchMapper.batchInsert(withdrawFullDailies, DwsDailyPkgWithdrawMapper.class);
        }
    }
}
