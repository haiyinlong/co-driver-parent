package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("dws_daily_package_promotion")
public class DwsDailyPackagePromotion {

    private Long id;
    private Long dates;
    private Long projectId;
    private Long productId;
    private String productName;
    private Long channelId;
    private String channelName;
    private String pkg;
    private BigDecimal totalCost;
    private BigDecimal exchangeRate;
    private BigDecimal changeTotalCost;

}
