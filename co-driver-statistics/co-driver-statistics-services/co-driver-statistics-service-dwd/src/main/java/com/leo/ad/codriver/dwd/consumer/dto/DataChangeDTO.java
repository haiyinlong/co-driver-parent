package com.leo.ad.codriver.dwd.consumer.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * DataChangeDTO
 *
 * @author HaiYinLong
 * @version 2024/06/04 15:29
 **/
@Data
public class DataChangeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7811505754299700400L;
    private Integer dates;
    /**
     * dwd-promote
     */
    private String changeType;
}
