package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyCohortMissionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageCohortMission;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 每天任务同期转化统计
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
@Service
@AllArgsConstructor
@Order
public class DwsDailyPackageCohortMissionServiceImpl implements DwsService {
    private final DwsDailyCohortMissionMapper dwsDailyCohortMissionMapper;
    private final DwBatchMapper<DwsDailyPackageCohortMission, DwsDailyCohortMissionMapper> dwBatchMapper;

    /**
     * 统计依赖于dws_daily_package_register,需要在dws_daily_package_register同步完成之后执行
     *
     * @param dates 统计日期
     */
    @Override
    @ShowExecuteTime(name = "dwsDailyPackageCohortMission")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyCohortMissionMapper.deleteByDates(dates);
        List<DwsDailyPackageCohortMission> dwsDailyCohortConversions = dwsDailyCohortMissionMapper.statistics(dates);
        dwBatchMapper.batchInsert(dwsDailyCohortConversions, DwsDailyCohortMissionMapper.class);
    }
}
