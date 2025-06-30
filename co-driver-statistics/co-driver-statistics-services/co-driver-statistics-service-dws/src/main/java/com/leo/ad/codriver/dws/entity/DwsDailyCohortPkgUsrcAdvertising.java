package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 * 同期群广告数据
 *
 * @TableName dws_daily_cohort_pkg_usrc_advertising
 */
@TableName(value = "dws_daily_cohort_pkg_usrc_advertising")
@Getter
@Setter
public class DwsDailyCohortPkgUsrcAdvertising extends DwsDailyCohortPkgAdvertising {

    /**
     * 用户来源
     */
    private String userSource;

    public DwsDailyCohortPkgUsrcAdvertising(Integer dates, Integer registerDates, String pkg, Integer cohortDay,
        String userSource) {
        super(dates, registerDates, pkg, cohortDay);
        this.userSource = userSource;
    }

    public static DwsDailyCohortPkgUsrcAdvertising of(Integer dates, Integer registerDates, Integer registerCohortDay,
        String pkg, String userSource) {
        return new DwsDailyCohortPkgUsrcAdvertising(dates, registerDates, pkg, registerCohortDay, userSource);

    }

}
