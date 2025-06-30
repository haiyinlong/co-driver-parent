package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @TableName dws_daily_cohort_pkg_usrc_conversion
 */
@TableName(value = "dws_daily_cohort_pkg_usrc_conversion")
@Getter
@Setter
public class DwsDailyCohortPkgUsrcConversion extends DwsDailyCohortPkgConversion {

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyCohortPkgUsrcConversion(Integer dates, Integer registerDates, Integer cohortDay, String pkg,
        String userSource) {
        super(dates, registerDates, cohortDay, pkg);
        this.userSource = userSource;
    }

    public static DwsDailyCohortPkgUsrcConversion of(Integer dates, Integer registerDates, Integer registerDay,
        String pkg, String userSource) {
        return new DwsDailyCohortPkgUsrcConversion(dates, registerDates, registerDay, pkg, userSource);
    }

}
