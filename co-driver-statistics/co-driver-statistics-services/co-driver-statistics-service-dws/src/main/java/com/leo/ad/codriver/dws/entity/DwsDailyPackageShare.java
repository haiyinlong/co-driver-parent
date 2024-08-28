package com.leo.ad.codriver.dws.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * DwsPkgGameFullDaily
 *
 * @author HaiYinLong
 * @version 2024/07/02 16:56
 **/
@Data
@TableName("dws_daily_package_share")
public class DwsDailyPackageShare implements BaseEntity {

    private Long id;
    private Long dates;
    private String version;
    private String pkg;
    private String country;
    private Long invitationNum;
    private Long fillCodeNum;
    private Long totalUserNum;
    private Date createTime;
    private Date updateTime;

    public void update() {
        this.updateTime = new Date();
    }

    public void init() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
