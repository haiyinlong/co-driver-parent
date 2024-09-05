package com.leo.ad.codriver.common.event.dws;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverEvent;

import lombok.Getter;

/**
 * 每日包广告数据汇总数据更新
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPackageAllAdUpdateEvent extends CoDriverEvent {
    @Serial
    private static final long serialVersionUID = 3527409359219967945L;

    public DwsDailyPackageAllAdUpdateEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
