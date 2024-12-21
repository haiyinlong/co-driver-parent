package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyPkgExchangeRecordUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPkgExchangeRecordUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = -4892089532567683876L;

    public DwsDailyPkgExchangeRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
