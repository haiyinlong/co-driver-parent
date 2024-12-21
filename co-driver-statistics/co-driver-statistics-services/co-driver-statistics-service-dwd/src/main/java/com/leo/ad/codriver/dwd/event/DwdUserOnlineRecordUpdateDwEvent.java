package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserOnlineRecordUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserOnlineRecordUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = 4686575402473653110L;

    public DwdUserOnlineRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
