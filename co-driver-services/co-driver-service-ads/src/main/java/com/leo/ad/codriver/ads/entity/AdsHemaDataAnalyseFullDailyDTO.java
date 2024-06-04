package com.leo.ad.codriver.ads.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * AddHemaDataAnalyseFullDailyDTO
 *
 * @author HaiYinLong
 * @version 2024/05/09 17:31
 **/
@Data
public class AdsHemaDataAnalyseFullDailyDTO {
    private Integer dates;
    private String channelId;
    private String channelName;
    private String pkg;
    private String country;
    private String version;
    private Integer userType;
    private Long loginUserNum;
    private Long registerPkgUserNum;
    private Long registerUserNum;
    private BigDecimal pkgPromotionTotalCost;
    private Long matchPageShowUserNum;
    private Long gameStartNum;
    private BigDecimal gameFinishTotalTime;
    private BigDecimal promotionTotalCoin;
    private BigDecimal promotionTotalLifeGain;
    private Long gameEnterOnlyUserNum;
    private BigDecimal gameSlotsTotalBet;
    private Long gameSlotsBetNum;
    private Long gameSlotsBetUserNum;
    private Long showOfferPageUserNum;
    private Long clickTaskUserNum;
    private Long webLoadingUserNum;
    private Long downloadPagShowUserNum;
    private Long downloadPagClickUserNum;
    private Long showWithdrawPageUserNum;
    private Long goldGameEnterOnlyUserNum;
    private BigDecimal goldGameSlotsSpinTotalBet;
    private Long goldGameSlotsSpinNum;
    private BigDecimal slotDeductionTotalAmount;
    private BigDecimal slotAdditionTotalAmount;
    private BigDecimal ludoTotalAmount;
    private BigDecimal withdrawTotalAmount;
    private Long conversionUserNum;
    private Long paymentUserNum;
    private BigDecimal paymentAmount;
    private Long retention1;
    private Long retention7;
}
