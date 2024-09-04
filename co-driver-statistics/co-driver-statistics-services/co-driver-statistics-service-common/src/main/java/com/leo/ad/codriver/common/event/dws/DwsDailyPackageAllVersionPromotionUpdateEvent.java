package com.leo.ad.codriver.common.event.dws;

import java.io.Serial;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * 推广花费更新需要重新计算
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:46
 **/
@Getter
public class DwsDailyPackageAllVersionPromotionUpdateEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -1025171762250620127L;
    private final Integer dates;

    public DwsDailyPackageAllVersionPromotionUpdateEvent(Object source, Integer dates) {
        super(source);
        this.dates = dates;
    }
}
