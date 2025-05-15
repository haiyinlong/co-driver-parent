package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyPkgFragmentSummaryUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPkgFragmentSummaryUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = 2266185748657937758L;

    public DwsDailyPkgFragmentSummaryUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
