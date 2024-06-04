package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsWithdrawFullDailyMapper;
import com.leo.ad.codriver.dws.entity.DwsWithdrawFullDaily;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * DwsServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwsWithdrawFullDailyServiceImpl implements DwsService {

    private final DwsWithdrawFullDailyMapper dwsWithdrawFullDailyMapper;
    private final DwBatchMapper<DwsWithdrawFullDaily, DwsWithdrawFullDailyMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsWithdrawFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    public void syncData(Integer dates) {
        dwsWithdrawFullDailyMapper.delete(dates);
        List<DwsWithdrawFullDaily> withdrawFullDailies = dwsWithdrawFullDailyMapper.queryStatisticsActiveList(dates);
        if (!CollectionUtils.isEmpty(withdrawFullDailies)) {
            log.info("{} DwsWithdrawFullDaily syncActiveData 更新插入数据{}条", dates, withdrawFullDailies.size());
            dwBatchMapper.batchInsert(withdrawFullDailies, DwsWithdrawFullDailyMapper.class);
        }

        withdrawFullDailies = dwsWithdrawFullDailyMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(withdrawFullDailies)) {
            log.info("{} DwsWithdrawFullDaily syncNewData 更新插入数据{}条", dates, withdrawFullDailies.size());
            dwBatchMapper.batchInsert(withdrawFullDailies, DwsWithdrawFullDailyMapper.class);
        }
    }
}
