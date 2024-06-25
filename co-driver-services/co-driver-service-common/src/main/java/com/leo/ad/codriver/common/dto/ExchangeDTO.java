package com.leo.ad.codriver.common.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * ExchangeDTO
 *
 * @author HaiYinLong
 * @version 2024/06/24 14:33
 **/
@Data
public class ExchangeDTO {
    List<ExchangeDetailDTO> result;

    public BigDecimal getUsdToInrExchange() {
        if (CollectionUtils.isEmpty(result)) {
            return BigDecimal.ZERO;
        }

        return result.stream()
                .filter(e -> e.getCurrencyF().equals("USD") && e.getCurrencyT().equals("INR"))
                .findFirst()
                .get()
                .getExchange();
    }
}
