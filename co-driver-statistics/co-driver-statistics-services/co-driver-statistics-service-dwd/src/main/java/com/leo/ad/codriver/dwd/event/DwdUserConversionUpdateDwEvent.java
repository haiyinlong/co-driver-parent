package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserConversionUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/12/20 12:08
 **/
@Getter
public class DwdUserConversionUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 1498282614699536602L;

    public DwdUserConversionUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
