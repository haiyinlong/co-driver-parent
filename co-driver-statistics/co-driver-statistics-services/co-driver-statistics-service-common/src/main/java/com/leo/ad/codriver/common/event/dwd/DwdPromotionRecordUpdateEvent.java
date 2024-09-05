package com.leo.ad.codriver.common.event.dwd;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverEvent;

import lombok.Getter;

/**
 * DwdPromotionRecordUpdateEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdPromotionRecordUpdateEvent extends CoDriverEvent {
    @Serial
    private static final long serialVersionUID = 4158133776654572026L;

    public DwdPromotionRecordUpdateEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
