package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * dws广告汇总统计，有新增的广告商就新增字段
 *
 * @author user
 * @TableName dws_daily_pkg_usrc_advertising
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "dws_daily_pkg_usrc_advertising")
@Data
public class DwsDailyPkgUsrcAdvertising extends DwsDailyPkgAdvertising {

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyPkgUsrcAdvertising() {
        super();
    }

    public static DwsDailyPkgUsrcAdvertising of(Integer dates, String pkg, String userUsrc, Integer userType) {
        DwsDailyPkgUsrcAdvertising dwsDailyPkgUsrcAdvertising = new DwsDailyPkgUsrcAdvertising();
        dwsDailyPkgUsrcAdvertising.setDates(dates);
        dwsDailyPkgUsrcAdvertising.setPkg(pkg);
        dwsDailyPkgUsrcAdvertising.setUserType(userType);
        dwsDailyPkgUsrcAdvertising.setUserSource(userUsrc);
        return dwsDailyPkgUsrcAdvertising;
    }
}
