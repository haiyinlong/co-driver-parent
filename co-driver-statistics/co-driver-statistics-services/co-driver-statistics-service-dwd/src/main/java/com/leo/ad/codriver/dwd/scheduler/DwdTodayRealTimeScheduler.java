package com.leo.ad.codriver.dwd.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.service.DwdService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdTodayRealTimeScheduler
 *
 * @author HaiYinLong
 * @version 2025/07/01 10:30
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DwdTodayRealTimeScheduler {
    private final List<DwdService> dwdServices;

    /**
     * 实时更新当天数据，每小时更新一次当天的历史数据，每天晚上同一再处理一次保证数据的真确性<br/>
     */
    @Scheduled(cron = "0 0 1-22 * * ?")
    @Async("asyncServiceExecutor")
    public void updateCurrentDate() {
        // 获取统计日期
        Integer dates = DateUtils.getNowDates();
        log.info("{} dwd 开始实时同步所有数据", dates);
        for (DwdService service : dwdServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + "当天" + service.getClass().getSimpleName() + " 数据同步异常", e);
            }
        }
        log.info("{} dwd 实时同步所有数据同步结束", dates);
    }
}
