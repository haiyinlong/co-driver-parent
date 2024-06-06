package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageCohortRetentionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageCohortRetention;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * DwsServiceImpl**
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
@Order
@Slf4j
public class DwsDailyPackageCohortRetentionServiceImpl implements DwsService {

    private final DwsDailyPackageCohortRetentionMapper dwsDailyPackageCohortRetentionMapper;
    private final DwBatchMapper<DwsDailyPackageCohortRetention, DwsDailyPackageCohortRetentionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwsDailyPackageCohortRetention syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        long startTime = System.currentTimeMillis();
        dwsDailyPackageCohortRetentionMapper.deleteByDates(dates);
        log.info("{} dwsDailyPackageCohortRetention 删除历史数据, 耗时:{} ", dates,
                (System.currentTimeMillis() - startTime) / 1000);
        startTime = System.currentTimeMillis();
        List<DwsDailyPackageCohortRetention> dwsDailyPackageCohortRetentions =
                dwsDailyPackageCohortRetentionMapper.statistics(dates);
        log.info("{} dwsDailyPackageCohortRetention 获取数据条数：{}, 耗时:{} ", dates, dwsDailyPackageCohortRetentions.size(),
                (System.currentTimeMillis() - startTime) / 1000);
        startTime = System.currentTimeMillis();
        dwBatchMapper.batchInsert(dwsDailyPackageCohortRetentions, DwsDailyPackageCohortRetentionMapper.class);
        log.info("{} dwsDailyPackageCohortRetention 数据更新, 耗时:{} ", dates,
                (System.currentTimeMillis() - startTime) / 1000);
    }
}
