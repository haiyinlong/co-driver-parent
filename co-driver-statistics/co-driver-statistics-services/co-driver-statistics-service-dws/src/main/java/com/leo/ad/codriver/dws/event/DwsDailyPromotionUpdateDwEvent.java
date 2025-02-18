package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsDailyPromotionEventUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPromotionUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 1015196351825898272L;

    public DwsDailyPromotionUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
