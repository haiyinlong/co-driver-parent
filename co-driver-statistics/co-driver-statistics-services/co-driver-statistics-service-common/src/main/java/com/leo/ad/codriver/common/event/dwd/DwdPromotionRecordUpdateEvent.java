package com.leo.ad.codriver.common.event.dwd;

import java.io.Serial;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * DwdPromotionRecordUpdateEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdPromotionRecordUpdateEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -7552228133708206006L;
    private final Integer dates;

    public DwdPromotionRecordUpdateEvent(Object source, Integer dates) {
        super(source);
        this.dates = dates;
    }
}
