package com.leo.ad.codriver.ads.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("ads_daily_oeta_base_report")
public class AdsDailyOetaBaseReport {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private String version;
    private String pkg;
    private String country;
    private Long userType;
    private Long newUserNum;
    private Long secondUserNum;
    private BigDecimal secondUserRetentionRate;
    private Long tertiaryUserNum;
    private BigDecimal tertiaryUserRetentionRate;
    private Long activeUserNum;
    private Long avgUserOnlineTime;
    private Long shareNewUserNum;
    private BigDecimal shareRate;
    private Long offerUserNum;
    private BigDecimal offerRate;
    private Long paymentUserNum;
    private BigDecimal paymentRate;
    private BigDecimal qpLtv;
    private BigDecimal adRewardIncome;
    private Long adRewardUserNum;
    private Long adRewardShowNum;
    private Long adRewardEcpm;
    private BigDecimal avgUserAdRewardShowNum;
    private BigDecimal adRewardRate;
    private Long adDirectSoldUserNum;
    private Long adDirectSoldShowNum;
    private BigDecimal avgUserAdDirectSoldShowNum;
    private Long adDirectSoldClickNum;
    private Long adDirectSoldClickShowRate;
    private BigDecimal adDirectSoldRate;
    private Long avgUserAdShowNum;
    private Long avgUserAdInterShowNum;
    private Long avgUserAdMrecShowNum;
    private BigDecimal withdrawAmount;
    private BigDecimal withdrawFee;
    private BigDecimal withdrawCost;
    private BigDecimal promotionCost;
    private BigDecimal totalExpenditure;
    private BigDecimal cpi;
    private BigDecimal adRoi;
    private BigDecimal totalRoi;
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
