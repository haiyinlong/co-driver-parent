package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_promotion")
public class DwsDailyPackagePromotion implements BaseEntity {

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
