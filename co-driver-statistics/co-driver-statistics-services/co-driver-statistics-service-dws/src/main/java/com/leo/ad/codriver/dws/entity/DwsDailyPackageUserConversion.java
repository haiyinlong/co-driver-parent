package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dws_daily_package_user_conversion")
public class DwsDailyPackageUserConversion {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String pkg;
    private String version;
    private Integer userType;
    private String country;
    private Long userNum;
    private Integer sourceType;
}
