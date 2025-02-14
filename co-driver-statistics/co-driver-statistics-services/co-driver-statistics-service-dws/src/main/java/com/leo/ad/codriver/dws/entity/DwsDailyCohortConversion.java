package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_cohort_conversion")
public class DwsDailyCohortConversion implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private Integer registerDates;
    private String pkg;
    private String registerVersion;
    private String country;
    private Long cohortDay;
    private Long cohortUserNum;
    private Long registerUserNum;
    private BigDecimal conversionRate;
    /**
     * 1导流来源；2盲盒来源
     */
    private Integer sourceType;
}
