package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsHemaEventFullDailyMapper;
import com.leo.ad.codriver.dws.entity.DwsHemaEventFullDaily;
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
public class DwsHemaEventFullDailyServiceImpl implements DwsService {

    private final DwsHemaEventFullDailyMapper dwsHemaEventFullDailyMapper;
    private final DwBatchMapper<DwsHemaEventFullDaily, DwsHemaEventFullDailyMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsHemaEventFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
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
    }
}
