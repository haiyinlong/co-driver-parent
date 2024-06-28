package com.leo.ad.codriver.ads.service.impl;

import com.leo.ad.codriver.ads.dao.AdsFifteenDayCohortMissionMapper;
import com.leo.ad.codriver.ads.entity.AdsFifteenDayCohortFinishMission;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HaiYinLong
 * @version 2024/04/09 16:23
 **/
@Service
@AllArgsConstructor
public class AdsFifteenDayCohortMissionServiceImpl
        implements AdsService {
    private final AdsFifteenDayCohortMissionMapper adsFifteenDayCohortMissionMapper;

    @Override
    @ShowExecuteTime(name = "adsFifteenDayCohortMission syncData")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        // 每次统计15天的数据，删除15天前的数据
        List<Integer> statisticsDates = DateUtils.getDates(dates, -15);
        adsFifteenDayCohortMissionMapper.deleteByDates(statisticsDates);
        List<AdsFifteenDayCohortFinishMission> fifteenDayCohortConversions =
                adsFifteenDayCohortMissionMapper.statistics(statisticsDates);
        fifteenDayCohortConversions.forEach(adsFifteenDayCohortMissionMapper::insert);
    }

}
