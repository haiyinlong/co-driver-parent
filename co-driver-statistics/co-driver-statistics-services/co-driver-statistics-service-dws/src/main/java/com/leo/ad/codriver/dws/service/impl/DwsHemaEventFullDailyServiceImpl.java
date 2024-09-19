package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsHemaEventFullDailyMapper;
import com.leo.ad.codriver.dws.entity.DwsHemaEventFullDaily;
import com.leo.ad.codriver.dws.event.DwsDailyPackageHemaUpdateDwEvent;
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
public class DwsHemaEventFullDailyServiceImpl implements DwsService {

    private final DwsHemaEventFullDailyMapper dwsHemaEventFullDailyMapper;
    private final DwBatchMapper<DwsHemaEventFullDaily, DwsHemaEventFullDailyMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsHemaEventFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // TODO 改为数据库获取明细，代码中进行汇总，控制新增，修改和删除；
        dwsHemaEventFullDailyMapper.deleteByDates(dates);
        List<DwsHemaEventFullDaily> dwsHemaEventFullDailies =
            dwsHemaEventFullDailyMapper.queryStatisticsActiveList(dates);
        if (!CollectionUtils.isEmpty(dwsHemaEventFullDailies)) {
            log.info("{} DwsHemaEventFullDaily syncActiveData 更新插入数据{}条", dates, dwsHemaEventFullDailies.size());
            dwBatchMapper.batchInsert(dwsHemaEventFullDailies, DwsHemaEventFullDailyMapper.class);
        }

        dwsHemaEventFullDailies = dwsHemaEventFullDailyMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(dwsHemaEventFullDailies)) {
            log.info("{} DwsHemaEventFullDaily syncNewData 更新插入数据{}条", dates, dwsHemaEventFullDailies.size());
            dwBatchMapper.batchInsert(dwsHemaEventFullDailies, DwsHemaEventFullDailyMapper.class);
        }
        applicationEventPublisher.publishEvent(new DwsDailyPackageHemaUpdateDwEvent(this, dates));
    }
}
