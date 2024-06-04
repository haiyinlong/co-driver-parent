package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("dwd_user_finish_mission")
public class DwdUserFinishMission {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private Long userId;
    private String pkg;
    private String country;
    private String registerVersion;
    private String version;
    private Date registerDate;
    private Integer registerDates;
    private String eventType;
    private Integer state;
    private Date createTime;

}
