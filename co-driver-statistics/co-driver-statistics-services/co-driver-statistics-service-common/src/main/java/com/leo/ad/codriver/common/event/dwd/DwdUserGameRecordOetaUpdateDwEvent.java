package com.leo.ad.codriver.common.event.dwd;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

/**
 * DwdUserGameRecordOetaUpdateEvent
 *
 * @author HaiYinLong
 * @version 2024/09/05 09:49
 **/
public class DwdUserGameRecordOetaUpdateDwEvent extends CoDriverDwEvent {
    @Serial
    private static final long serialVersionUID = 5452301800669666090L;

    public DwdUserGameRecordOetaUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
