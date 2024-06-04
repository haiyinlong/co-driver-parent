package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dws_daily_package_register")
public class DwsDailyPackageRegister {

    private Long id;
    private Integer dates;
    private String pkg;
    private String country;
    private String version;
    private Long userNum;

}
