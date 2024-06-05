package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsHemaAccountFullDailyMapper;
import com.leo.ad.codriver.dws.entity.DwsHemaAccountFullDaily;
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
public class DwsHemaAccountFullDailyServiceImpl implements DwsService {

    private final DwsHemaAccountFullDailyMapper dwsHemaAccountFullDailyMapper;
    private final DwBatchMapper<DwsHemaAccountFullDaily, DwsHemaAccountFullDailyMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsHemaAccountFullDaily syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwsHemaAccountFullDailyMapper.delete(dates);
        List<DwsHemaAccountFullDaily> hemaAccountFullDailies =
                dwsHemaAccountFullDailyMapper.queryStatisticsActiveList(dates);
        if (!CollectionUtils.isEmpty(hemaAccountFullDailies)) {
            log.info("{} DwsHemaAccountFullDaily syncActiveData 更新插入数据{}条", dates, hemaAccountFullDailies.size());
            dwBatchMapper.batchInsert(hemaAccountFullDailies, DwsHemaAccountFullDailyMapper.class);
        }

        hemaAccountFullDailies = dwsHemaAccountFullDailyMapper.queryStatisticsNewList(dates);
        if (!CollectionUtils.isEmpty(hemaAccountFullDailies)) {
            log.info("{} DwsHemaAccountFullDaily syncNewData 更新插入数据{}条", dates, hemaAccountFullDailies.size());
            dwBatchMapper.batchInsert(hemaAccountFullDailies, DwsHemaAccountFullDailyMapper.class);
        }
    }
}
