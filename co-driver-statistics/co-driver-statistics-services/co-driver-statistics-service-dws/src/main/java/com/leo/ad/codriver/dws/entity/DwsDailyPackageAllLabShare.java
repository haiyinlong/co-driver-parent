package com.leo.ad.codriver.dws.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_lab_share")
public class DwsDailyPackageAllLabShare implements BaseEntity {

    private Long id;
    private Long dates;
    private String version;
    private String pkg;
    private Long configGroupId;
    private String configGroupType;
    private Long invitationNum;
    private Long fillCodeNum;
    private Long totalUserNum;
    private Date createTime;
    private Date updateTime;

    public void init() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public void update() {
        this.updateTime = new Date();
    }
}
