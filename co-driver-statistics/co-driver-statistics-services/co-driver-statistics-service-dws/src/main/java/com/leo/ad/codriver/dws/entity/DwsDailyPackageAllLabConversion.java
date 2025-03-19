package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_lab_conversion")
public class DwsDailyPackageAllLabConversion implements BaseEntity {

    private Long id;
    private Long dates;
    private String pkg;
    private String version;
    private String configGroupName;
    private String configGroupType;
    private Long userType;
    private Long userNum;
    private Integer sourceType;
}
