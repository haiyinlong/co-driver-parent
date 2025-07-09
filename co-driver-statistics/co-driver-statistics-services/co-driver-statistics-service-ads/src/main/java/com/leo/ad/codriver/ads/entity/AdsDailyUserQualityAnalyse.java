package com.leo.ad.codriver.ads.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName ads_daily_user_quality_analyse
 */
@TableName(value = "ads_daily_user_quality_analyse")
@Data
public class AdsDailyUserQualityAnalyse implements BaseEntity {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 登录用户数
     */
    private Long activityUserNum;

    /**
     *
     */
    private Long newUserNum;

    /**
     *
     */
    private BigDecimal dollarCost;

    /**
     *
     */
    private BigDecimal cpi;

    /**
     *
     */
    private Long secondDayUserNum;

    /**
     *
     */
    private Long thirdDayUserNum;

    /**
     *
     */
    private BigDecimal gameOnlineTime;

    /**
     *
     */
    private BigDecimal adRewardRate;

    /**
     *
     */
    private BigDecimal adRewardAvgShowNum;

    /**
     *
     */
    private BigDecimal adRewardEcpm;

    /**
     *
     */
    private BigDecimal adRewardArpu;

    /**
     *
     */
    private BigDecimal conversionRate;

    /**
     *
     */
    private BigDecimal paymentRate;

    /**
     *
     */
    private BigDecimal paymentArpu;

    /**
     *
     */
    private BigDecimal adRewardAvgShowNumDau;

    /**
     *
     */
    private BigDecimal adRewardEcpmDau;

    /**
     *
     */
    private BigDecimal shareRate;

    /**
     *
     */
    private BigDecimal adRewardArpuDau;

    /**
     *
     */
    private BigDecimal paymentArpuDau;

    /**
     *
     */
    private BigDecimal adBoxD1Roi;

    /**
     *
     */
    private BigDecimal adBoxD3Roi;

    /**
     *
     */
    private BigDecimal adBoxD7Roi;

    /**
     *
     */
    private BigDecimal adBoxD14Roi;

    /**
     *
     */
    private BigDecimal adBoxD30Roi;

    /**
     *
     */
    private BigDecimal adD1Roi;

    /**
     *
     */
    private BigDecimal adD3Roi;

    /**
     *
     */
    private BigDecimal adD7Roi;

    /**
     *
     */
    private BigDecimal adD14Roi;

    /**
     *
     */
    private BigDecimal adD30Roi;

    /**
     *
     */
    private BigDecimal boxD1Roi;

    /**
     *
     */
    private BigDecimal boxD3Roi;

    /**
     *
     */
    private BigDecimal boxD7Roi;

    /**
     *
     */
    private BigDecimal boxD14Roi;

    /**
     *
     */
    private BigDecimal boxD30Roi;

    /**
     *
     */
    private BigDecimal ltv1;

    /**
     *
     */
    private BigDecimal ltv3;

    /**
     *
     */
    private BigDecimal ltv7;

    /**
     *
     */
    private BigDecimal ltv14;

    /**
     *
     */
    private BigDecimal ltv30;

    private BigDecimal d1Ad;
    private BigDecimal d3Ad;
    private BigDecimal d7Ad;
    private BigDecimal d14Ad;
    private BigDecimal d30Ad;
    private BigDecimal d1Box;
    private BigDecimal d3Box;
    private BigDecimal d7Box;
    private BigDecimal d14Box;
    private BigDecimal d30Box;
    private BigDecimal d1Withdraw;
    private BigDecimal d3Withdraw;
    private BigDecimal d7Withdraw;
    private BigDecimal d14Withdraw;
    private BigDecimal d30Withdraw;

    /**
     *
     */
    private Long rewardNotCustomDirectsoldUserNum;
    private Long rewardNotCustomDirectsoldShowCount;
    private BigDecimal rewardNotCustomDirectsoldIncome;
    private BigDecimal totalNotCustomBannerIncome;
    private Long newUserOnlineTime;
    private Long paymentUserNum;
    private BigDecimal paymentEventLtv;
    private BigDecimal paymentDauEventLtv;
    private Long conversionUserNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public void resetCohortData(LocalDateTime now) {
        this.createTime = now;
        this.adBoxD1Roi = BigDecimal.ZERO;
        this.adBoxD3Roi = BigDecimal.ZERO;
        this.adBoxD7Roi = BigDecimal.ZERO;
        this.adBoxD14Roi = BigDecimal.ZERO;
        this.adBoxD30Roi = BigDecimal.ZERO;
        this.boxD1Roi = BigDecimal.ZERO;
        this.boxD3Roi = BigDecimal.ZERO;
        this.boxD7Roi = BigDecimal.ZERO;
        this.boxD14Roi = BigDecimal.ZERO;
        this.boxD30Roi = BigDecimal.ZERO;
        this.adD1Roi = BigDecimal.ZERO;
        this.adD3Roi = BigDecimal.ZERO;
        this.adD7Roi = BigDecimal.ZERO;
        this.adD14Roi = BigDecimal.ZERO;
        this.adD30Roi = BigDecimal.ZERO;
        this.ltv1 = BigDecimal.ZERO;
        this.ltv3 = BigDecimal.ZERO;
        this.ltv7 = BigDecimal.ZERO;
        this.ltv14 = BigDecimal.ZERO;
        this.ltv30 = BigDecimal.ZERO;
        this.d1Ad = BigDecimal.ZERO;
        this.d3Ad = BigDecimal.ZERO;
        this.d7Ad = BigDecimal.ZERO;
        this.d14Ad = BigDecimal.ZERO;
        this.d30Ad = BigDecimal.ZERO;
        this.d1Box = BigDecimal.ZERO;
        this.d3Box = BigDecimal.ZERO;
        this.d7Box = BigDecimal.ZERO;
        this.d14Box = BigDecimal.ZERO;
        this.d30Box = BigDecimal.ZERO;
        this.d1Withdraw = BigDecimal.ZERO;
        this.d3Withdraw = BigDecimal.ZERO;
        this.d7Withdraw = BigDecimal.ZERO;
        this.d14Withdraw = BigDecimal.ZERO;
        this.d30Withdraw = BigDecimal.ZERO;
    }

    public void updateDay1Day3Day7(TempDailyUserQualityAnalyse tempDailyUserQualityAnalyse) {
        this.adD1Roi = tempDailyUserQualityAnalyse.getAdD1Roi();
        this.adD3Roi = tempDailyUserQualityAnalyse.getAdD3Roi();
        this.adD7Roi = tempDailyUserQualityAnalyse.getAdD7Roi();
        this.boxD1Roi = tempDailyUserQualityAnalyse.getBoxD1Roi();
        this.boxD3Roi = tempDailyUserQualityAnalyse.getBoxD3Roi();
        this.boxD7Roi = tempDailyUserQualityAnalyse.getBoxD7Roi();
        this.adBoxD1Roi = tempDailyUserQualityAnalyse.getAdBoxD1Roi();
        this.adBoxD3Roi = tempDailyUserQualityAnalyse.getAdBoxD3Roi();
        this.adBoxD7Roi = tempDailyUserQualityAnalyse.getAdBoxD7Roi();
        this.ltv1 = tempDailyUserQualityAnalyse.getLtv1();
        this.ltv3 = tempDailyUserQualityAnalyse.getLtv3();
        this.ltv7 = tempDailyUserQualityAnalyse.getLtv7();
        this.d1Ad = tempDailyUserQualityAnalyse.getD1Ad();
        this.d3Ad = tempDailyUserQualityAnalyse.getD3Ad();
        this.d7Ad = tempDailyUserQualityAnalyse.getD7Ad();
        this.d1Box = tempDailyUserQualityAnalyse.getD1Box();
        this.d3Box = tempDailyUserQualityAnalyse.getD3Box();
        this.d7Box = tempDailyUserQualityAnalyse.getD7Box();
        this.d1Withdraw = tempDailyUserQualityAnalyse.getD1Withdraw();
        this.d3Withdraw = tempDailyUserQualityAnalyse.getD3Withdraw();
        this.d7Withdraw = tempDailyUserQualityAnalyse.getD7Withdraw();
    }

    public void updateDay14(TempDailyUserQualityAnalyse tempDailyUserQualityAnalyse) {
        this.boxD14Roi = tempDailyUserQualityAnalyse.getBoxD14Roi();
        this.adBoxD14Roi = tempDailyUserQualityAnalyse.getAdBoxD14Roi();
        this.adD14Roi = tempDailyUserQualityAnalyse.getAdD14Roi();
        this.ltv14 = tempDailyUserQualityAnalyse.getLtv14();
        this.d14Ad = tempDailyUserQualityAnalyse.getD14Ad();
        this.d14Box = tempDailyUserQualityAnalyse.getD14Box();
        this.d14Withdraw = tempDailyUserQualityAnalyse.getD14Withdraw();
    }

    public void updateDay30(TempDailyUserQualityAnalyse tempDailyUserQualityAnalyse) {
        this.boxD30Roi = tempDailyUserQualityAnalyse.getBoxD30Roi();
        this.adBoxD30Roi = tempDailyUserQualityAnalyse.getAdBoxD30Roi();
        this.adD30Roi = tempDailyUserQualityAnalyse.getAdD30Roi();
        this.d30Ad = tempDailyUserQualityAnalyse.getD30Ad();
        this.d30Box = tempDailyUserQualityAnalyse.getD30Box();
        this.d30Withdraw = tempDailyUserQualityAnalyse.getD30Withdraw();
    }
}
