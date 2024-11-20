package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllGameSingleMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllGameSingle;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllGameSingleServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/11 16:39
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllGameSingleServiceImpl implements DwsService {
    private final DwsDailyPackageAllGameSingleMapper dwsDailyPackageAllGameSingleMapper;
    private final DwBatchMapper<DwsDailyPackageAllGameSingle, DwsDailyPackageAllGameSingleMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllGameSingle")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // TODO 改为数据库获取明细，代码中进行汇总，控制新增，修改和删除；
        List<DwsDailyPackageAllGameSingle> activeList = dwsDailyPackageAllGameSingleMapper.queryActiveList(dates);
        activeList.forEach(DwsDailyPackageAllGameSingle::calculate);
        dwBatchMapper.batchInsert(activeList, DwsDailyPackageAllGameSingleMapper.class);

        List<DwsDailyPackageAllGameSingle> newList = dwsDailyPackageAllGameSingleMapper.queryNewList(dates);
        newList.forEach(DwsDailyPackageAllGameSingle::calculate);
        dwBatchMapper.batchInsert(newList, DwsDailyPackageAllGameSingleMapper.class);

        List<DwsDailyPackageAllGameSingle> dwsDailyPackageAllGameSingleList =
            dwsDailyPackageAllGameSingleMapper.queryList(dates);
        List<Long> delIds = getDelIds(dwsDailyPackageAllGameSingleList, activeList, newList);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPackageAllGameSingleMapper.deleteBatchIds(delIds);
        }
    }

}
