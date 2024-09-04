package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_version_promotion")
public class DwsDailyPackageAllVersionPromotion implements BaseEntity {

    private Long id;
    private Long dates;
    private String pkg;
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
