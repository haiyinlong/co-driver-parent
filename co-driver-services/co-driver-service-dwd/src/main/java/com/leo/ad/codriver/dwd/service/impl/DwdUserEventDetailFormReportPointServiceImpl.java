package com.leo.ad.codriver.dwd.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserEventDetailMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserEventDetail;
import com.leo.ad.codriver.dwd.service.DwdEventService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DwdUserEventDetailFormReportEventServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/18 16:47
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserEventDetailFormReportPointServiceImpl implements DwdEventService {
    private final DwdUserEventDetailMapper dwdUserEventDetailMapper;
    private final DwBatchMapper<DwdUserEventDetail, DwdUserEventDetailMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserEventDetail form reportPoint syncData")
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        // 先删除数据
        dwdUserEventDetailMapper.deleteByDates(dates, "ods_report_point_diversion");
        // 查询统计总数据，然后分页进行获取
        long diversionEventReportCount = dwdUserEventDetailMapper.getReportPointCount(dates);
        long totalPage = LongUtils.divide(diversionEventReportCount, BatchConst.BATCH_NUMBER.longValue());
        if (totalPage <= 0) {
            return;
        }
        List<DwdUserEventDetail> reportPointList;
        for (int i = 1; i <= totalPage; i++) {
            reportPointList = dwdUserEventDetailMapper.queryReportPoint(dates, BatchConst.BATCH_NUMBER.intValue(),
                    (int) ((i - 1) * BatchConst.BATCH_NUMBER));
            batchMapper.batchInsert(reportPointList, DwdUserEventDetailMapper.class);
        }
    }

}
