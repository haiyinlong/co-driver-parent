package com.leo.ad.codriver.consumer.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DataChangeDTO
 *
 * @author HaiYinLong
 * @version 2024/06/04 15:29
 **/
@Data
public class DataChangeDTO implements Serializable {

    private Integer dates;
    /**
     * dwd-promote
     */
    private String changeType;
}
