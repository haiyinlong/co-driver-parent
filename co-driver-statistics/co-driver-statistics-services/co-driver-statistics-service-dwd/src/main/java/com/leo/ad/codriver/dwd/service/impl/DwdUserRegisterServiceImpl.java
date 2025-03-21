package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserRegisterMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserRegister;
import com.leo.ad.codriver.dwd.event.DwdUserRegisterRecordUpdateDwEvent;
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
@Order(Integer.MIN_VALUE)
public class DwdUserRegisterServiceImpl implements DwdService {
    private final DwdUserRegisterMapper dwdUserRegisterMapper;
    private final DwBatchMapper<DwdUserRegister, DwdUserRegisterMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserRegister syncData")
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserRegisterRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        DwCountDTO statisticsCount = dwdUserRegisterMapper.getStatisticsCount(dates);
        if (ObjectUtils.isEmpty(statisticsCount) || statisticsCount.getCount() == 0) {
            log.info("DwsDailyRegister {} 统计数据为空，跳过处理", dates);
            return false;
        }
        int loopNum = statisticsCount.loopNum();
        long startId;
        long endId;
        List<DwdUserRegister> registerList;
        for (int i = 1; i <= loopNum; i++) {
            startId = statisticsCount.loopStartId(i);
            endId = statisticsCount.loopEndId(i);
            registerList = dwdUserRegisterMapper.statisticsPage(dates, startId, endId);
            if (CollectionUtils.isEmpty(registerList)) {
                continue;
            }
            // 转化数据，入库
            registerList =
                registerList.stream().filter(registerItem -> ObjectUtils.isEmpty(registerItem.getId())).toList();
            batchMapper.batchInsert(registerList, DwdUserRegisterMapper.class);
        }
        return true;
    }
}
