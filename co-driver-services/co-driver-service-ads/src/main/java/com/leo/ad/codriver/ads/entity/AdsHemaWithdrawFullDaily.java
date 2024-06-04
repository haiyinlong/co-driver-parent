package com.leo.ad.codriver.ads.entity;

/**
 * AdsHemaWithdrawFullDaily
 *
 * @author HaiYinLong
 * @version 2024/05/13 16:37
 **/

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.common.util.LongUtils;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

@Data
@TableName("ads_hema_withdraw_full_daily")
public class AdsHemaWithdrawFullDaily {

    private Long id;
    private Integer dates;
    private String channelId;
    private String channelName;
    private String pkg;
    private String country;
    private Integer userType;
    private String version;
    private Long dau;
    private Long dnu;
    private Long showWithdrawPageUserNum;
    private BigDecimal showWithdrawPageRate;
    private Long withdrawUserNum;
    private Long withdrawSuccessUserNum;
    private BigDecimal withdrawBankAmountFee;
    private BigDecimal withdrawAwsAmountFee;
    private BigDecimal sendTotalAmount;
    private BigDecimal ludoTotalAmount;
    private BigDecimal ludoAvgAmount;
    private BigDecimal slotAdditionTotalAmount;
    private BigDecimal slotAdditionAvgAmount;
    private BigDecimal slotDeductionTotalAmount;
    private BigDecimal slotDeductionAvgAmount;
    private BigDecimal newUserRechargeGiftTotalAmount;
    private BigDecimal piggyBankBreakTotalAmount;
    private BigDecimal piggyBankBreakAvgAmount;
    private BigDecimal dailyCheckinTotalAmount;
    private BigDecimal dailyCheckinAvgAmount;
    private BigDecimal slotRankingTotalAmount;
    private BigDecimal slotRankingAvgAmount;
    private BigDecimal offerTotalAmount;
    private BigDecimal offerTotalAvgAmount;
    private BigDecimal superDiceRollTotalAmount;
    private BigDecimal superDiceRollAvgAmount;
    private BigDecimal luckyScratchCardTotalAmount;
    private BigDecimal luckyScratchCardAvgAmount;
    private Long balance3000To5000UserNum;
    private Long balance1000To3000UserNum;
    private Long balance0To1000UserNum;
    private Long balanceGreaterThan5000UserNum;

    private BigDecimal spinReward1000TotalAmount;
    private BigDecimal spinReward1000AvgUserAmount;
    private BigDecimal spinChestTotalAmount;
    private BigDecimal spinChestAvgUserAmount;

    public AdsHemaWithdrawFullDaily() {
        this.dau = 0L;
        this.dnu = 0L;
        this.showWithdrawPageUserNum = 0L;
        this.showWithdrawPageRate = BigDecimal.ZERO;
        this.withdrawUserNum = 0L;
        this.withdrawSuccessUserNum = 0L;
        this.withdrawBankAmountFee = BigDecimal.ZERO;
        this.withdrawAwsAmountFee = BigDecimal.ZERO;
        this.sendTotalAmount = BigDecimal.ZERO;
        this.ludoTotalAmount = BigDecimal.ZERO;
        this.ludoAvgAmount = BigDecimal.ZERO;

        this.slotAdditionTotalAmount = BigDecimal.ZERO;
        this.slotAdditionAvgAmount = BigDecimal.ZERO;
        this.slotDeductionTotalAmount = BigDecimal.ZERO;
        this.slotDeductionAvgAmount = BigDecimal.ZERO;

        this.newUserRechargeGiftTotalAmount = BigDecimal.ZERO;
        this.piggyBankBreakTotalAmount = BigDecimal.ZERO;
        this.piggyBankBreakAvgAmount = BigDecimal.ZERO;
        this.dailyCheckinTotalAmount = BigDecimal.ZERO;
        this.dailyCheckinAvgAmount = BigDecimal.ZERO;
        this.slotRankingTotalAmount = BigDecimal.ZERO;
        this.slotRankingAvgAmount = BigDecimal.ZERO;
        this.offerTotalAmount = BigDecimal.ZERO;
        this.offerTotalAvgAmount = BigDecimal.ZERO;
        this.superDiceRollTotalAmount = BigDecimal.ZERO;
        this.superDiceRollAvgAmount = BigDecimal.ZERO;
        this.luckyScratchCardTotalAmount = BigDecimal.ZERO;
        this.luckyScratchCardAvgAmount = BigDecimal.ZERO;
        this.balance3000To5000UserNum = 0L;
        this.balance1000To3000UserNum = 0L;
        this.balance0To1000UserNum = 0L;
        this.balanceGreaterThan5000UserNum = 0L;

        this.spinReward1000TotalAmount = BigDecimal.ZERO;
        this.spinReward1000AvgUserAmount = BigDecimal.ZERO;
        this.spinChestTotalAmount = BigDecimal.ZERO;
        this.spinChestAvgUserAmount = BigDecimal.ZERO;
    }

    public static AdsHemaWithdrawFullDaily of(Integer dates, String channelId, String channelName, String pkg,
                                              String country, String version, Integer userType, Long loginUserNum, Long registerUserNum) {
        AdsHemaWithdrawFullDaily adsHemaWithdrawFullDaily = new AdsHemaWithdrawFullDaily();
        adsHemaWithdrawFullDaily.setDates(dates);
        adsHemaWithdrawFullDaily.setChannelId(channelId);
        adsHemaWithdrawFullDaily.setChannelName(channelName);
        adsHemaWithdrawFullDaily.setPkg(pkg);
        adsHemaWithdrawFullDaily.setCountry(country);
        adsHemaWithdrawFullDaily.setVersion(version);
        adsHemaWithdrawFullDaily.setUserType(userType);
        adsHemaWithdrawFullDaily.setDau(loginUserNum);
        adsHemaWithdrawFullDaily.setDnu(registerUserNum);
        return adsHemaWithdrawFullDaily;
    }

    public void calculateWithdrawPage(Long showWithdrawPageUserNum) {
        if (!ObjectUtils.isEmpty(showWithdrawPageUserNum) && showWithdrawPageUserNum > 0) {
            this.showWithdrawPageUserNum = showWithdrawPageUserNum;
            this.showWithdrawPageRate =
                    BigDecimalUtils.dividePercentage(this.showWithdrawPageUserNum, getTotalDauOrDnuByUserType());
        }
    }

    private Long getTotalDauOrDnuByUserType() {
        if (userType == 0) {
            return this.dau;
        }
        return this.dnu;
    }

    public void calculateWithdrawAmount(Long withdrawUserNum, Long withdrawSuccessUserNum,
                                        BigDecimal withdrawBankAmountFee, BigDecimal withdrawAwsAmountFee) {
        if (!ObjectUtils.isEmpty(withdrawUserNum) && withdrawUserNum > 0) {
            this.withdrawUserNum = withdrawUserNum;
        }
        if (!ObjectUtils.isEmpty(withdrawSuccessUserNum) && withdrawSuccessUserNum > 0) {
            this.withdrawSuccessUserNum = withdrawSuccessUserNum;
        }
        if (!ObjectUtils.isEmpty(withdrawBankAmountFee) && withdrawBankAmountFee.compareTo(BigDecimal.ZERO) > 0) {
            this.withdrawBankAmountFee = withdrawBankAmountFee;
        }
        if (!ObjectUtils.isEmpty(withdrawAwsAmountFee) && withdrawAwsAmountFee.compareTo(BigDecimal.ZERO) > 0) {
            this.withdrawAwsAmountFee = withdrawAwsAmountFee;
        }
    }

    public void calculateSendAmount(BigDecimal ludoTotalAmount, Long ludoUserNum, BigDecimal slotAdditionTotalAmount,
                                    Long slotAdditionUserNum, BigDecimal slotDeductionTotalAmount, Long slotDeductionUserNum,
                                    BigDecimal newUserRechargeGiftTotalAmount, BigDecimal piggyBankBreakTotalAmount, Long piggyBankBreakUserNum,
                                    BigDecimal dailyCheckinTotalAmount, Long dailyCheckinUserNum, BigDecimal slotRankingTotalAmount,
                                    Long slotRankingUserNum, BigDecimal offerTaskRechargeFirstTotalAmount, Long offerTaskRechargeFirstUserNum,
                                    BigDecimal offerTaskRecharge200TotalAmount, Long offerTaskRecharge200UserNum,
                                    BigDecimal offerTaskRecharge300TotalAmount, Long offerTaskRecharge300UserNum,
                                    BigDecimal offerTaskRecharge500TotalAmount, Long offerTaskRecharge500UserNum,
                                    BigDecimal offerTaskRecharge1000TotalAmount, Long offerTaskRecharge1000UserNum,
                                    BigDecimal offerTaskRecharge2000TotalAmount, Long offerTaskRecharge2000UserNum,
                                    BigDecimal offerTaskRecharge3000TotalAmount, Long offerTaskRecharge3000UserNum,
                                    BigDecimal offerTaskRecharge5000TotalAmount, Long offerTaskRecharge5000UserNum,
                                    BigDecimal superDiceRollTotalAmount, Long superDiceRollUserNum, BigDecimal luckyScratchCardTotalAmount,
                                    Long luckyScratchCardUserNum) {

        if (!ObjectUtils.isEmpty(ludoTotalAmount) && ludoTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.ludoTotalAmount = ludoTotalAmount;
            this.ludoAvgAmount = BigDecimalUtils.divide(this.ludoTotalAmount, ludoUserNum);
        }

        if (!ObjectUtils.isEmpty(slotAdditionTotalAmount) && slotAdditionTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.slotAdditionTotalAmount = slotAdditionTotalAmount;
        }

        if (!ObjectUtils.isEmpty(slotAdditionUserNum) && slotAdditionUserNum > 0) {
            this.slotAdditionAvgAmount = BigDecimalUtils.divide(this.slotAdditionTotalAmount, slotAdditionUserNum);
        }

        if (!ObjectUtils.isEmpty(slotDeductionTotalAmount)) {
            this.slotDeductionTotalAmount = slotDeductionTotalAmount;
        }

        if (!ObjectUtils.isEmpty(slotDeductionUserNum) && slotDeductionUserNum > 0) {
            this.slotDeductionAvgAmount = BigDecimalUtils.divide(this.slotDeductionTotalAmount, slotDeductionUserNum);
        }

        if (!ObjectUtils.isEmpty(newUserRechargeGiftTotalAmount)
                && newUserRechargeGiftTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.newUserRechargeGiftTotalAmount = newUserRechargeGiftTotalAmount;
        }

        if (!ObjectUtils.isEmpty(piggyBankBreakTotalAmount)
                && piggyBankBreakTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.piggyBankBreakTotalAmount = piggyBankBreakTotalAmount;
            this.piggyBankBreakAvgAmount =
                    BigDecimalUtils.divide(this.piggyBankBreakTotalAmount, piggyBankBreakUserNum);
        }

        if (!ObjectUtils.isEmpty(dailyCheckinTotalAmount) && dailyCheckinTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.dailyCheckinTotalAmount = dailyCheckinTotalAmount;
            this.dailyCheckinAvgAmount = BigDecimalUtils.divide(this.dailyCheckinTotalAmount, dailyCheckinUserNum);
        }

        if (!ObjectUtils.isEmpty(slotRankingTotalAmount) && slotRankingTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.slotRankingTotalAmount = slotRankingTotalAmount;
            this.slotRankingAvgAmount = BigDecimalUtils.divide(this.slotRankingTotalAmount, slotRankingUserNum);
        }
        // 汇总积分墙 金额和用户数
        this.offerTotalAmount =
                calculateOfferTotalAmount(offerTaskRechargeFirstTotalAmount, offerTaskRecharge200TotalAmount,
                        offerTaskRecharge300TotalAmount, offerTaskRecharge500TotalAmount, offerTaskRecharge1000TotalAmount,
                        offerTaskRecharge2000TotalAmount, offerTaskRecharge3000TotalAmount, offerTaskRecharge5000TotalAmount);
        Long offerTotalUserNum = calculateOfferTotalUserNum(offerTaskRechargeFirstUserNum, offerTaskRecharge200UserNum,
                offerTaskRecharge300UserNum, offerTaskRecharge500UserNum, offerTaskRecharge1000UserNum,
                offerTaskRecharge2000UserNum, offerTaskRecharge3000UserNum, offerTaskRecharge5000UserNum);
        this.offerTotalAvgAmount = calculateOfferAvgAmount(this.offerTotalAmount, offerTotalUserNum);

        if (!ObjectUtils.isEmpty(superDiceRollTotalAmount) && superDiceRollTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.superDiceRollTotalAmount = superDiceRollTotalAmount;
            this.superDiceRollAvgAmount = BigDecimalUtils.divide(this.superDiceRollTotalAmount, superDiceRollUserNum);
        }

        if (!ObjectUtils.isEmpty(luckyScratchCardTotalAmount)
                && luckyScratchCardTotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.luckyScratchCardTotalAmount = luckyScratchCardTotalAmount;
            this.luckyScratchCardAvgAmount =
                    BigDecimalUtils.divide(this.luckyScratchCardTotalAmount, luckyScratchCardUserNum);
        }
        // 计算发送总金额 sendTotalAmount
        this.sendTotalAmount = calculateTotalSendAmount();
    }

    /**
     * 合计总发送现金
     *
     * @return
     */
    private BigDecimal calculateTotalSendAmount() {
        return BigDecimalUtils.add(this.ludoTotalAmount, this.slotAdditionTotalAmount,
                this.newUserRechargeGiftTotalAmount, this.piggyBankBreakTotalAmount, this.dailyCheckinTotalAmount,
                this.slotRankingTotalAmount, this.offerTotalAmount, this.superDiceRollTotalAmount,
                this.luckyScratchCardTotalAmount);
    }

    private BigDecimal calculateOfferAvgAmount(BigDecimal offerTotalAvgAmount, Long offerTotalUserNum) {
        return BigDecimalUtils.divide(offerTotalAvgAmount, offerTotalUserNum);
    }

    private Long calculateOfferTotalUserNum(Long offerTaskRechargeFirstUserNum, Long offerTaskRecharge200UserNum,
                                            Long offerTaskRecharge300UserNum, Long offerTaskRecharge500UserNum, Long offerTaskRecharge1000UserNum,
                                            Long offerTaskRecharge2000UserNum, Long offerTaskRecharge3000UserNum, Long offerTaskRecharge5000UserNum) {
        return LongUtils.add(offerTaskRechargeFirstUserNum, offerTaskRecharge200UserNum, offerTaskRecharge300UserNum,
                offerTaskRecharge500UserNum, offerTaskRecharge1000UserNum, offerTaskRecharge2000UserNum,
                offerTaskRecharge3000UserNum, offerTaskRecharge5000UserNum);
    }

    private BigDecimal calculateOfferTotalAmount(BigDecimal offerTaskRechargeFirstTotalAmount,
                                                 BigDecimal offerTaskRecharge200TotalAmount, BigDecimal offerTaskRecharge300TotalAmount,
                                                 BigDecimal offerTaskRecharge500TotalAmount, BigDecimal offerTaskRecharge1000TotalAmount,
                                                 BigDecimal offerTaskRecharge2000TotalAmount, BigDecimal offerTaskRecharge3000TotalAmount,
                                                 BigDecimal offerTaskRecharge5000TotalAmount) {
        return BigDecimalUtils.add(offerTaskRechargeFirstTotalAmount, offerTaskRecharge200TotalAmount,
                offerTaskRecharge300TotalAmount, offerTaskRecharge500TotalAmount, offerTaskRecharge1000TotalAmount,
                offerTaskRecharge2000TotalAmount, offerTaskRecharge3000TotalAmount, offerTaskRecharge5000TotalAmount);
    }

    public void calculateBalance(Long balance0To1000UserNum, Long balance1000To3000UserNum,
                                 Long balance3000To5000UserNum, Long balanceGreaterThan5000UserNum) {
        if (!ObjectUtils.isEmpty(balance0To1000UserNum) && balance0To1000UserNum > 0) {
            this.balance0To1000UserNum = balance0To1000UserNum;
        }
        if (!ObjectUtils.isEmpty(balance1000To3000UserNum) && balance1000To3000UserNum > 0) {
            this.balance1000To3000UserNum = balance1000To3000UserNum;
        }
        if (!ObjectUtils.isEmpty(balance3000To5000UserNum) && balance3000To5000UserNum > 0) {
            this.balance3000To5000UserNum = balance3000To5000UserNum;
        }
        if (!ObjectUtils.isEmpty(balanceGreaterThan5000UserNum) && balanceGreaterThan5000UserNum > 0) {
            this.balanceGreaterThan5000UserNum = balanceGreaterThan5000UserNum;
        }
    }

    public void calculateSpinReward(BigDecimal spinReward1000TotalAmount, Long spinReward1000UserNum) {
        if (!ObjectUtils.isEmpty(spinReward1000TotalAmount)
                && spinReward1000TotalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.spinReward1000TotalAmount = spinReward1000TotalAmount;
        }
        if (!ObjectUtils.isEmpty(spinReward1000UserNum) && spinReward1000UserNum > 0) {
            this.spinReward1000AvgUserAmount =
                    BigDecimalUtils.divide(this.spinReward1000TotalAmount, spinReward1000UserNum);
        }
    }

    public void calculateSpinChest(BigDecimal spin5ChestTotalAmount, Long spin5ChestUserNum,
                                   BigDecimal spin10ChestTotalAmount, Long spin10ChestUserNum, BigDecimal spin15ChestTotalAmount,
                                   Long spin15ChestUserNum, BigDecimal spin20ChestTotalAmount, Long spin20ChestUserNum) {
        BigDecimal totalAmount = BigDecimalUtils.add(spin5ChestTotalAmount, spin10ChestTotalAmount,
                spin15ChestTotalAmount, spin20ChestTotalAmount);

        if (!ObjectUtils.isEmpty(totalAmount) && totalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.spinChestTotalAmount = totalAmount;
        }

        long totalUserNum =
                LongUtils.add(spin5ChestUserNum, spin10ChestUserNum, spin15ChestUserNum, spin20ChestUserNum);
        if (!ObjectUtils.isEmpty(totalUserNum) && totalUserNum > 0) {
            this.spinChestAvgUserAmount = BigDecimalUtils.divide(this.spinChestTotalAmount, totalUserNum);
        }

    }
}
