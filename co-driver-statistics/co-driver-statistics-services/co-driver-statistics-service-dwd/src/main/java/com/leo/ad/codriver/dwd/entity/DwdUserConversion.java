package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("dwd_user_conversion")
public class DwdUserConversion {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private Long userId;
    private String pkg;
    private String version;
    private String registerVersion;
    private String country;
    private Date registerDate;
    private Integer registerDates;
    private Integer type;
    private Date createTime;

}
