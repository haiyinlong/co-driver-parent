package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dws_daily_package_version_promotion")
public class DwsDailyPackageVersionPromotion {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private String pkg;
    private String country;
    private String version;
    private Long pkgUserNum;
    private BigDecimal cost;
    private BigDecimal changeCost;
    private Long userNum;
    private BigDecimal userRate;
    private BigDecimal pkgCost;
    private BigDecimal pkgChangeCost;
    private BigDecimal cpi;
    private Date createTime;

    public void initDate() {
        this.createTime = new Date();
    }
}
