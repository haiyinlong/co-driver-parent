package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdPromotionRecordUpdateEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUpdateFinishEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 8323212446021371720L;

    public DwdUpdateFinishEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
