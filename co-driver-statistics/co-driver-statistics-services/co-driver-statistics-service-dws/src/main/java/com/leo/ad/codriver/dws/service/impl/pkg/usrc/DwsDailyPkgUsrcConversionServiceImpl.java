package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcConversionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcConversion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_conversion】的数据库操作Service实现
 * @createDate 2024-11-20 17:07:05
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcConversionServiceImpl implements DwsService {

    private final DwsDailyPkgUsrcConversionMapper dwsDailyPkgUsrcConversionMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcConversion, DwsDailyPkgUsrcConversionMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgUsrcConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        // 查询统计数据
        List<DwsDailyPkgUsrcConversion> activeList = dwsDailyPkgUsrcConversionMapper.queryActiveList(dates);
        dwBatchMapper.batchInsert(activeList, DwsDailyPkgUsrcConversionMapper.class);
        List<DwsDailyPkgUsrcConversion> newList = dwsDailyPkgUsrcConversionMapper.queryNewList(dates);
        dwBatchMapper.batchInsert(newList, DwsDailyPkgUsrcConversionMapper.class);

        // 不删除，更新数据
        List<DwsDailyPkgUsrcConversion> dbList = dwsDailyPkgUsrcConversionMapper.queryDbList(dates);
        List<Long> delIds = getDelIds(dbList, activeList, newList);

        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgUsrcConversionMapper.deleteBatchIds(delIds);
        }

    }
}
