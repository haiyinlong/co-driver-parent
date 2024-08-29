package com.leo.ad.codriver.dws.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dws_daily_package_online")
public class DwsDailyPackageOnline {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private String version;
    private String pkg;
    private String country;
    private Long totalOnlineTime;
    private Long newUserOnlineTime;
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
