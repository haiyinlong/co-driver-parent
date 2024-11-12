package com.leo.ad.codriver.common.event;

import java.io.Serial;

import com.leo.ad.codriver.common.dao.enetity.DwTaskRecord;

import lombok.Getter;

/**
 * DwResatrtTaskEvent
 *
 * @author HaiYinLong
 * @version 2024/11/11 16:33
 **/
@Getter
public class DwRestartTaskEvent extends CoDriverDwEvent {
    @Serial
    private static final long serialVersionUID = -4178429027970711674L;
    private DwTaskRecord task;

    public DwRestartTaskEvent(Object source, Integer dates, DwTaskRecord task) {
        super(source, dates);
        this.task = task;
    }
}
