package com.leo.ad.codriver.dwd.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.async.AsyncThreadExecutor;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserEventDetailMapper;
import com.leo.ad.codriver.dwd.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserEventDetail;
import com.leo.ad.codriver.dwd.event.DwdUserEventDetailUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdEventService;
import com.leo.ad.codriver.dwd.service.DwdStreamService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserEventDetailFormEventReportServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/04/18 16:47
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserEventDetailFormEventReportServiceImpl implements DwdEventService, DwdStreamService {
    public static final String ODS_EVENT_REPORT = "ods_event_report";
    private final DwdUserEventDetailMapper dwdUserEventDetailMapper;
    private final DwBatchMapper<DwdUserEventDetail, DwdUserEventDetailMapper> batchMapper;
    private final static Integer ASYNC_THREAD_NUMBER = 4;

    @Override
    @ShowExecuteTime(name = "dwdUserEventDetail form eventReport syncData")
    @AutoPushEventWithTrue(events = {DwdUserEventDetailUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    @Async
    public boolean syncData(Integer dates) {
        Integer nowDates = DateUtils.getNowDates();
        if (Objects.equals(dates, nowDates)) {
            // 当天数据不进行统计，跳过
            return false;
        }
        int rowNumInterval = 2000;
        // 先删除数据
        long startTime = System.currentTimeMillis();
        Integer delRowNum = dwdUserEventDetailMapper.deleteByDates(dates, "ods_event_report");
        log.info(" {} 删除diversion.event_report 历史数据耗时: {}", dates, (System.currentTimeMillis() - startTime) / 1000);
        // 查询统计总数据，然后分页进行获取
        long diversionEventReportCount = dwdUserEventDetailMapper.getEventReportCount(dates);
        if (diversionEventReportCount <= 0) {
            return delRowNum > 0;
        }
        long totalPage = LongUtils.divide(diversionEventReportCount, (long)rowNumInterval);
        log.info(" {} diversion.event_report 分页统计情况总条数:{} 查询页数:{}", dates, diversionEventReportCount, totalPage);
        if (totalPage <= 0) {
            return false;
        }
        // 数据量太大线程会导致数据库有压力,固定每次处理ASYNC_THREAD_NUMBER页数据
        AsyncThreadExecutor asyncThreadExecutor = null;
        for (int i = 1; i <= totalPage; i++) {
            // 没有线程池，自己创建一个
            if (ObjectUtils.isEmpty(asyncThreadExecutor) || asyncThreadExecutor.getCountDownLatch().getCount() == 0) {
                asyncThreadExecutor = AsyncThreadExecutor.of(
                    (totalPage - (i - 1)) >= ASYNC_THREAD_NUMBER ? ASYNC_THREAD_NUMBER : (int)(totalPage - (i - 1)));
            }
            int pageSize = i;
            asyncThreadExecutor.execute(() -> {
                List<DwdUserEventDetail> diversionEventList =
                    dwdUserEventDetailMapper.queryEventReport(dates, rowNumInterval, ((pageSize - 1) * rowNumInterval));
                batchMapper.batchInsert(diversionEventList, DwdUserEventDetailMapper.class);
                log.info(" {} diversion.event_report 执行完第{}页数据", dates, pageSize);
            });
            if (i > 0 && ((i % ASYNC_THREAD_NUMBER == 0) || totalPage == i)) {
                try {
                    asyncThreadExecutor.await();
                } catch (InterruptedException e) {
                    log.error("diversion.event_report 执行异常", e);
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean syncChangeData(DataChangeDTO dataChangeDTO) {
        DwdUserEventDetail dwdUserEventDetail =
            dwdUserEventDetailMapper.getStatisticsEventReport(ODS_EVENT_REPORT, dataChangeDTO.getSourceId());
        if (ObjectUtils.isEmpty(dwdUserEventDetail)) {
            dwdUserEventDetailMapper.deleteEventReportBySourceId(ODS_EVENT_REPORT, dataChangeDTO.getSourceId());
            return true;
        }
        batchMapper.batchInsert(Collections.singletonList(dwdUserEventDetail), DwdUserEventDetailMapper.class);
        return true;
    }
}
