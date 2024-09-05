package com.leo.ad.codriver.common.event.dws;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * 每日包广告数据汇总数据更新
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPackageAllLabAdUpdateDwEvent extends CoDriverDwEvent {
    @Serial
    private static final long serialVersionUID = 3527409359219967945L;

    public DwsDailyPackageAllLabAdUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
