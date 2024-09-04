package com.leo.ad.codriver.common.event.dws;

import java.io.Serial;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * 每日包广告数据汇总数据更新
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:54
 **/
@Getter
public class DwsDailyPackageAdUpdateEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 7077859589938085614L;
    private final Integer dates;

    public DwsDailyPackageAdUpdateEvent(Object source, Integer dates) {
        super(source);
        this.dates = dates;
    }
}
