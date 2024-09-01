package com.leo.ad.codriver.dwd.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dwd_user_ad_record")
public class DwdUserAdRecord implements BaseEntity {

    private Long id;
    private Long dates;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private String adFormat;
    private String network;
    private Long adExhibit;
    private BigDecimal revenue;
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
