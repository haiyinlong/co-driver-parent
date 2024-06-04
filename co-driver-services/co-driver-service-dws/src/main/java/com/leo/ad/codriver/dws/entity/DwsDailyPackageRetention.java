package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dws_daily_package_retention")
public class DwsDailyPackageRetention {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String pkg;
    private String country;
    private String version;
    private Integer registerDates;
    private Integer registerDay;
    private Long retentionUserNum;

}
