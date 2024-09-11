package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

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
        this.exchangeCashRate =
            BigDecimalUtils.divide(exchangeCashCount, totalExchangeCount).setScale(4, RoundingMode.HALF_UP);
        this.exchangeChipRate =
            BigDecimalUtils.divide(exchangeChipCount, totalExchangeCount).setScale(4, RoundingMode.HALF_UP);
        this.exchangeProtectChipRate =
            BigDecimalUtils.divide(exchangeProtectChipCount, totalExchangeCount).setScale(4, RoundingMode.HALF_UP);
    }

}
