package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsHemaBalanceFullDailyMapper;
import com.leo.ad.codriver.dws.entity.DwsHemaBalanceFullDaily;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
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
public class DwsHemaBalanceFullDailyServiceImpl implements DwsService {

    private final DwsHemaBalanceFullDailyMapper dwsHemaBalanceFullDailyMapper;
    private final DwBatchMapper<DwsHemaBalanceFullDaily, DwsHemaBalanceFullDailyMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsHemaBalanceFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsHemaBalanceFullDailyMapper.delete(dates);
        List<DwsHemaBalanceFullDaily> hemaBalanceFullDailies =
                dwsHemaBalanceFullDailyMapper.queryStatisticsActiveList(dates);
        if (!CollectionUtils.isEmpty(hemaBalanceFullDailies)) {
            log.info("{} DwsHemaBalanceFullDaily syncActiveData 更新插入数据{}条", dates, hemaBalanceFullDailies.size());
            dwBatchMapper.batchInsert(hemaBalanceFullDailies, DwsHemaBalanceFullDailyMapper.class);
        }

        hemaBalanceFullDailies = dwsHemaBalanceFullDailyMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(hemaBalanceFullDailies)) {
            log.info("{} DwsHemaBalanceFullDaily syncNewData 更新插入数据{}条", dates, hemaBalanceFullDailies.size());
            dwBatchMapper.batchInsert(hemaBalanceFullDailies, DwsHemaBalanceFullDailyMapper.class);
        }
    }
}
