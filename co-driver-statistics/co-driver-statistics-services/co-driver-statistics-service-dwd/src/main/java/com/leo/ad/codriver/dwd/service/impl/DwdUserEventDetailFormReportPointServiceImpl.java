package com.leo.ad.codriver.dwd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserEventDetailMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserEventDetail;
import com.leo.ad.codriver.dwd.service.DwdEventService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        // TODO 太慢了
        int rowNumInterval = 2000;
        // 先删除数据
        Integer delRowNum = dwdUserEventDetailMapper.deleteByDates(dates, "ods_report_point_diversion");
        // 查询统计总数据，然后分页进行获取
        long diversionEventReportCount = dwdUserEventDetailMapper.getReportPointCount(dates);
        if (diversionEventReportCount <= 0) {
            return delRowNum > 0;
        }
        long totalPage = LongUtils.divide(diversionEventReportCount, (long)rowNumInterval);
        if (totalPage <= 0) {
            return delRowNum > 0;
        }
        List<DwdUserEventDetail> reportPointList;
        for (int i = 1; i <= totalPage; i++) {
            reportPointList =
                dwdUserEventDetailMapper.queryReportPoint(dates, rowNumInterval, ((i - 1) * rowNumInterval));
            batchMapper.batchInsert(reportPointList, DwdUserEventDetailMapper.class);
        }
        return true;
    }

}
