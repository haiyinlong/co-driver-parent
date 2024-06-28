package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("dws_daily_package_payment")
public class DwsDailyPackagePayment {

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
