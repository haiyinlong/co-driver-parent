package com.leo.ad.codriver.dws.event;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

/**
 * DwsDailyPkgAccumulateAdDwEvent
 *
 * @author HaiYinLong
 * @version 2025/03/14 14:16
 **/
public class DwsDailyPkgAccumulateAdDwEvent extends CoDriverDwEvent {

    public DwsDailyPkgAccumulateAdDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
