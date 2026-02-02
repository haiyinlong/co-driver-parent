package com.leo.ad.codriver.dwd.scheduler;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.service.DwdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
        // 更新最近三天的数据
        int dates;
        for (int i = 4; i < 1; i--) {
            dates = DateUtils.getPreviousDate(i);
            dwdPromotionRecordServiceImpl.syncData(dates);
            log.info("{} dwdPromotionRecord 更新数据结束", dates);
        }
    }

}
