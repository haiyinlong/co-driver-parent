package com.leo.ad.codriver.dws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageShareMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageShare;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageShareServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 12:50
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageShareServiceImpl implements DwsService {
    private final DwsDailyPackageShareMapper dwsDailyPackageShareMapper;
    private final DwBatchMapper<DwsDailyPackageShare, DwsDailyPackageShareMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageShare")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        // 获取数据
        List<DwsDailyPackageShare> packageShareList = dwsDailyPackageShareMapper.queryStatistics(dates);
        if (CollectionUtils.isEmpty(packageShareList)) {
            return;
        }
        packageShareList.forEach(dwsDailyPackageShare -> {
            if (ObjectUtils.isEmpty(dwsDailyPackageShare.getId())) {
                dwsDailyPackageShare.init();
            } else {
                dwsDailyPackageShare.update();
            }
        });
        dwBatchMapper.batchInsert(packageShareList, DwsDailyPackageShareMapper.class);

    }
}
