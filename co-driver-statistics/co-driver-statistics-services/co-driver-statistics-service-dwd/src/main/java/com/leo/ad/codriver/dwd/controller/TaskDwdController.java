package com.leo.ad.codriver.dwd.controller;

import java.util.List;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.service.DwdService;

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
@RequestMapping("/task/dwd")
@Tag(name = "DwdController", description = "Dwd层任务")
@AllArgsConstructor
@Slf4j
public class TaskDwdController {
    private final List<DwdService> dwdServices;
    private final DwdService dwdUserConversionServiceImpl;
    private final DwdService dwdUserRegisterServiceImpl;
    private final DwdService dwdUserLoginRecordServiceImpl;
    private final DwdService dwdUserFinishMissionServiceImpl;
    private final DwdService dwdUserAccountRecordServiceImpl;
    private final DwdService dwdUserWithdrawRecordServiceImpl;
    private final DwdService dwdUserBalanceRecordServiceImpl;
    private final DwdService dwdUserGameRecordServiceImpl;
    private final DwdService dwdUserAdRecordServiceImpl;
    private final DwdService dwdUserOnlineServiceImpl;
    private final DwdService dwdPromotionRecordServiceImpl;
    private final DwdService dwdUserGameRecordOetaServiceImpl;

    private final DwdService dwdUserShareRecordServiceImpl;

    private final DwdService dwdQpLtvRecordServiceImpl;

    @GetMapping("/")
    @Operation(summary = "触发所有dwd", description = "触发dwd数据同步")
    public String dwdHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        for (DwdService service : dwdServices) {
            service.syncData(dates);
        }
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userConversion")
    @Operation(summary = "触发用户转化dwd", description = "触发dwd数据同步")
    public String dwdUserConversionHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserConversionServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userQpLtv")
    @Operation(summary = "触发用户转化dwd", description = "触发dwd数据同步")
    public String dwdUserQpLtvHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdQpLtvRecordServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userFinishMission")
    @Operation(summary = "触发用户完成任务dwd", description = "触发dwd数据同步")
    public String dwdUserFinishMissionHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserFinishMissionServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userRegister")
    @Operation(summary = "触发用户注册", description = "触发dwd数据同步")
    public String dwdUserRegisterHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserRegisterServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userBalance")
    @Operation(summary = "触发用户余额dwd", description = "触发dwd数据同步")
    public String dwdUserBalanceHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserBalanceRecordServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userLoginRecord")
    @Deprecated
    @Operation(summary = "触发用户登录记录dwd", description = "触发dwd数据同步,后续替换成loginRecord")
    public String dwdUserLoginRecordHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserLoginRecordServiceImpl.syncData(dates);
        return "执行完成dwdUserLoginRecordHandle数据同步";
    }

    @GetMapping("/userAccountRecord")
    @Operation(summary = "触发用户账户记录dwd", description = "触发dwd数据同步")
    public String dwdUserAccountRecordHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserAccountRecordServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userWithdrawRecord")
    @Operation(summary = "触发用户提现记录dwd", description = "触发dwd数据同步")
    public String dwdUserWithdrawRecordHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserWithdrawRecordServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userGameRecord")
    @Operation(summary = "触发用户游戏记录dwd", description = "触发dwd数据同步")
    public String userGameRecordHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserGameRecordServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userAdRecord")
    @Operation(summary = "触发用户广告记录dwd", description = "触发dwd数据同步")
    public String userAdRecordHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserAdRecordServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userOnlineRecord")
    @Operation(summary = "触发用户在线记录dwd", description = "触发dwd数据同步")
    public String userOnlineRecordHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserOnlineServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/promotionRecord")
    @Operation(summary = "触发推广记录dwd", description = "触发dwd数据同步")
    public String syncPromotionRecordHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdPromotionRecordServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/oetaGameRecord")
    @Operation(summary = "触发同步Oeta游戏记录dwd", description = "触发dwd数据同步")
    public String syncGameRecordOetaHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserGameRecordOetaServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

    @GetMapping("/userShareRecord")
    @Operation(summary = "触发同步裂变记录dwd", description = "触发dwd数据同步")
    public String syncUserShareRecordHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        // 同步数据到dwd
        dwdUserShareRecordServiceImpl.syncData(dates);
        return "执行完成dwd数据同步";
    }

}
