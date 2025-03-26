package com.leo.ad.codriver.common.task.event;

import java.io.Serial;

import org.springframework.context.ApplicationEvent;

import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;

import lombok.Getter;

/**
 * DwTaskRecordExecuteEvent
 *
 * @author HaiYinLong
 * @version 2025/03/25 15:22
 **/
@Getter
public class DwTaskRecordExecuteEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 3209652368935287169L;
    DwTaskRecord dbTaskRecord;

    public DwTaskRecordExecuteEvent(Object source, DwTaskRecord dbTaskRecord) {
        super(source);
        this.dbTaskRecord = dbTaskRecord;
    }
}
