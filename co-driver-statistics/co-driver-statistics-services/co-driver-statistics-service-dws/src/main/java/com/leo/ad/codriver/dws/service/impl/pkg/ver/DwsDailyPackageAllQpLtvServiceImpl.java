package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllQpLtvMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllQpLtv;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllQpLtvServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 18:46
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllQpLtvServiceImpl implements DwsService {
    private final DwsDailyPackageAllQpLtvMapper dwsDailyPackageAllQpLtvMapper;
    private final DwBatchMapper<DwsDailyPackageAllQpLtv, DwsDailyPackageAllQpLtvMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllQpLtv")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwsDailyPackageAllQpLtvMapper.deleteByDates(dates);
        List<DwsDailyPackageAllQpLtv> dwsDailyPackageAllQpLtvList = dwsDailyPackageAllQpLtvMapper.selectByDates(dates);
        if (CollectionUtils.isEmpty(dwsDailyPackageAllQpLtvList)) {
            return;
        }
        dwsDailyPackageAllQpLtvList.forEach(DwsDailyPackageAllQpLtv::init);
        dwBatchMapper.batchInsert(dwsDailyPackageAllQpLtvList, DwsDailyPackageAllQpLtvMapper.class);
    }
}
