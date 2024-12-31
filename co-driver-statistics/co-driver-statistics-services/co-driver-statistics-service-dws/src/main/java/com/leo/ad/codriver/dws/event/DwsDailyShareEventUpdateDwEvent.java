package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyShareEventUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyShareEventUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = 1582461105189084940L;

    public DwsDailyShareEventUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
