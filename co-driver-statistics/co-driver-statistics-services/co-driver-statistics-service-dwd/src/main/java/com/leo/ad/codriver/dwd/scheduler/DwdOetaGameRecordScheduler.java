package com.leo.ad.codriver.dwd.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.service.DwdService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdOetaGameRecordScheduler
 *
 * @author HaiYinLong
 * @version 2024/11/12 10:46
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DwdOetaGameRecordScheduler {
    private final DwdService dwdUserGameRecordOetaServiceImpl;

    @Scheduled(cron = "0 0 1-23 * * ?")
    @Async("asyncServiceExecutor")
    public void syncDwdOetaGameRecord() {
        Integer dates = DateUtils.getNowDates();
        dwdUserGameRecordOetaServiceImpl.syncData(dates);
        log.info("{} dwdPromotionRecord 更新数据结束", dates);
    }
}
