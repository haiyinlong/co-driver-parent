package com.leo.ad.codriver.common.event.dws;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverEvent;

import lombok.Getter;

/**
 * 推广花费更新需要重新计算
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:46
 **/
@Getter
public class DwsDailyPackageAllVersionPromotionUpdateEvent extends CoDriverEvent {
    @Serial
    private static final long serialVersionUID = 424915631296013502L;

    public DwsDailyPackageAllVersionPromotionUpdateEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
