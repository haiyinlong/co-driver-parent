package com.leo.ad.codriver.dwd.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dwd_user_register")
public class DwdUserOnline {

    private Long id;
    private Long dates;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private Long onlineTime;
    private Date createTime;

}
