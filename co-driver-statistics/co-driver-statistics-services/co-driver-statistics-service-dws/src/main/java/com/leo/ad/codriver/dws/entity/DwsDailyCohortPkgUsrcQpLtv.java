package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @TableName dws_daily_cohort_pkg_usrc_qp_ltv
 */
@TableName(value = "dws_daily_cohort_pkg_usrc_qp_ltv")
@Setter
@Getter
public class DwsDailyCohortPkgUsrcQpLtv extends DwsDailyCohortPkgQpLtv {

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyCohortPkgUsrcQpLtv() {}

    public DwsDailyCohortPkgUsrcQpLtv(Integer dates, Integer registerDates, String pkg, Integer cohortDay,
        String userSource) {
        super(dates, registerDates, pkg, cohortDay);
        this.userSource = userSource;
    }

    public static DwsDailyCohortPkgUsrcQpLtv of(Integer dates, Integer registerDates, Integer cohortDay, String pkg,
        String userSource) {
        return new DwsDailyCohortPkgUsrcQpLtv(dates, registerDates, pkg, cohortDay, userSource);
    }
}
