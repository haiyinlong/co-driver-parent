package com.leo.ad.codriver.dwd.dto;

import com.leo.ad.codriver.dwd.entity.DwdUserEvent;

import lombok.Data;

/**
 * DwdUserEventWithRegisterDateDTO
 *
 * @author HaiYinLong
 * @version 2025/02/18 10:58
 **/
@Data
public class DwdUserEventWithRegisterDateDTO extends DwdUserEvent {
    private Integer registerDates;

}
