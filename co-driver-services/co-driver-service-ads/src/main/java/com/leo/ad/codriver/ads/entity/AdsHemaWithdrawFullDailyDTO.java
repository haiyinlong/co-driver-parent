package com.leo.ad.codriver.ads.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * AdsHemaWithdrawFullDailyDTO
 *
 * @author HaiYinLong
 * @version 2024/05/13 16:33
 **/
@Data
public class AdsHemaWithdrawFullDailyDTO {
    private Integer dates;
    private String channelId;
    private String channelName;
    private String pkg;
    private String country;
    private String version;
    private Integer userType;
    private Long loginUserNum;
    private Long registerUserNum;
    private Long showWithdrawPageUserNum;
    private Long withdrawUserNum;
    private Long withdrawSuccessUserNum;
    private BigDecimal withdrawBankAmountFee;
    private BigDecimal withdrawAwsAmountFee;
    private BigDecimal ludoTotalAmount;
    private Long sendTotalNum;
    private Long ludoUserNum;
    private BigDecimal slotDeductionTotalAmount;
    private Long slotDeductionUserNum;
    private BigDecimal slotAdditionTotalAmount;
    private Long slotAdditionUserNum;
    private BigDecimal newUserRechargeGiftTotalAmount;
    private BigDecimal piggyBankBreakTotalAmount;
    private Long piggyBankBreakUserNum;
    private BigDecimal dailyCheckinTotalAmount;
    private Long dailyCheckinUserNum;
    private BigDecimal slotRankingTotalAmount;
    private Long slotRankingUserNum;
    private BigDecimal offerTaskRechargeFirstTotalAmount;
    private Long offerTaskRechargeFirstUserNum;
    private BigDecimal offerTaskRecharge200TotalAmount;
    private Long offerTaskRecharge200UserNum;
    private BigDecimal offerTaskRecharge300TotalAmount;
    private Long offerTaskRecharge300UserNum;
    private BigDecimal offerTaskRecharge500TotalAmount;
    private Long offerTaskRecharge500UserNum;
    private BigDecimal offerTaskRecharge1000TotalAmount;
    private Long offerTaskRecharge1000UserNum;
    private BigDecimal offerTaskRecharge2000TotalAmount;
    private Long offerTaskRecharge2000UserNum;
    private BigDecimal offerTaskRecharge3000TotalAmount;
    private Long offerTaskRecharge3000UserNum;
    private BigDecimal offerTaskRecharge5000TotalAmount;
    private Long offerTaskRecharge5000UserNum;
    private BigDecimal superDiceRollTotalAmount;
    private Long superDiceRollUserNum;
    private BigDecimal luckyScratchCardTotalAmount;
    private Long luckyScratchCardUserNum;
    private BigDecimal spinReward1000TotalAmount;
    private Long spinReward1000UserNum;
    private BigDecimal spin5ChestTotalAmount;
    private Long spin5ChestUserNum;
    private BigDecimal spin10ChestTotalAmount;
    private Long spin10ChestUserNum;
    private BigDecimal spin15ChestTotalAmount;
    private Long spin15ChestUserNum;
    private BigDecimal spin20ChestTotalAmount;
    private Long spin20ChestUserNum;

    private Long balance3000To5000UserNum;
    private Long balance1000To3000UserNum;
    private Long balance0To1000UserNum;
    private Long balanceGreaterThan5000UserNum;

}
