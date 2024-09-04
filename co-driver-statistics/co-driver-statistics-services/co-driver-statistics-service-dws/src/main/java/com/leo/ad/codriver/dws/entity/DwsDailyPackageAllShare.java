package com.leo.ad.codriver.dws.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_share")
public class DwsDailyPackageAllShare {

    private Long id;
    private Long dates;
    private String version;
    private String pkg;
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
