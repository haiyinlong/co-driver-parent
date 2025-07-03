package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyPkgCohortAdvertisingUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPkgCohortAdvertisingUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = 4609609918792288364L;

    public DwsDailyPkgCohortAdvertisingUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
