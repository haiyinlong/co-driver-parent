package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserExchangeRecordUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserExchangeRecordUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 4156011664595561560L;

    public DwdUserExchangeRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
