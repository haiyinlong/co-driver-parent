package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

/**
 * 事件对应的是直接从ods层获取数据，弃用。
 *
 * @author HaiYinLong
 * @version 2024/09/10 15:14
 **/
@Deprecated
public class DwsDailyPackagePromotionUpdateEvent extends CoDriverDwEvent {
    @Serial
    private static final long serialVersionUID = -1185010442102214041L;

    public DwsDailyPackagePromotionUpdateEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
