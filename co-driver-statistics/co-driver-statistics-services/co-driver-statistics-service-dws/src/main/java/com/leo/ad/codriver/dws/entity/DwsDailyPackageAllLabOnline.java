package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_lab_online")
public class DwsDailyPackageAllLabOnline {

    private Long id;
    private Long dates;
    private String version;
    private String pkg;
    private String configGroupName;
    private String configGroupType;
    private Long totalOnlineTime;
    private BigDecimal avgUserOnlineTime;
    private Long newUserOnlineTime;
    private BigDecimal avgNewUserOnlineTime;
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
