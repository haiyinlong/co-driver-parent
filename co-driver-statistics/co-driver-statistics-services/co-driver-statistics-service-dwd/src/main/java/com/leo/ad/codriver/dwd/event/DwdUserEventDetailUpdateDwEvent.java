package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserEventDetailUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserEventDetailUpdateDwEvent extends CoDriverDwEvent {
    @Serial
    private static final long serialVersionUID = 2775925274512738289L;

    public DwdUserEventDetailUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
