package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyPkgConversionUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPkgConversionUpdateDwEvent extends CoDriverDwEvent {
    @Serial
    private static final long serialVersionUID = -2412527955498774385L;

    public DwsDailyPkgConversionUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
