package com.leo.ad.codriver.dwd.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dwd_user_event_detail")
public class DwdUserEventDetail implements BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String source;
    private Long sourceId;
    private Long userId;
    private String eventId;
    private String eventMsg;
    private String version;
    private String pkg;
    private String country;
    private Date registerDate;
    private Integer registerDates;
    private String registerVersion;
    private Integer registerDay;
    private Integer registerCohortDay;
    private Date createTime;

}
