package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserGameGoodsMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserGameGoods;
import com.leo.ad.codriver.dwd.event.DwdUserGameGoodsInstallDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DwdUserGameGoodsServiceImpl implements DwdService {

    private final DwdUserGameGoodsMapper dwdUserGameGoodsMapper;
    private final DwBatchMapper<DwdUserGameGoods, DwdUserGameGoodsMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserGameGoods syncData")
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserGameGoodsInstallDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        DwCountDTO statisticsCount = dwdUserGameGoodsMapper.getOdsStatisticsCount();
        if (ObjectUtils.isEmpty(statisticsCount) || ObjectUtils.isEmpty(statisticsCount.getMinId())) {
            return false;
        }
        int loopNum = statisticsCount.loopNum();
        long startId;
        long endId;
        List<DwdUserGameGoods> userGameGoodsList;
        for (int i = 1; i <= loopNum; i++) {
            startId = statisticsCount.loopStartId(i);
            endId = statisticsCount.loopEndId(i);
            userGameGoodsList = dwdUserGameGoodsMapper.queryOdsStatisticsInterval(dates, startId, endId);
            if (CollectionUtils.isEmpty(userGameGoodsList)) {
                continue;
            }
            userGameGoodsList.forEach(userGameGoods -> {
                userGameGoods.updateDates(dates);
            });
            // 转化数据，入库
            dwBatchMapper.batchInsert(userGameGoodsList, DwdUserGameGoodsMapper.class);
        }
        return true;
    }
}
