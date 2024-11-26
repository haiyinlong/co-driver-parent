package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * dws推广花费
 *
 * @TableName dws_daily_pkg_usrc_invested
 */
@TableName(value = "dws_daily_pkg_usrc_invested")
@Data
public class DwsDailyPkgUsrcInvested extends DwsDailyPkgInvested {

    /**
     * 用户来源
     */
    private String userSource;

    public static DwsDailyPkgUsrcInvested of(Integer dates, String pkg, String network) {
        DwsDailyPkgUsrcInvested pkgInvested = new DwsDailyPkgUsrcInvested();
        pkgInvested.setPkg(pkg);
        pkgInvested.setDates(dates);
        pkgInvested.setUserSource(network);
        return pkgInvested;
    }

    public String uniqueKey() {
        return getPkg() + "_" + getUserSource();
    }
}
