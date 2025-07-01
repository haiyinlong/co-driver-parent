package com.leo.ad.codriver.dws.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwsUpdateFinishEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwsUpdateFinishEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 8323212446021371720L;

    public DwsUpdateFinishEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
