package com.leo.ad.codriver.common.event;

import java.io.Serial;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * CoDriverEvent
 *
 * @author HaiYinLong
 * @version 2024/09/05 09:50
 **/
@Getter
public class CoDriverEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -8274554445985737569L;
    private final Integer dates;

    public CoDriverEvent(Object source, Integer dates) {
        super(source);
        this.dates = dates;
    }
}
