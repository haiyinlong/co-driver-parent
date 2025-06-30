package com.leo.ad.codriver.dwd.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dwd_user_login_record")
public class DwdUserLoginRecord {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private Long sourceId;
    private Long userId;
    private String pkg;
    private String country;
    private String version;
    private Date registerDate;
    private Integer registerDates;
    private String registerVersion;
    private Integer registerDay;
    private Integer registerCohortDay;
    private Date createTime;
    @TableField(exist = false)
    private String userSource;
}
