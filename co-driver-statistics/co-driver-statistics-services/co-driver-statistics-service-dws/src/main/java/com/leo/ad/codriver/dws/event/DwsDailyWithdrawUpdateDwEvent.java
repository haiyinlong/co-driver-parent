package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyWithdrawEventUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyWithdrawUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 6289224627737022787L;

    public DwsDailyWithdrawUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
