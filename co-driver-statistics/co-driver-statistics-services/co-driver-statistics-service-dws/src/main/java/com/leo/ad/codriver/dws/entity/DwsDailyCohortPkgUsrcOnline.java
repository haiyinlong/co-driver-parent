package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @TableName dws_daily_cohort_pkg_usrc_online
 */
@TableName(value = "dws_daily_cohort_pkg_usrc_online")
@Setter
@Getter
public class DwsDailyCohortPkgUsrcOnline extends DwsDailyCohortPkgOnline {

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyCohortPkgUsrcOnline(Integer dates, String pkg, Integer registerDates, Integer cohortDay,
        String userSource) {
        super(dates, pkg, registerDates, cohortDay);
        this.userSource = userSource;
    }

    public static DwsDailyCohortPkgUsrcOnline of(Integer dates, Integer registerDates, Integer registerDay, String pkg,
        String userSource) {

        return new DwsDailyCohortPkgUsrcOnline(dates, pkg, registerDates, registerDay, userSource);
    }
}
