package com.leo.ad.codriver.dws.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsPkgUserFullDailyMapper;
import com.leo.ad.codriver.dws.entity.DwsPkgUserFullDaily;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 每日包用户全量统计数据
 *
 * @author HaiYinLong
 * @version 2024/07/3 18:34
 **/
@Service
@AllArgsConstructor
public class DwsPkgUserFullDailyServiceImpl implements DwsService {
    private final DwsPkgUserFullDailyMapper dwsPkgUserFullDailyMapper;
    private final DwBatchMapper<DwsPkgUserFullDaily, DwsPkgUserFullDailyMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "DwsPkgGameFullDaily")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        List<DwsPkgUserFullDaily> pkgUserList = dwsPkgUserFullDailyMapper.statistics(dates);
        batchMapper.batchInsert(pkgUserList, DwsPkgUserFullDailyMapper.class);
    }
}
