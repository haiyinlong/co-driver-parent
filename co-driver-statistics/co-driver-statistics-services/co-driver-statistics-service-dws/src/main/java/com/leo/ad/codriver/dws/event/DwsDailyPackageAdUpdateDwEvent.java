package com.leo.ad.codriver.dws.event;

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
public class DwsDailyPackageAdUpdateDwEvent extends CoDriverDwEvent {
    @Serial
    private static final long serialVersionUID = -5601607152327388285L;

    public DwsDailyPackageAdUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
