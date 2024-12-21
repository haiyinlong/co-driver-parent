package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserShareRecordUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserShareRecordUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = -2003456348883225788L;

    public DwdUserShareRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
