package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_lab_retention")
public class DwsDailyPackageAllLabRetention {

    private Long id;
    private Long dates;
    private String pkg;
    private String version;
    private Long configGroupId;
    private String configGroupType;
    private Long registerDates;
    private Long registerDay;
    private Long retentionUserNum;

}
