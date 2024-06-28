package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("dws_daily_package_cohort_retention")
public class DwsDailyPackageCohortRetention implements BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private Integer registerDates;
    private String pkg;
    private String country;
    private String registerVersion;
    private Integer cohortDay;
    private Long registerUserNum;
    private Long retentionUserNum;
    private BigDecimal retentionRate;

}
