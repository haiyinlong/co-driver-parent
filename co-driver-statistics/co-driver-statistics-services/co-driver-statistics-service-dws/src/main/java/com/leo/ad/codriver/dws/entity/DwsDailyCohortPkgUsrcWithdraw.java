package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @TableName dws_daily_cohort_pkg_usrc_withdraw
 */
@TableName(value = "dws_daily_cohort_pkg_usrc_withdraw")
@Getter
@Setter
public class DwsDailyCohortPkgUsrcWithdraw extends DwsDailyCohortPkgWithdraw {

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyCohortPkgUsrcWithdraw(Integer dates, Integer registerDates, Integer cohortDay, String pkg,
        String userSource) {
        super(dates, registerDates, cohortDay, pkg);
        this.userSource = userSource;
    }

    public static DwsDailyCohortPkgUsrcWithdraw of(Integer dates, Integer registerDates, Integer registerDay,
        String pkg, String userSource) {
        return new DwsDailyCohortPkgUsrcWithdraw(dates, registerDates, registerDay, pkg, userSource);
    }
}
