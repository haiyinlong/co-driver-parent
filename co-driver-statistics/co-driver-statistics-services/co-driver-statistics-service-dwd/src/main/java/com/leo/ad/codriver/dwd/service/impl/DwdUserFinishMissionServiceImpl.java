package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserFinishMissionMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserFinishMission;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:15
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserFinishMissionServiceImpl implements DwdService {
    private final DwdUserFinishMissionMapper dwdUserFinishMissionMapper;
    private final DwBatchMapper<DwdUserFinishMission, DwdUserFinishMissionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserFinishMission syncData")
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        dwdUserFinishMissionMapper.deleteByDates(dates);
        List<DwdUserFinishMission> dwdUserFinishMissions = dwdUserFinishMissionMapper.statisticsUserId(dates);
        if (!CollectionUtils.isEmpty(dwdUserFinishMissions)) {
            dwBatchMapper.batchInsert(dwdUserFinishMissions, DwdUserFinishMissionMapper.class);
        }
        dwdUserFinishMissions = dwdUserFinishMissionMapper.statisticsAid(dates);
        if (!CollectionUtils.isEmpty(dwdUserFinishMissions)) {
            dwBatchMapper.batchInsert(dwdUserFinishMissions, DwdUserFinishMissionMapper.class);
        }
    }
}
