package com.leo.ad.codriver.dws.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackagePromotionScheduler
 *
 * @author HaiYinLong
 * @version 2024/09/10 15:09
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DwsDailyPackagePromotionScheduler {
    private final DwsService dwsDailyPackagePromotionServiceImpl;

    /**
     * 定时主动计算推广花费
     *
     * @deprecated 废弃, 使用直接使用dwsDailyPackageAllPromotion数据即可，不需要再计算
     */
    @Scheduled(cron = "0 30 0-10 * * ?")
    @Async("asyncServiceExecutor")
    @Deprecated
    public void syncPackagePromotion() {
        // 获取统计日期
        Integer dates;
        int[] days = {1, 2, 3};
        for (int day : days) {
            dates = DateUtils.getPreviousDate(day);
            dwsDailyPackagePromotionServiceImpl.syncData(dates);
        }
    }
}
