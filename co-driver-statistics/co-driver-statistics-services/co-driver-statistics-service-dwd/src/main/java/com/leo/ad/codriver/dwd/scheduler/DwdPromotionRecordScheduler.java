package com.leo.ad.codriver.dwd.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.service.DwdService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdPromotionRecordScheduler
 *
 * @author HaiYinLong
 * @version 2024/09/04 16:10
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DwdPromotionRecordScheduler {
    private final DwdService dwdPromotionRecordServiceImpl;

    @Scheduled(cron = "0 30 3-5 * * ?")
    @Async("asyncServiceExecutor")
    public void syncUpdateHmGameRetention() {
        Integer dates = DateUtils.getPreviousDate();
        dwdPromotionRecordServiceImpl.syncData(dates);
        log.info("{} dwdPromotionRecord 更新数据结束", dates);
    }

}
