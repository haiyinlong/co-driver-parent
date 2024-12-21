package com.leo.ad.codriver.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dim.service.DimService;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/***
 * 定义同步入口**<br>
 * 定时任务通过各个模块自己配置，不进行统一调用
 *
 * @author HaiYinLong
 * @version 2024/04/09 11:48
 **/
@Deprecated
@Component
@Slf4j
@RequiredArgsConstructor
public class DwScheduleTask {
    private final ExchangeRate exchangeRate;

    private final List<DwsService> dwsServices;
    private final List<DimService> dimServices;
    private final List<DwdService> dwdServices;
    private final List<AdsService> adsServices;

    /**
     * 全量同步数据，每日凌晨全量同步前一天数据
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @Async("asyncServiceExecutor")
    public void syncAllTask() {
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
        // 获取统计日期
        Integer dates = DateUtils.getPreviousDate();
        log.info("{} dwd 开始同步所有数据", dates);
        for (DwdService service : dwdServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + " " + service.getClass().getSimpleName() + "全量数据同步异常", e);
            }
        }
        log.info("{} dwd 所有数据同步结束", dates);
        log.info("{} dws 开始同步所有数据", dates);
        for (DwsService service : dwsServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + " " + service.getClass().getSimpleName() + "全量数据同步异常", e);
            }
        }
        log.info("{} dws 所有数据同步结束", dates);
        log.info("{} ads 开始同步所有数据", dates);
        for (AdsService service : adsServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + " " + service.getClass().getSimpleName() + "全量数据同步异常", e);
            }
        }
        log.info("{} ads 所有数据同步结束", dates);
    }

    /**
     * 实时更新当天数据，每小时更新一次当天的历史数据，每天晚上同一再处理一次保证数据的真确性<br/>
     */
    @Scheduled(cron = "0 0 1-22 * * ?")
    @Async("asyncServiceExecutor")
    public void updateCurrentDate() {
        // 获取统计日期
        Integer dates = DateUtils.getNowDates();
        log.info("{} 实时同步当天数据", dates);
        log.info("dim 开始实时同步所有数据");
        // DIM
        for (DimService service : dimServices) {
            try {
                service.syncData();
            } catch (Exception e) {
                log.error(dates + "当天" + service.getClass().getSimpleName() + " 数据同步异常", e);
            }
        }
        log.info("dim 实时同步所有数据同步结束");
        log.info("{} dwd 开始实时同步所有数据", dates);
        for (DwdService service : dwdServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + "当天" + service.getClass().getSimpleName() + " 数据同步异常", e);
            }
        }
        log.info("{} dwd 实时同步所有数据同步结束", dates);
        log.info("{} dws 开始实时同步所有数据", dates);
        for (DwsService service : dwsServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + "当天" + service.getClass().getSimpleName() + " 数据同步异常", e);
            }
        }
        log.info("{} dws 实时同步所有数据同步结束", dates);
        log.info("{} ads 开始实时同步所有数据", dates);
        for (AdsService service : adsServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + "当天" + service.getClass().getSimpleName() + " 数据同步异常", e);
            }
        }
        log.info("{} ads 实时同步所有数据同步结束", dates);
    }

}
