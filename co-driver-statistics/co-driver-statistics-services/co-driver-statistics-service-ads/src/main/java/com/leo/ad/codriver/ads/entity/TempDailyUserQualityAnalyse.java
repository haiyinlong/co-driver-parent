package com.leo.ad.codriver.ads.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * CohortUserQualityDTO
 *
 * @author HaiYinLong
 * @version 2025/06/30 15:06
 **/
@Data
@TableName("temp_daily_user_quality_analyse")
public class TempDailyUserQualityAnalyse implements BaseEntity {
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
    private BigDecimal adBoxD1Roi;
    private BigDecimal adBoxD3Roi;
    private BigDecimal adBoxD7Roi;
    private BigDecimal adBoxD14Roi;
    private BigDecimal adBoxD30Roi;
    private BigDecimal adD1Roi;
    private BigDecimal adD3Roi;
    private BigDecimal adD7Roi;
    private BigDecimal adD14Roi;
    private BigDecimal adD30Roi;
    private BigDecimal boxD1Roi;
    private BigDecimal boxD3Roi;
    private BigDecimal boxD7Roi;
    private BigDecimal boxD14Roi;
    private BigDecimal boxD30Roi;
    private BigDecimal ltv1;
    private BigDecimal ltv3;
    private BigDecimal ltv7;
    private BigDecimal ltv14;
}
