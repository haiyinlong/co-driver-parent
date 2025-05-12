package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserGameFragmentGoodsInstallDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserGameFragmentGoodsInstallDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 4686575402473653110L;

    public DwdUserGameFragmentGoodsInstallDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
