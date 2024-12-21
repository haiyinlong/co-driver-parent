package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserQpLtvUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserQpLtvUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 5181750859908109787L;

    public DwdUserQpLtvUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
