package com.leo.ad.codriver.common.event.dwd;

import java.io.Serial;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * 用户广告数据完成事件
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:43
 **/
@Getter
public class DwdUserAdRecordUpdateEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 4570689386063524480L;
    private final Integer dates;

    public DwdUserAdRecordUpdateEvent(Object source, Integer dates) {
        super(source);
        this.dates = dates;
    }

}
