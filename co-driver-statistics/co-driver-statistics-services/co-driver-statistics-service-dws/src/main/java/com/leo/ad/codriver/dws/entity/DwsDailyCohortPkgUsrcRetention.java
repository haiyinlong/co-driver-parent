package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @TableName dws_daily_cohort_pkg_usrc_retention
 */
@TableName(value = "dws_daily_cohort_pkg_usrc_retention")
@Setter
@Getter
public class DwsDailyCohortPkgUsrcRetention extends DwsDailyCohortPkgRetention {

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyCohortPkgUsrcRetention(Integer dates, String pkg, Integer registerDates, Integer cohortDay,
        String userSource) {
        super(dates, pkg, registerDates, cohortDay);
        this.userSource = userSource;
    }

    public static DwsDailyCohortPkgUsrcRetention of(int dates, Integer registerDates, Integer registerDay, String pkg,
        String userSource) {
        return new DwsDailyCohortPkgUsrcRetention(dates, pkg, registerDates, registerDay, userSource);
    }
}
