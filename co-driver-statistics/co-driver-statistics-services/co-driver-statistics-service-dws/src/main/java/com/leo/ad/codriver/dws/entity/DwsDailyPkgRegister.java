package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_pkg_register")
public class DwsDailyPkgRegister implements BaseEntity {

    private Long id;
    private Long dates;
    private String pkg;
    private Long userNum;

}
