package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

/**
 * DwsDailyPkgAccumulateWithdrawDwEvent
 *
 * @author HaiYinLong
 * @version 2025/03/14 14:16
 **/
public class DwsDailyPkgAccumulateWithdrawDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = -3654631749862273884L;

    public DwsDailyPkgAccumulateWithdrawDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
