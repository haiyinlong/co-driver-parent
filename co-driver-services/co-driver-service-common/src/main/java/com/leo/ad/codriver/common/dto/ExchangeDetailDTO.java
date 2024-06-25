package com.leo.ad.codriver.common.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * ExchangeDTO
 *
 * @author HaiYinLong
 * @version 2024/06/24 14:33
 **/
@Data
public class ExchangeDetailDTO {
    private String currencyF;
    private String currencyT;
    private BigDecimal exchange;
}
