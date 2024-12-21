package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserRegisterRecordUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserRegisterRecordUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = -4316194183502031525L;

    public DwdUserRegisterRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
