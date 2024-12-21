package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserLtvRecordUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserLtvRecordUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = -2267056739352820206L;

    public DwdUserLtvRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
