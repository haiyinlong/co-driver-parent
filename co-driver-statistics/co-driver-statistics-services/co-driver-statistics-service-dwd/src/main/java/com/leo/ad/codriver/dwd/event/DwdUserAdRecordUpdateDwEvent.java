package com.leo.ad.codriver.dwd.event;

import java.io.Serial;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.Getter;

/**
 * 用户广告数据完成事件
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:43
 **/
@Getter
public class DwdUserAdRecordUpdateDwEvent extends CoDriverDwEvent {

    @Serial
    private static final long serialVersionUID = 5030057341471183695L;

    public DwdUserAdRecordUpdateDwEvent(Object source, Integer dates) {
        super(source, dates);
    }
}
