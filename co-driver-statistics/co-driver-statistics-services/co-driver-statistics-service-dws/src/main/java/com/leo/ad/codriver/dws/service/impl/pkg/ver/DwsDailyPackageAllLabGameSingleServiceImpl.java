package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPackageAllLabGameSingleMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabGameSingle;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllLabGameSingleServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/11 16:39
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPackageAllLabGameSingleServiceImpl implements DwsService {
    private final DwsDailyPackageAllLabGameSingleMapper dwsDailyPackageAllLabGameSingleMapper;
    private final DwBatchMapper<DwsDailyPackageAllLabGameSingle, DwsDailyPackageAllLabGameSingleMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DwsDailyPackageAllLabGameSingle")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // TODO 改为数据库获取明细，代码中进行汇总，控制新增，修改和删除；
        // 根据游戏逐个获取数据
        List<String> gameCodeList = dwsDailyPackageAllLabGameSingleMapper.getGameCodeList();
        List<DwsDailyPackageAllLabGameSingle> activeList = null;
        List<DwsDailyPackageAllLabGameSingle> newList = null;
        List<DwsDailyPackageAllLabGameSingle> allList = new ArrayList<>();
        for (String gameCode : gameCodeList) {
            activeList = dwsDailyPackageAllLabGameSingleMapper.queryActiveList(dates, gameCode);
            activeList.forEach(DwsDailyPackageAllLabGameSingle::calculate);
            dwBatchMapper.batchInsert(activeList, DwsDailyPackageAllLabGameSingleMapper.class);
            allList.addAll(activeList);
            newList = dwsDailyPackageAllLabGameSingleMapper.queryNewList(dates, gameCode);
            newList.forEach(DwsDailyPackageAllLabGameSingle::calculate);
            dwBatchMapper.batchInsert(newList, DwsDailyPackageAllLabGameSingleMapper.class);
            allList.addAll(newList);
        }
        List<DwsDailyPackageAllLabGameSingle> dwsDailyPackageAllGameSingleList =
            dwsDailyPackageAllLabGameSingleMapper.queryList(dates);
        List<Long> delIds = getDelIds(dwsDailyPackageAllGameSingleList, allList, Collections.emptyList());
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPackageAllLabGameSingleMapper.deleteBatchIds(delIds);
        }
    }

}
