package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserEventReportUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:43
 **/
@Getter
public class DwdUserEventReportUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = -8456035978576622098L;

    public DwdUserEventReportUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
