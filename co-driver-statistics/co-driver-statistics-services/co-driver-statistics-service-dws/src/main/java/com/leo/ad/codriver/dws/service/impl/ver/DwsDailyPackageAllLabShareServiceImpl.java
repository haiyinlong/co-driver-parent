package com.leo.ad.codriver.dws.service.impl.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabShareMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabShare;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllLabShareServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/28 12:50
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllLabShareServiceImpl implements DwsService {
    private final DwsDailyPackageAllLabShareMapper dwsDailyPackageAllLabShareMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabShare, DwsDailyPackageAllLabShareMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabShare")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 获取数据
        List<DwsDailyPackageAllLabShare> packageShareList = dwsDailyPackageAllLabShareMapper.queryStatistics(dates);
        if (CollectionUtils.isEmpty(packageShareList)) {
            return;
        }
        packageShareList.forEach(dwsDailyPackageAllLabShare -> {
            if (ObjectUtils.isEmpty(dwsDailyPackageAllLabShare.getId())) {
                dwsDailyPackageAllLabShare.init();
            } else {
                dwsDailyPackageAllLabShare.update();
            }
        });
        dwBatchMapper.batchInsert(packageShareList, DwsDailyPackageAllLabShareMapper.class);

    }
}
