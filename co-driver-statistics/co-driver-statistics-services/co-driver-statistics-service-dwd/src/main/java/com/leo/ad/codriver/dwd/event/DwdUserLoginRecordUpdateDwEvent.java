package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserLoginRecordUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserLoginRecordUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = -4585436086228242373L;

    public DwdUserLoginRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
