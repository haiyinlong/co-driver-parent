package com.leo.ad.codriver.ads.controller;

import java.util.List;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.util.DateUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwController
 *
 * @author HaiYinLong
 * @version 2024/04/16
 **/
@RestController
@RequestMapping("/task/ads")
@Tag(name = "AdsController", description = "Ads层任务")
@AllArgsConstructor
@Slf4j
public class TaskAdsController {

    // private final AdsService adsHemaDataAnalyseFullDailyServiceImpl;
    private final AdsService adsFifteenDayCohortConversionServiceImpl;
    private final AdsService adsFifteenDayCohortMissionServiceImpl;
    private final AdsService adsHemaWithdrawFullDailyServiceImpl;
    private final AdsService adsGameLevelFullDailyServiceImpl;
    private final AdsService adsGameAnalyseFullDailyServiceImpl;
    private final AdsService adsDailyOetaBaseReportServiceImpl;
    private final AdsService adsDailyLabOetaBaseReportServiceImpl;
    private final AdsService adsDailyGameOetaReportServiceImpl;
    private final AdsService adsDailyMiniGameOetaReportServiceImpl;
    private final AdsService adsDailyLabMiniGameOetaReportServiceImpl;
    private final AdsService adsOeta30DaysAccumulateIncomeServiceImpl;

    private final List<AdsService> adsServices;

    @GetMapping("/")
    @Operation(summary = "ads数据同步", description = "触发ads数据同步")
    public String adsHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        log.info("{}  ads数据同步开始", dates);
        for (AdsService service : adsServices) {
            service.syncData(dates);
        }
        log.info("{}  ads数据同步结束", dates);
        return "执行完成ads数据同步";
    }

    // @GetMapping("/hemaDataAnalyseFullDaily")
    // @Operation(summary = "触发ads河马大盘数据同步", description = "触发ads数据同步")
    // public String adsHemaDataAnalyseFullDailyHandle(@RequestParam("dates") Integer dates) {
    // // 获取统计日期
    // if (ObjectUtils.isEmpty(dates)) {
    // dates = DateUtils.getPreviousDate();
    // }
    // adsHemaDataAnalyseFullDailyServiceImpl.syncData(dates);
    // return "执行完成ads数据同步";
    // }

    @GetMapping("/adsFifteenDayCohortConversion")
    @Operation(summary = "触发ads15天同期群转化数据同步", description = "触发ads数据同步")
    public String adsFifteenDayCohortConversionHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsFifteenDayCohortConversionServiceImpl.syncData(dates);
        return "执行完成ads数据同步";
    }

    @GetMapping("/adsFifteenDayCohortMission")
    @Operation(summary = "触发ads15天同期群任务数据同步", description = "触发ads数据同步")
    public String adsFifteenDayCohortMissionHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsFifteenDayCohortMissionServiceImpl.syncData(dates);
        return "执行完成ads数据同步";
    }

    @GetMapping("/adsHemaWithdrawFullDaily")
    @Operation(summary = "触发ads河马提现数据同步", description = "触发ads数据同步")
    public String adsHemaWithdrawFullDailyHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsHemaWithdrawFullDailyServiceImpl.syncData(dates);
        return "执行完成ads数据同步";
    }

    @GetMapping("/adsGameLevelFullDaily")
    @Operation(summary = "触发ads游侠关卡数据同步", description = "触发ads数据同步")
    public String adsGameLevelFullDailyHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsGameLevelFullDailyServiceImpl.syncData(dates);
        return "执行完成ads数据同步";
    }

    @GetMapping("/adsGameAnalyseFullDaily")
    @Operation(summary = "触发ads游戏数据同步", description = "触发ads数据同步")
    public String adsGameAnalyseFullDailyHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsGameAnalyseFullDailyServiceImpl.syncData(dates);
        return "执行完成ads游戏数据同步";
    }

    @GetMapping("/oetaBaseReport")
    @Operation(summary = "触发adsOeta基础报表数据同步", description = "触发ads数据同步")
    public String adsOetaBaseReportHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsDailyOetaBaseReportServiceImpl.syncData(dates);
        return "执行完成ads游戏数据同步";
    }

    @GetMapping("/labOetaBaseReport")
    @Operation(summary = "触发adsLabOeta基础报表数据同步", description = "触发ads数据同步")
    public String adsLabOetaBaseReportHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsDailyLabOetaBaseReportServiceImpl.syncData(dates);
        return "执行完成ads游戏数据同步";
    }

    @GetMapping("/gameOetaReport")
    @Operation(summary = "触发adsGameOetaReport基础报表数据同步", description = "触发ads数据同步")
    public String adsDailyGameOetaReportHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsDailyGameOetaReportServiceImpl.syncData(dates);
        return "执行完成ads游戏数据同步";
    }

    @GetMapping("/miniGameOetaReport")
    @Operation(summary = "触发adsGaemOetaReport基础报表数据同步", description = "触发ads数据同步")
    public String adsDailyMinGameOetaReportHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsDailyMiniGameOetaReportServiceImpl.syncData(dates);
        return "执行完成ads游戏数据同步";
    }

    @GetMapping("/miniLabGameOetaReport")
    @Operation(summary = "触发adsGaemOetaReport基础报表数据同步", description = "触发ads数据同步")
    public String adsDailyLabMinGameOetaReportHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsDailyLabMiniGameOetaReportServiceImpl.syncData(dates);
        return "执行完成ads游戏数据同步";
    }

    @GetMapping("/oeta30DayAccumulateIncome")
    @Operation(summary = "触发adsOeta30DaysAccumulateIncome基础报表数据同步", description = "触发ads数据同步")
    public String oeta30DayAccumulateIncome(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        adsOeta30DaysAccumulateIncomeServiceImpl.syncData(dates);
        return "执行完成ads游戏数据同步";
    }

}
