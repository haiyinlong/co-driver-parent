package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgShareMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgShare;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgShareServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 12:50
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgShareServiceImpl implements DwsService {
    private final DwsDailyPkgShareMapper dwsDailyPkgShareMapper;
    private final DwBatchMapper<DwsDailyPkgShare, DwsDailyPkgShareMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgShare")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 获取数据
        List<DwsDailyPkgShare> packageShareList = dwsDailyPkgShareMapper.queryStatistics(dates);
        if (CollectionUtils.isEmpty(packageShareList)) {
            return;
        }
        packageShareList.forEach(dwsDailyPackageAllShare -> {
            if (ObjectUtils.isEmpty(dwsDailyPackageAllShare.getId())) {
                dwsDailyPackageAllShare.init();
            } else {
                dwsDailyPackageAllShare.update();
            }
        });
        dwBatchMapper.batchInsert(packageShareList, DwsDailyPkgShareMapper.class);

    }
}
