package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_ad_event")
public class DwsDailyPackageAllAdEvent implements BaseEntity {

    private Long id;
    private Integer dates;
    private String version;
    private Integer userType;
    private String pkg;
    private Long clickUserNum;
    private Long clickRecordNum;
    private BigDecimal avgClickRecordNum;
    private Long showUserNum;
    private Long showRecordNum;
    private BigDecimal avgShowRecordNum;
    private BigDecimal clickUserRate;
    private Date createTime;

    public DwsDailyPackageAllAdEvent() {
        this.clickUserNum = 0L;
        this.clickRecordNum = 0L;
        this.showUserNum = 0L;
        this.showRecordNum = 0L;
    }

    public void calculate() {
        this.avgClickRecordNum = BigDecimalUtils.divide(this.clickRecordNum, this.clickUserNum);
        this.avgShowRecordNum = BigDecimalUtils.divide(this.showRecordNum, this.showUserNum);
        this.clickUserRate = BigDecimalUtils.divide(this.clickUserNum, this.showUserNum);
        this.createTime = new Date();
    }
}
