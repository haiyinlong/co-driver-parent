package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyPkgFragmentTransactionSummaryUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPkgFragmentTransactionSummaryUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = -5082313211278448722L;

    public DwsDailyPkgFragmentTransactionSummaryUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
