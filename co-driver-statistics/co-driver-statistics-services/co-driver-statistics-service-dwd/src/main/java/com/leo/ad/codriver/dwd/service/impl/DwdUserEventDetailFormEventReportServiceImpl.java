package com.leo.ad.codriver.dwd.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.async.AsyncThreadExecutor;
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
 * DwdUserEventDetailFormEventReportServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/18 16:47
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserEventDetailFormEventReportServiceImpl implements DwdEventService {
    private final DwdUserEventDetailMapper dwdUserEventDetailMapper;
    private final DwBatchMapper<DwdUserEventDetail, DwdUserEventDetailMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserEventDetail form eventReport syncData")
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        // 先删除数据
        long startTime = System.currentTimeMillis();
        dwdUserEventDetailMapper.deleteByDates(dates, "ods_event_report");

        log.info(" {} 删除diversion.event_report 历史数据耗时: {}", dates, (System.currentTimeMillis() - startTime) / 1000);
        // 查询统计总数据，然后分页进行获取
        long diversionEventReportCount = dwdUserEventDetailMapper.getEventReportCount(dates);
        // TODO 数据量太大线程会导致数据库有压力
        long totalPage = LongUtils.divide(diversionEventReportCount, BatchConst.BATCH_MAX_NUMBER.longValue());
        log.info(" {} diversion.event_report 分页统计情况总条数:{} 查询页数:{}", dates, diversionEventReportCount, totalPage);
        if (totalPage <= 0) {
            return;
        }
        AsyncThreadExecutor asyncThreadExecutor = AsyncThreadExecutor.of((int) totalPage);
        for (int i = 1; i <= totalPage; i++) {
            int pageSize = i;
            asyncThreadExecutor.execute(() -> {
                List<DwdUserEventDetail> diversionEventList = dwdUserEventDetailMapper.queryEventReport(dates,
                        BatchConst.BATCH_MAX_NUMBER.intValue(), (int) ((pageSize - 1) * BatchConst.BATCH_MAX_NUMBER));
                batchMapper.batchInsert(diversionEventList, DwdUserEventDetailMapper.class);
                log.info(" {} diversion.event_report 执行完第{}页数据", dates, pageSize);
            });
        }
        try {
            asyncThreadExecutor.await();
        } catch (InterruptedException e) {
            log.error("diversion.event_report 执行异常", e);
            throw new RuntimeException(e);
        }
    }

}
