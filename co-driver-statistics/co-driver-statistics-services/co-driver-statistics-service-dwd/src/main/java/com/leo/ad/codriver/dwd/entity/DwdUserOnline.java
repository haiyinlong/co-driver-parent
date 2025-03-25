package com.leo.ad.codriver.dwd.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dwd_user_online")
public class DwdUserOnline implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private Long userId;
    @TableField("`version`")
    private String version;
    private String pkg;
    private String country;
    private Long onlineTime;
    private Date createTime;

}
