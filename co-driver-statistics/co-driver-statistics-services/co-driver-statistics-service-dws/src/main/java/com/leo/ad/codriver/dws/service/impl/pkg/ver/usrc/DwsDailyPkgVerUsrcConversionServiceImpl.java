package com.leo.ad.codriver.dws.service.impl.pkg.ver.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcConversionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcConversion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_conversion】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:30
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcConversionServiceImpl implements DwsService {
    private final DwsDailyPkgVerUsrcConversionMapper dwsDailyPkgVerUsrcConversionMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcConversion, DwsDailyPkgVerUsrcConversionMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgVerUsrcConversion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 查询统计数据
        List<DwsDailyPkgVerUsrcConversion> activeList = dwsDailyPkgVerUsrcConversionMapper.queryActiveList(dates);
        dwBatchMapper.batchInsert(activeList, DwsDailyPkgVerUsrcConversionMapper.class);
        List<DwsDailyPkgVerUsrcConversion> newList = dwsDailyPkgVerUsrcConversionMapper.queryNewList(dates);
        dwBatchMapper.batchInsert(newList, DwsDailyPkgVerUsrcConversionMapper.class);

        // 不删除，更新数据
        List<DwsDailyPkgVerUsrcConversion> dbList = dwsDailyPkgVerUsrcConversionMapper.queryDbList(dates);
        List<Long> delIds = getDelIds(dbList, activeList, newList);

        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgVerUsrcConversionMapper.deleteBatchIds(delIds);
        }

    }
}
