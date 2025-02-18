package com.leo.ad.codriver.dwd.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dwd_user_event")
public class DwdUserEvent implements BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private Long userId;
    private String eventId;
    private String eventMsg;
    private String version;
    private String pkg;
    private Date createTime;

    public DwdUserEvent() {
        this.createTime = new Date();
    }

    public String getPkgVersionKey() {
        return pkg + "_" + version;
    }
}
