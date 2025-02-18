package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyAdConversionEventUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyAdConversionEventUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 8775880568356364147L;

    public DwsDailyAdConversionEventUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
