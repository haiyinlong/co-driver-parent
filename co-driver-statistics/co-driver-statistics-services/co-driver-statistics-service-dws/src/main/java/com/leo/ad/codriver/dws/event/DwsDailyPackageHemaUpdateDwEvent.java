package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyPackageHemaUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPackageHemaUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 5218521167248181545L;

    public DwsDailyPackageHemaUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
