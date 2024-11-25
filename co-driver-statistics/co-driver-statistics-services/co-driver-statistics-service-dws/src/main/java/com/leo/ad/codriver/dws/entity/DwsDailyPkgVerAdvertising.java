package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * dws广告汇总统计，有新增的广告商就新增字段
 *
 * @author user
 * @TableName dws_daily_pkg_ver_advertising
 */
@TableName(value = "dws_daily_pkg_ver_advertising")
@Data
public class DwsDailyPkgVerAdvertising extends DwsDailyPkgAdvertising {

    /**
     * 应用版本
     */
    private String version;

    public DwsDailyPkgVerAdvertising() {
        super();
    }

    public static DwsDailyPkgVerAdvertising of(Integer dates, String pkg, String version, Integer userType) {
        DwsDailyPkgVerAdvertising dwsDailyPkgVerAdvertising = new DwsDailyPkgVerAdvertising();
        dwsDailyPkgVerAdvertising.setDates(dates);
        dwsDailyPkgVerAdvertising.setPkg(pkg);
        dwsDailyPkgVerAdvertising.setUserType(userType);
        dwsDailyPkgVerAdvertising.setVersion(version);
        return dwsDailyPkgVerAdvertising;
    }

}
