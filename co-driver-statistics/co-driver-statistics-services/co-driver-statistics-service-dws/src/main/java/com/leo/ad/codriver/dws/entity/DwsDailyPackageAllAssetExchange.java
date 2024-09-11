package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_asset_exchange")
public class DwsDailyPackageAllAssetExchange implements BaseEntity {

    private Long id;
    private Integer dates;
    private String pkg;
    private String version;
    private Long userType;
    private Long totalUserNum;
    private Long totalExchangeCount;
    private Long exchangeCashUserNum;
    private Long exchangeCashCount;
    private BigDecimal exchangeCashTotalAmount;
    private BigDecimal exchangeCashToTotalChip;
    private BigDecimal exchangeCashRate;
    private Long exchangeChipUserNum;
    private Long exchangeChipCount;
    private BigDecimal exchangeChipTotalAmount;
    private BigDecimal exchangeChipToTotalCash;
    private BigDecimal exchangeChipRate;
    private Long exchangeProtectChipUserNum;
    private Long exchangeProtectChipCount;
    private BigDecimal exchangeProtectChipTotalAmount;
    private BigDecimal exchangeProtectChipToTotalChip;
    private BigDecimal exchangeProtectChipRate;
    private Date createTime;

    public void calculateRateAndInit() {
        this.createTime = new Date();
        this.exchangeCashRate = calculateRateAndInit(exchangeCashCount, totalExchangeCount);
        this.exchangeChipRate = calculateRateAndInit(exchangeChipCount, totalExchangeCount);
        this.exchangeProtectChipRate = calculateRateAndInit(exchangeProtectChipCount, totalExchangeCount);
    }

    private BigDecimal calculateRateAndInit(Long count, Long totalCount) {
        if (Objects.equals(totalCount, 0L)) {
            return BigDecimal.ZERO;
        }
        if (Objects.equals(count, 0L)) {
            return BigDecimal.ZERO;
        }
        return BigDecimalUtils.divide(count, totalCount).setScale(4, RoundingMode.HALF_UP);
    }
}
