package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsPkgGameFullDailyMapper;
import com.leo.ad.codriver.dws.entity.DwsPkgGameFullDaily;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 每日包游戏全量统计数据
 *
 * @author HaiYinLong
 * @version 2024/07/3 18:34
 **/
@Service
@AllArgsConstructor
public class DwsPkgGameFullDailyServiceImpl implements DwsService {
    private final DwsPkgGameFullDailyMapper dwsPkgGameFullDailyMapper;
    private final DwBatchMapper<DwsPkgGameFullDaily, DwsPkgGameFullDailyMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "DwsPkgGameFullDaily")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsPkgGameFullDailyMapper.deleteByDates(dates);
        // 统计活跃用户
        List<DwsPkgGameFullDaily> pkgGameActiveList = dwsPkgGameFullDailyMapper.statisticsActive(dates);
        batchMapper.batchInsert(pkgGameActiveList, DwsPkgGameFullDailyMapper.class);
        // 统计新增用户
        List<DwsPkgGameFullDaily> pkgGameNewList = dwsPkgGameFullDailyMapper.statisticsNew(dates);
        batchMapper.batchInsert(pkgGameNewList, DwsPkgGameFullDailyMapper.class);
    }
}
