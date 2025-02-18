package com.leo.ad.codriver.dws.controller;

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
    private final DwsService dwsHemaAccountFullDailyServiceImpl;
    private final DwsService dwsWithdrawFullDailyServiceImpl;
    private final DwsService dwsDailyPackageUserConversionServiceImpl;
    private final DwsService dwsDailyPackageRegisterServiceImpl;
    private final DwsService dwsUserRegisterPkgFullDailyServiceImpl;
    private final DwsService dwsDailyPackageCohortConversionServiceImpl;
    private final DwsService dwsDailyPackagePromotionServiceImpl;
    private final DwsService dwsDailyPkgUsrcInvestedServiceImpl;
    private final DwsService dwsDailyPkgInvestedServiceImpl;

    private final DwsService dwsDailyPackageLoginServiceImpl;
    private final DwsService dwsDailyPackagePaymentServiceImpl;
    private final DwsService dwsDailyPackageCohortMissionServiceImpl;
    private final DwsService dwsHemaBalanceFullDailyServiceImpl;
    private final DwsService dwsDailyPackageGameLevelServiceImpl;
    private final DwsService dwsPkgGameFullDailyServiceImpl;
    private final DwsService dwsPkgUserFullDailyServiceImpl;
    private final DwsService dwsDailyPackageOnlineServiceImpl;

    private final DwsService dwsDailyPackageShareServiceImpl;
    private final DwsService dwsDailyPkgVerAdvertisingServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcAdvertisingServiceImpl;
    private final DwsService dwsDailyPkgAdvertisingServiceImpl;
    private final DwsService dwsDailyPkgUsrcAdvertisingServiceImpl;

    private final DwsService dwsDailyPackageAllLabAdServiceImpl;
    private final DwsService dwsDailyPackageAllLabConversionServiceImpl;
    private final DwsService dwsDailyPackageAllLabLoginServiceImpl;
    private final DwsService dwsDailyPackageAllLabOnlineServiceImpl;
    private final DwsService dwsDailyPackageAllLabQpLtvServiceImpl;
    private final DwsService dwsDailyPackageAllLabRegisterServiceImpl;
    private final DwsService dwsDailyPackageAllLabShareServiceImpl;
    private final DwsService dwsDailyPackageAllShareServiceImpl;
    private final DwsService dwsDailyPackageAllLabVersionPromotionServiceImpl;
    private final DwsService dwsDailyPackageAllLabWithdrawServiceImpl;
    private final DwsService dwsDailyPackageAllGameServiceImpl;
    private final DwsService dwsDailyPkgGameServiceImpl;
    private final DwsService dwsDailyPackageAllLabGameServiceImpl;
    private final DwsService dwsDailyPackageAllAssetExchangeServiceImpl;
    private final DwsService dwsDailyPackageAllLabAssetExchangeServiceImpl;
    // private final DwsService dwsDailyPackageAllGameSingleServiceImpl;
    // private final DwsService dwsDailyPackageAllLabGameSingleServiceImpl;

    // 留存
    private final DwsService dwsDailyPackageRetentionServiceImpl;
    private final DwsService dwsDailyPackageCohortRetentionServiceImpl;
    private final DwsService dwsPkgRetentionFullDailyServiceImpl;
    private final DwsService dwsDailyPackageAllLabRetentionServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcRetentionServiceImpl;
    private final DwsService dwsDailyPkgRetentionServiceImpl;
    private final DwsService dwsDailyPkgUsrcRetentionServiceImpl;

    // oeta base user source 统计
    private final DwsService dwsDailyPkgVerUsrcConversionServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcLoginServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcOnlineServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcPromotionServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcQpLtvServiceImpl;
    // private final DwsService dwsDailyPkgVerUsrcRegisterServiceImpl;
    private final DwsService dwsDailyRegisterServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcShareServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcWithdrawServiceImpl;
    private final DwsService dwsDailyPkgUsrcConversionServiceImpl;
    private final DwsService dwsDailyPkgUsrcLoginServiceImpl;
    private final DwsService dwsDailyPkgUsrcOnlineServiceImpl;
    private final DwsService dwsDailyPkgOnlineServiceImpl;
    private final DwsService dwsDailyPkgUsrcPromotionServiceImpl;
    private final DwsService dwsDailyPkgUsrcQpLtvServiceImpl;
    // private final DwsService dwsDailyPkgUsrcRegisterServiceImpl;
    private final DwsService dwsDailyPkgUsrcShareServiceImpl;
    private final DwsService dwsDailyPkgUsrcWithdrawServiceImpl;
    // 广告事件，广告转化
    private final DwsService dwsDailyPkgVerAdConversionEventServiceImpl;
    private final DwsService dwsDailyPkgUsrcAdConversionEventServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcAdConversionEventServiceImpl;
    private final DwsService dwsDailyPkgAdConversionEventServiceImpl;

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

    @GetMapping("/packageGameOetaHandle")
    @Operation(summary = "触发dws游戏oeta数据", description = "触发dws数据同步")
    public String dwsPackageGameOetaHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageAllGameServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/pkgGameOetaHandle")
    @Operation(summary = "触发pkg dws游戏oeta数据", description = "触发dws数据同步")
    public String pkgGameOetaHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPkgGameServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    @GetMapping("/packageLabGameOetaHandle")
    @Operation(summary = "触发dwsLab游戏oeta数据", description = "触发dws数据同步")
    public String dwsPackageLabGameOetaHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageAllLabGameServiceImpl.syncData(dates);
        return "执行完成dws数据同步";
    }

    // @GetMapping("/packageGameSingleOetaHandle")
    // @Operation(summary = "触发dws各个游戏oeta数据", description = "触发dws数据同步")
    // public String dwsPackageGameSingleOetaHandle(@RequestParam("dates") Integer dates) {
    // // 获取统计日期
    // if (ObjectUtils.isEmpty(dates)) {
    // dates = DateUtils.getPreviousDate();
    // }
    // dwsDailyPackageAllGameSingleServiceImpl.syncData(dates);
    // return "执行完成dws数据同步";
    // }

    // @GetMapping("/packageLabGameSingleOetaHandle")
    // @Operation(summary = "触发dwsLab各个游戏oeta数据", description = "触发dws数据同步")
    // public String dwsPackageLabGameSingleOetaHandle(@RequestParam("dates") Integer dates) {
    // // 获取统计日期
    // if (ObjectUtils.isEmpty(dates)) {
    // dates = DateUtils.getPreviousDate();
    // }
    // dwsDailyPackageAllLabGameSingleServiceImpl.syncData(dates);
    // return "执行完成dws数据同步";
    // }

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
        try {
            dwsDailyRegisterServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageRegisterServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsUserRegisterPkgFullDailyServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        try {
            dwsDailyPackageRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageCohortRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsPkgRetentionFullDailyServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllLabRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    // @GetMapping("/hemaEvent")
    // @Operation(summary = "触发dws 河马事件记录task", description = "触发dws数据同步")
    // public String dwsHemaEventHandle(@RequestParam("dates") Integer dates) {
    // // 获取统计日期
    // if (ObjectUtils.isEmpty(dates)) {
    // dates = DateUtils.getPreviousDate();
    // }
    // dwsHemaEventFullDailyServiceImpl.syncData(dates);
    // return "执行完成dws数据同步";
    // }

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
        try {
            dwsDailyPkgUsrcInvestedServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgInvestedServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackagePromotionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        // dwsDailyPackageAdServiceImpl.syncData(dates);
        dwsDailyPkgVerAdvertisingServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcAdvertisingServiceImpl.syncData(dates);
        dwsDailyPkgAdvertisingServiceImpl.syncData(dates);
        dwsDailyPkgUsrcAdvertisingServiceImpl.syncData(dates);
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

    @GetMapping("/packageAllAssetExchange")
    @Operation(summary = "触发dws包维度的资产兑换数据统计task", description = "触发dws数据同步")
    public String dwsDailyPackageAllAssetExchangeHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageAllAssetExchangeServiceImpl.syncData(dates);
        return "执行完成dws包维度的资产兑换数据统计同步";
    }

    @GetMapping("/packageAllLabAssetExchange")
    @Operation(summary = "触发dws包维度的ab资产兑换数据统计task", description = "触发dws数据同步")
    public String dwsDailyPackageAllLabAssetExchangeHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageAllLabAssetExchangeServiceImpl.syncData(dates);
        return "执行完成dws包维度的ab资产兑换数据统计同步";
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
        dwsDailyPackageAllLabAssetExchangeServiceImpl.syncData(dates);
        return "执行完成dws包维度的在线时长数据统计同步";
    }

    @GetMapping("/oetaBaseUsrc")
    @Operation(summary = "触发dws包oetaBaseUsrc维度数据统计", description = "触发dws数据同步")
    public String dwsOetaBaseUsrcHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyRegisterServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcLoginServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcConversionServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcOnlineServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcQpLtvServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcRetentionServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcShareServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcWithdrawServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcPromotionServiceImpl.syncData(dates);
        dwsDailyPkgUsrcConversionServiceImpl.syncData(dates);
        dwsDailyPkgUsrcLoginServiceImpl.syncData(dates);
        dwsDailyPkgUsrcOnlineServiceImpl.syncData(dates);
        dwsDailyPkgOnlineServiceImpl.syncData(dates);
        dwsDailyPkgUsrcPromotionServiceImpl.syncData(dates);
        dwsDailyPkgUsrcQpLtvServiceImpl.syncData(dates);
        dwsDailyPkgUsrcRetentionServiceImpl.syncData(dates);
        dwsDailyPkgUsrcShareServiceImpl.syncData(dates);
        dwsDailyPkgUsrcWithdrawServiceImpl.syncData(dates);
        dwsDailyPkgVerAdvertisingServiceImpl.syncData(dates);
        return "执行完成dws包oetaBaseUsrc维度数据统计同步";
    }

    @GetMapping("/packageShare")
    @Operation(summary = "触发dws包packageShare维度数据统计", description = "触发dws数据同步")
    public String dwsPackageShareHandle(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwsDailyPackageAllShareServiceImpl.syncData(dates);
        dwsDailyPackageShareServiceImpl.syncData(dates);
        dwsDailyPkgVerUsrcShareServiceImpl.syncData(dates);
        dwsDailyPkgUsrcShareServiceImpl.syncData(dates);
        dwsDailyPackageAllLabShareServiceImpl.syncData(dates);
        return "执行完成dws包packageShare维度数据统计同步";
    }

    @GetMapping("/packageAdConversionEvent")
    @Operation(summary = "触发dws包AdConversionEvent维度数据统计", description = "触发dws数据同步")
    public String dwsPkgVerAdConversionEvent(@RequestParam("dates") Integer dates) {
        // 获取统计日期
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        try {
            dwsDailyPkgVerAdConversionEventServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error("dws包dwsDailyPkgVerAdConversionEventServiceImpl维度数据统计同步异常", e);
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcAdConversionEventServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error("dws包dwsDailyPkgUsrcAdConversionEventServiceImpl维度数据统计同步异常", e);
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcAdConversionEventServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error("dws包dwsDailyPkgVerUsrcAdConversionEventServiceImpl维度数据统计同步异常", e);
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgAdConversionEventServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error("dws包dwsDailyPkgAdConversionEventServiceImpl维度数据统计同步异常", e);
            throw new RuntimeException(e);
        }
        return "执行完成dws包AdConversionEvent维度数据统计同步";
    }

}
