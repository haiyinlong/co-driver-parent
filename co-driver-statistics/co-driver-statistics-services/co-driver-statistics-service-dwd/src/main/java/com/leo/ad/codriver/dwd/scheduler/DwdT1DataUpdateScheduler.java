package com.leo.ad.codriver.dwd.scheduler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dim.service.DimService;
import com.leo.ad.codriver.dwd.event.DwdUpdateFinishEvent;
import com.leo.ad.codriver.dwd.service.DwdService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdT1DataUpdateScheduler
 *
 * @author HaiYinLong
 * @version 2025/07/01 10:30
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DwdT1DataUpdateScheduler {
    private final ExchangeRate exchangeRate;
    private final List<DwdService> dwdServices;
    private final List<DimService> dimServices;
    private final ApplicationEventPublisher publisher;

    @Scheduled(cron = "30 0 0 * * ?")
    @Async("asyncServiceExecutor")
    public void syncAllTask() {
        Integer dates = DateUtils.getPreviousDate();
        // 更新汇率
        exchangeRate.updateFeeUSDToINR();
        log.info("dim 开始全量同步所有数据");
        for (DimService service : dimServices) {
            try {
                service.syncData();
            } catch (Exception e) {
                log.error(service.getClass().getSimpleName() + "全量数据同步异常", e);
            }
        }
        log.info("dim 全量数据同步结束");
        log.info("{} dwd 开始同步所有数据, 共{} 个", dates, dwdServices.size());
        for (DwdService service : dwdServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + " " + service.getClass().getSimpleName() + "全量数据同步异常", e);
            }
        }
        log.info("{} dwd 所有数据同步结束", dates);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error(dates + " dwd 所有数据同步结束, 休息5秒异常", e);
        }
        publisher.publishEvent(new DwdUpdateFinishEvent(this, dates));
    }
}
