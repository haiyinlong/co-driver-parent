package com.leo.ad.codriver.dwd.event;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;
import lombok.Getter;

import java.io.Serial;

/**
 * DwdUserPaymentRecordUpdateDwEvent
 *
 * @author HaiYinLong
 **/
@Getter
public class DwdUserPaymentRecordUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = -1804552812959278354L;

    public DwdUserPaymentRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
