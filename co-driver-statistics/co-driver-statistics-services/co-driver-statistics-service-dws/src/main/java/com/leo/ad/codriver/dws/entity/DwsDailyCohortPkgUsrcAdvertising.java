package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 同期群广告数据
 *
 * @TableName dws_daily_cohort_pkg_usrc_advertising
 */
@TableName(value = "dws_daily_cohort_pkg_usrc_advertising")
@Data
public class DwsDailyCohortPkgUsrcAdvertising extends DwsDailyCohortPkgAdvertising {

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyCohortPkgUsrcAdvertising() {
        super();
    }

    public static DwsDailyCohortPkgUsrcAdvertising ofPkgUsrc(Integer dates, Integer registerDates, Integer registerDay,
        String pkg, String userSource) {
        DwsDailyCohortPkgUsrcAdvertising dwsDailyCohortPkgUsrcAdvertising = new DwsDailyCohortPkgUsrcAdvertising();
        dwsDailyCohortPkgUsrcAdvertising.setDates(dates);
        dwsDailyCohortPkgUsrcAdvertising.setRegisterDates(registerDates);
        dwsDailyCohortPkgUsrcAdvertising.setCohortDay(registerDay);
        dwsDailyCohortPkgUsrcAdvertising.setPkg(pkg);
        dwsDailyCohortPkgUsrcAdvertising.setUserSource(userSource);
        return dwsDailyCohortPkgUsrcAdvertising;
    }
}
