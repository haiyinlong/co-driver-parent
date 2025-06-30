package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 * @TableName dws_daily_cohort_pkg_usrc_share
 */
@TableName(value = "dws_daily_cohort_pkg_usrc_share")
@Setter
@Getter
public class DwsDailyCohortPkgUsrcShare extends DwsDailyCohortPkgShare {

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyCohortPkgUsrcShare(Integer dates, Integer registerDates, Integer cohortDay, String pkg,
        String userSource) {
        super(dates, registerDates, cohortDay, pkg);
        this.userSource = userSource;
    }

    public static DwsDailyCohortPkgUsrcShare of(Integer dates, Integer registerDates, Integer cohortDay, String pkg,
        String userSource) {
        return new DwsDailyCohortPkgUsrcShare(dates, registerDates, cohortDay, pkg, userSource);
    }
}
