package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserEventMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserEvent;
import com.leo.ad.codriver.dwd.event.DwdUserEventReportUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.ods.dao.OdsEventReportMapper;
import com.leo.ad.codriver.ods.entity.OdsEventReport;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdEventReportServiceImpl
 *
 * @author HaiYinLong
 * @version 2025/02/18 16:41
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdEventReportServiceImpl implements DwdService {
    private final DwdUserEventMapper dwdUserEventMapper;
    private final OdsEventReportMapper odsEventReportMapper;
    private final DwBatchMapper<DwdUserEvent, DwdUserEventMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "DwdEventReportService  syncData")
    @Transactional(rollbackFor = Exception.class)
    @AutoPushEventWithTrue(events = {DwdUserEventReportUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        dwdUserEventMapper.deleteByDate(dates);
        // 获取指定日期的数据 ods 和已存在的数据， 写入到dwd
        DwCountDTO statisticsCount = odsEventReportMapper.getOdsCount(dates);
        if (ObjectUtils.isEmpty(statisticsCount) || ObjectUtils.isEmpty(statisticsCount.getMinId())) {
            log.info("{} DwdEventReportService 统计对象为空,不执行同步", dates);
            return false;
        }
        int loopNum = statisticsCount.loopNum();
        long startId;
        long endId;
        List<OdsEventReport> odsEventReportList;
        for (int i = 1; i <= loopNum; i++) {
            startId = statisticsCount.loopStartId(i);
            endId = statisticsCount.loopEndId(i);
            odsEventReportList = odsEventReportMapper.queryOdsByInterval(dates, startId, endId);
            if (CollectionUtils.isEmpty(odsEventReportList)) {
                continue;
            }
            // 转化数据，入库
            List<DwdUserEvent> dwdUserEventList = odsEventReportList.stream().map(DwdUserEvent::of).toList();
            batchMapper.batchInsert(dwdUserEventList, DwdUserEventMapper.class);
        }
        return true;
    }
}
