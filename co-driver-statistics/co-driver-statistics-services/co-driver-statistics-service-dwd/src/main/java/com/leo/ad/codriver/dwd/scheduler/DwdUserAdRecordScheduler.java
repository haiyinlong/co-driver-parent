package com.leo.ad.codriver.dwd.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.service.DwdService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserAdRecordScheduler
 *
 * @author HaiYinLong
 * @version 2024/09/04 16:10
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DwdUserAdRecordScheduler {
    private final DwdService dwdUserAdRecordServiceImpl;

    @Scheduled(cron = "0 0 1 * * ?")
    @Async("asyncServiceExecutor")
    public void syncUpdateHmGameRetention() {
        Integer dates = DateUtils.getPreviousDate();
        dwdUserAdRecordServiceImpl.syncData(dates);
        log.info("{} DwdUserAdRecord 更新数据结束", dates);
    }

}
