package com.leo.ad.codriver.dwd.dto;

import com.leo.ad.codriver.dwd.entity.DwdUserEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DwdUserEventWithRegisterDateDTO
 *
 * @author HaiYinLong
 * @version 2025/02/18 10:58
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class DwdUserEventWithRegisterDateDTO extends DwdUserEvent {
    private Integer registerDate;

}
