package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserWithdrawRecordUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserWithdrawRecordUpdateDwEvent extends CoDriverDwEvent {
    @Serial
    private static final long serialVersionUID = 4158133776654572026L;

    public DwdUserWithdrawRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
