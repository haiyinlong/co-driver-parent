package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_pkg_retention")
public class DwsDailyPkgRetention implements BaseEntity {

    private Long id;
    private Long dates;
    private String pkg;
    private Long registerDates;
    private Long registerDay;
    private Long retentionUserNum;

}
