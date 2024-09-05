package com.leo.ad.codriver.controller;

import java.util.List;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dws.service.DwsService;

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
@RequestMapping("/task/dws")
@Tag(name = "DwsController控制层", description = "Dws层任务")
@AllArgsConstructor
@Slf4j
public class TaskDwsController {

    private final List<DwsService> dwsServices;
    private final DwsService dwsHemaEventFullDailyServiceImpl;
    private final DwsService dwsHemaAccountFullDailyServiceImpl;
    private final DwsService dwsWithdrawFullDailyServiceImpl;
    private final DwsService dwsDailyPackageUserConversionServiceImpl;
    private final DwsService dwsDailyPackageRegisterServiceImpl;
    private final DwsService dwsUserRegisterPkgFullDailyServiceImpl;
    private final DwsService dwsDailyPackageCohortConversionServiceImpl;
    private final DwsService dwsDailyPackagePromotionServiceImpl;
    private final DwsService dwsDailyPackageRetentionServiceImpl;
    private final DwsService dwsDailyPackageCohortRetentionServiceImpl;
    private final DwsService dwsDailyPackageLoginServiceImpl;
    private final DwsService dwsDailyPackagePaymentServiceImpl;
    private final DwsService dwsDailyPackageCohortMissionServiceImpl;
    private final DwsService dwsHemaBalanceFullDailyServiceImpl;
    private final DwsService dwsDailyPackageGameLevelServiceImpl;
    private final DwsService dwsPkgGameFullDailyServiceImpl;
    private final DwsService dwsPkgUserFullDailyServiceImpl;
    private final DwsService dwsPkgRetentionFullDailyServiceImpl;
    private final DwsService dwsDailyPackageAdServiceImpl;
    private final DwsService dwsDailyPackageOnlineServiceImpl;

    private final DwsService dwsDailyPackageAllLabAdServiceImpl;
    private final DwsService dwsDailyPackageAllLabConversionServiceImpl;
    private final DwsService dwsDailyPackageAllLabLoginServiceImpl;
    private final DwsService dwsDailyPackageAllLabOnlineServiceImpl;
    private final DwsService dwsDailyPackageAllLabQpLtvServiceImpl;
    private final DwsService dwsDailyPackageAllLabRegisterServiceImpl;
    private final DwsService dwsDailyPackageAllLabRetentionServiceImpl;
    private final DwsService dwsDailyPackageAllLabShareServiceImpl;
    private final DwsService dwsDailyPackageAllLabVersionPromotionServiceImpl;
    private final DwsService dwsDailyPackageAllLabWithdrawServiceImpl;

    @GetMapping("/")
    @Operation(summary = "触发dws所有task", description = "触发dws数据同步")
    public String dwsHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        log.info("{}  dws数据同步开始", dates);
        long dwdStartTime;
        for (DwsService service : dwsServices) {
            dwdStartTime = System.currentTimeMillis();
            service.syncData(dates);
            log.info("{} dws {} 同步结束, 耗时：{}", dates, service.getClass().getSimpleName(),
                (System.currentTimeMillis() - dwdStartTime) / 1000);
        }
        log.info("{} dws数据同步结束", dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packageUserConversion")
    @Operation(summary = "触发dws用户转化task", description = "触发dws数据同步")
    public String dwsPackageOfferHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageUserConversionServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packageCohortConversion")
    @Operation(summary = "触发dws用户同期转化task", description = "触发dws数据同步")
    public String dwsPackageCohortConversionHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageCohortConversionServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packageCohortMission")
    @Operation(summary = "触发dws用户同期任务task", description = "触发dws数据同步")
    public String dwsPackageCohortMissionHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageCohortMissionServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packageRegister")
    @Operation(summary = "触发dws每日包注册用户task", description = "触发dws数据同步")
    public String dwsPackageRegisterHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageRegisterServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/userRegisterPkg")
    @Operation(summary = "触发dws每日用户注册task", description = "触发dws数据同步")
    public String dwsUserRegisterPkgHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsUserRegisterPkgFullDailyServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packageLogin")
    @Operation(summary = "触发dws每日用户登录task", description = "触发dws数据同步")
    public String dwsPackageLoginHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageLoginServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packageRetention")
    @Operation(summary = "触发dws每日用户留存task", description = "触发dws数据同步")
    public String dwsPackageRetentionHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageRetentionServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packageCohortRetention")
    @Operation(summary = "触发dws每日用户同期留存task", description = "触发dws数据同步")
    public String dwsPackageCohortRetentionHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageCohortRetentionServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/withdraw")
    @Operation(summary = "触发dws每日用户提现task", description = "触发dws数据同步")
    public String dwsWithdrawHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsWithdrawFullDailyServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/hemaEvent")
    @Operation(summary = "触发dws 河马事件记录task", description = "触发dws数据同步")
    public String dwsHemaEventHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsHemaEventFullDailyServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/hemaBalance")
    @Operation(summary = "触发dws河马余额记录task", description = "触发dws数据同步")
    public String dwsHemaBalanceHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsHemaBalanceFullDailyServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/hemaAccount")
    @Operation(summary = "触发dws河马账户积分记录task", description = "触发dws数据同步")
    public String dwsHemaAccountHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsHemaAccountFullDailyServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packagePromotion")
    @Operation(summary = "触发dws推广花费记录task", description = "触发dws数据同步")
    public String dwsPackagePromotionHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackagePromotionServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packagePayment")
    @Operation(summary = "触发dws线下包付费记录task", description = "触发dws数据同步")
    public String dwsPackagePaymentHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackagePaymentServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packageGameLevel")
    @Operation(summary = "触发dws游戏关卡记录task", description = "触发dws数据同步")
    public String dwsPackageGameLevelHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageGameLevelServiceImpl.syncData(dates);
        return "执行完成dws游戏关卡记录同步";
    }

    @GetMapping("/pkgGameInfo")
    @Operation(summary = "触发dws游戏维度统计task", description = "触发dws数据同步")
    public String dwsPkgGameFullDailyHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsPkgGameFullDailyServiceImpl.syncData(dates);
        return "执行完成dws游戏维度统计同步";
    }

    @GetMapping("/pkgUserInfo")
    @Operation(summary = "触发dws包维度的用户数据统计task", description = "触发dws数据同步")
    public String dwsPkgUserFullDailyHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsPkgUserFullDailyServiceImpl.syncData(dates);
        return "执行完成dws包维度的用户数据统同步";
    }

    @GetMapping("/pkgRetentionFullDaily")
    @Operation(summary = "触发dws包维度的用户留存数据统计task", description = "触发dws数据同步")
    public String dwsPkgRetentionFullDailyHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsPkgRetentionFullDailyServiceImpl.syncData(dates);
        return "执行完成dws包维度的用户用户留存数据统同步";
    }

    @GetMapping("/packageAd")
    @Operation(summary = "触发dws包维度的包广告数据统计task", description = "触发dws数据同步")
    public String dwsDailyPackageAdHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageAdServiceImpl.syncData(dates);
        return "执行完成dws包维度的包广告数据统计同步";
    }

    @GetMapping("/packageOnline")
    @Operation(summary = "触发dws包维度的在线时长数据统计task", description = "触发dws数据同步")
    public String dwsDailyPackageOnlineHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageOnlineServiceImpl.syncData(dates);
        return "执行完成dws包维度的在线时长数据统计同步";
    }

    @GetMapping("/updateAllLab")
    @Operation(summary = "触发dws包维度的在线时长数据统计task", description = "触发dws数据同步")
    public String dwsupdateAllLabHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageAllLabRegisterServiceImpl.syncData(dates);
        dwsDailyPackageAllLabConversionServiceImpl.syncData(dates);
        dwsDailyPackageAllLabLoginServiceImpl.syncData(dates);
        dwsDailyPackageAllLabOnlineServiceImpl.syncData(dates);
        dwsDailyPackageAllLabQpLtvServiceImpl.syncData(dates);
        dwsDailyPackageAllLabRetentionServiceImpl.syncData(dates);
        dwsDailyPackageAllLabShareServiceImpl.syncData(dates);
        dwsDailyPackageAllLabWithdrawServiceImpl.syncData(dates);
        dwsDailyPackageAllLabAdServiceImpl.syncData(dates);
        dwsDailyPackageAllLabVersionPromotionServiceImpl.syncData(dates);
        return "执行完成dws包维度的在线时长数据统计同步";
    }
}
