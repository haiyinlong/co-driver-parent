package com.leo.ad.codriver.dws.service.impl.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllShareMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllShare;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllShareServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 12:50
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllShareServiceImpl implements DwsService {
    private final DwsDailyPackageAllShareMapper dwsDailyPackageAllShareMapper;
    private final DwBatchMapper<DwsDailyPackageAllShare, DwsDailyPackageAllShareMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllShare")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 获取数据
        List<DwsDailyPackageAllShare> packageShareList = dwsDailyPackageAllShareMapper.queryStatistics(dates);
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
        dwBatchMapper.batchInsert(packageShareList, DwsDailyPackageAllShareMapper.class);

    }
}
