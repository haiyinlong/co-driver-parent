package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * dws广告汇总统计，有新增的广告商就新增字段
 *
 * @author user
 * @TableName dws_daily_pkg_ver_usrc_advertising
 */
@TableName(value = "dws_daily_pkg_ver_usrc_advertising")
@Data
public class DwsDailyPkgVerUsrcAdvertising extends DwsDailyPkgAdvertising {

    /**
     * 应用版本
     */
    private String version;

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyPkgVerUsrcAdvertising() {
        super();
    }

    public static DwsDailyPkgVerUsrcAdvertising of(Integer dates, String pkg, String version, String usrc,
        Integer userType) {
        DwsDailyPkgVerUsrcAdvertising dwsDailyPkgVerUsrcAdvertising = new DwsDailyPkgVerUsrcAdvertising();
        dwsDailyPkgVerUsrcAdvertising.setDates(dates);
        dwsDailyPkgVerUsrcAdvertising.setPkg(pkg);
        dwsDailyPkgVerUsrcAdvertising.setUserType(userType);
        dwsDailyPkgVerUsrcAdvertising.setVersion(version);
        dwsDailyPkgVerUsrcAdvertising.setUserSource(usrc);
        return dwsDailyPkgVerUsrcAdvertising;
    }
}
