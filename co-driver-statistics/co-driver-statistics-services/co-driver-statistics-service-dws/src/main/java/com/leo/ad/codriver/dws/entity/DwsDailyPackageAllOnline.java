package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_online")
public class DwsDailyPackageAllOnline implements BaseEntity {

    private Long id;
    private Long dates;
    private String version;
    private String pkg;
    private Long totalOnlineTime;
    @TableField(exist = false)
    private Long totalUserNum;
    private BigDecimal avgUserOnlineTime;
    private Long newUserOnlineTime;
    @TableField(exist = false)
    private Long newUserNum;
    private BigDecimal avgNewUserOnlineTime;
    private Date createTime;

    public void init() {
        this.createTime = new Date();
        this.avgUserOnlineTime = BigDecimalUtils.divide(totalOnlineTime, totalUserNum);
        this.avgNewUserOnlineTime = BigDecimalUtils.divide(newUserOnlineTime, newUserNum);
    }

}
