package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("dws_daily_package_user_withdraw")

public class DwsDailyPackageUserWithdraw {

    private Long id;
    private Long dates;
    private Long projectId;
    private Long productId;
    private String productName;
    private Long channelId;
    private String channelName;
    private String pkg;
    private String country;
    private String version;
    private Long withdrawUserNum;
    private BigDecimal exchangeRate;
    private BigDecimal amount;
    private BigDecimal fee;
    private BigDecimal totalAmount;
    private BigDecimal changeAmount;
    private BigDecimal changeFee;
    private BigDecimal changeTotalAmount;
    private Long firstWithdrawUserNum;
    private Long secondWithdrawUserNum;

}
