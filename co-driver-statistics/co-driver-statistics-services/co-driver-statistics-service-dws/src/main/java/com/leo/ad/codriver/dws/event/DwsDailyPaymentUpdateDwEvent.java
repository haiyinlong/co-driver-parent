package com.leo.ad.codriver.dws.event;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;
import lombok.Getter;

import java.io.Serial;

@Getter
public class DwsDailyPaymentUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 7957782158850001320L;

    public DwsDailyPaymentUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
