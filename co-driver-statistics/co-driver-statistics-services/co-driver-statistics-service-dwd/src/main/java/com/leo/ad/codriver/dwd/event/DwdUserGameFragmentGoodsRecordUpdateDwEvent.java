package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * DwdUserGameFragmentGoodsRecordUpdateDwEvent
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:43
 **/
@Getter
public class DwdUserGameFragmentGoodsRecordUpdateDwEvent extends CoDriverDwEvent {


    @Serial
    private static final long serialVersionUID = 6451287564355457367L;

    public DwdUserGameFragmentGoodsRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
