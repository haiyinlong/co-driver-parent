package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_payment")
public class DwsDailyPackagePayment implements BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String pkg;
    private String version;
    private String country;
    private Long userNum;
    private BigDecimal payAmount;
    private BigDecimal exchangeRate;
    private BigDecimal changeAmount;

}
