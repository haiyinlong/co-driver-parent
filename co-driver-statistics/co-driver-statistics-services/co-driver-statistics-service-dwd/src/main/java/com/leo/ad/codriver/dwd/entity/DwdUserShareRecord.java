package com.leo.ad.codriver.dwd.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dwd_user_share_record")
public class DwdUserShareRecord {

    private Long id;
    private Long dates;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private Long parentUserId;
    private Date createTime;

}
