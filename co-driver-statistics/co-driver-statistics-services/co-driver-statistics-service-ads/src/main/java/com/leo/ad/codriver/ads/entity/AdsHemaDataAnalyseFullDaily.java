package com.leo.ad.codriver.ads.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@TableName("ads_hema_data_analyse_full_daily")
public class AdsHemaDataAnalyseFullDaily {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String channelId;
    private String channelName;
    private String pkg;
    private String country;
    private String version;
    private Integer userType;
    private Long dau;
    private Long dnu;
    private Long aPlayDau;
    private BigDecimal aPlayAvgNum;
    private BigDecimal aPlayAvgTime;
    private BigDecimal totalGoldCoin;
    private BigDecimal ludoTotalAmount;
    private BigDecimal aPlayAvgPower;
    private BigDecimal aPlayPermeability;
    private Long slotsDau;
    private Long slotsGoldGameEnterOnlyUserNum;
    private BigDecimal slotDeductionTotalAmount;
    private BigDecimal slotAdditionTotalAmount;
    private BigDecimal slotsGoldGameAvgUserBetNum;
    private BigDecimal slotsGoldGameAvgUserBetAmount;
    private Long slotsSpinUserNum;
    private BigDecimal avgUserBetNum;
    private BigDecimal avgUserBetAmount;
    private BigDecimal slotsPermeability;
    private Long scoreDau;
    private Long scoreParticipationNum;
    private BigDecimal scoreParticipation;
    private BigDecimal promotionCost;
    private BigDecimal withdrawTotalAmount;
    private BigDecimal promotionAvgCost;
    private Long webLoadingUserNum;
    private Long landingPageShowNum;
    private Long landingPageDownloadNum;
    private BigDecimal landingPageShowRate;
    private Long offerUserNum;
    private BigDecimal promotionAvgOfferRate;
    private BigDecimal promotionAvgOfferWithdrawRate;
    private Long promotionPayUserNum;
    private BigDecimal promotionAvgPayRate;
    private BigDecimal promotionAvgPayWithdrawRate;
    private BigDecimal promotionPayIncome;
    private BigDecimal promotionRoi;
    private Long withdrawPageUserNum;
    private BigDecimal withdrawPageRate;
    private BigDecimal d1NewUserRate;
    private BigDecimal d7NewUserRate;

    public AdsHemaDataAnalyseFullDaily() {
        this.dau = 0L;
        this.dnu = 0L;
        this.aPlayDau = 0L;
        this.aPlayAvgNum = BigDecimal.ZERO;
        this.aPlayAvgTime = BigDecimal.ZERO;
        this.totalGoldCoin = BigDecimal.ZERO;
        this.ludoTotalAmount = BigDecimal.ZERO;
        this.aPlayAvgPower = BigDecimal.ZERO;
        this.aPlayPermeability = BigDecimal.ZERO;
        this.slotsDau = 0L;
        this.slotsGoldGameEnterOnlyUserNum = 0L;
        this.slotDeductionTotalAmount = BigDecimal.ZERO;
        this.slotAdditionTotalAmount = BigDecimal.ZERO;
        this.slotsGoldGameAvgUserBetNum = BigDecimal.ZERO;
        this.slotsGoldGameAvgUserBetAmount = BigDecimal.ZERO;
        this.slotsSpinUserNum = 0L;
        this.avgUserBetNum = BigDecimal.ZERO;
        this.avgUserBetAmount = BigDecimal.ZERO;
        this.slotsPermeability = BigDecimal.ZERO;
        this.scoreDau = 0L;
        this.scoreParticipationNum = 0L;
        this.scoreParticipation = BigDecimal.ZERO;
        this.promotionCost = BigDecimal.ZERO;
        this.withdrawTotalAmount = BigDecimal.ZERO;
        this.promotionAvgCost = BigDecimal.ZERO;
        this.webLoadingUserNum = 0L;
        this.landingPageShowNum = 0L;
        this.landingPageDownloadNum = 0L;
        this.landingPageShowRate = BigDecimal.ZERO;
        this.offerUserNum = 0L;
        this.promotionAvgOfferRate = BigDecimal.ZERO;
        this.promotionAvgOfferWithdrawRate = BigDecimal.ZERO;
        this.promotionPayUserNum = 0L;
        this.promotionAvgPayRate = BigDecimal.ZERO;
        this.promotionAvgPayWithdrawRate = BigDecimal.ZERO;
        this.promotionPayIncome = BigDecimal.ZERO;
        this.promotionRoi = BigDecimal.ZERO;
        this.withdrawPageUserNum = 0L;
        this.withdrawPageRate = BigDecimal.ZERO;
        this.d1NewUserRate = BigDecimal.ZERO;
        this.d7NewUserRate = BigDecimal.ZERO;
    }

    public static AdsHemaDataAnalyseFullDaily of(Integer dates, String channelId, String channelName, String pkg,
                                                 String country, String version, Integer userType, Long loginUserNum, Long registerUserNum) {
        AdsHemaDataAnalyseFullDaily adsHemaDataAnalyseFullDaily = new AdsHemaDataAnalyseFullDaily();
        adsHemaDataAnalyseFullDaily.setDates(dates);
        adsHemaDataAnalyseFullDaily.setChannelId(channelId);
        adsHemaDataAnalyseFullDaily.setChannelName(channelName);
        adsHemaDataAnalyseFullDaily.setPkg(pkg);
        adsHemaDataAnalyseFullDaily.setCountry(country);
        adsHemaDataAnalyseFullDaily.setVersion(version);
        adsHemaDataAnalyseFullDaily.setUserType(userType);
        if (!ObjectUtils.isEmpty(loginUserNum)) {
            adsHemaDataAnalyseFullDaily.setDau(loginUserNum);
        }
        if (!ObjectUtils.isEmpty(registerUserNum)) {
            adsHemaDataAnalyseFullDaily.setDnu(registerUserNum);
        }
        return adsHemaDataAnalyseFullDaily;
    }

    /**
     * 计算 A玩法DAU A玩法平均局数 A玩法人均游玩时长 总发放金币数 总发放现金数ludo A玩法人均消耗体力 A玩法渗透率
     *
     * @param matchPageShowUserNum
     * @param gameStartNum
     * @param gameFinishTotalTime
     * @param promotionTotalCoin
     * @param promotionTotalLifeGain
     * @param ludoTotalAmount
     */
    public void calculateLudo(Long matchPageShowUserNum, Long gameStartNum, BigDecimal gameFinishTotalTime,
                              BigDecimal promotionTotalCoin, BigDecimal promotionTotalLifeGain, BigDecimal ludoTotalAmount) {
        if (!ObjectUtils.isEmpty(matchPageShowUserNum) && matchPageShowUserNum > 0) {
            this.aPlayDau = matchPageShowUserNum;
        }
        if (!ObjectUtils.isEmpty(gameStartNum) && gameStartNum > 0) {
            this.aPlayAvgNum = BigDecimalUtils.divide(gameStartNum, this.aPlayDau);
        }
        if (!ObjectUtils.isEmpty(gameFinishTotalTime) && gameFinishTotalTime.compareTo(BigDecimal.ZERO) > 0) {
            this.aPlayAvgTime = BigDecimalUtils.divide(gameFinishTotalTime, new BigDecimal(this.aPlayDau));
        }

        if (!ObjectUtils.isEmpty(promotionTotalCoin)) {
            this.totalGoldCoin = promotionTotalCoin;
        }

        if (!ObjectUtils.isEmpty(promotionTotalLifeGain)) {
            this.aPlayAvgPower = BigDecimalUtils.divide(promotionTotalLifeGain, this.aPlayDau);
        }

        this.aPlayPermeability = BigDecimalUtils.dividePercentage(this.aPlayDau, getTotalDauOrDnuByUserType());

        if (!ObjectUtils.isEmpty(ludoTotalAmount)) {
            this.ludoTotalAmount = ludoTotalAmount;
        }
    }

    /**
     * 计算SlotsDAU 总发放现金数slot slots摇奖用户 人均投注次数 人均投注金额 Slots渗透率
     *
     * @param gameEnterOnlyUserNum
     * @param gameSlotsBetUserNum
     * @param gameSlotsTotalBet
     * @param goldGameEnterOnlyUserNum
     * @param goldGameSlotsSpinTotalBet
     * @param goldGameSlotsSpinNum
     * @param slotDeductionTotalAmount
     * @param slotAdditionTotalAmount
     */
    public void calculateSlot(Long gameEnterOnlyUserNum, Long gameSlotsBetUserNum, Long gameSlotsBetNum,
                              BigDecimal gameSlotsTotalBet, Long goldGameEnterOnlyUserNum, BigDecimal goldGameSlotsSpinTotalBet,
                              Long goldGameSlotsSpinNum, BigDecimal slotDeductionTotalAmount, BigDecimal slotAdditionTotalAmount) {
        if (!ObjectUtils.isEmpty(gameEnterOnlyUserNum) && gameEnterOnlyUserNum > 0) {
            this.slotsDau = gameEnterOnlyUserNum;
        }
        if (!ObjectUtils.isEmpty(gameSlotsBetUserNum) && gameSlotsBetUserNum > 0) {
            this.slotsSpinUserNum = gameSlotsBetUserNum;
        }
        if (!ObjectUtils.isEmpty(gameSlotsBetNum)) {
            this.avgUserBetNum = BigDecimalUtils.divide(gameSlotsBetNum, this.slotsDau);
        }
        if (!ObjectUtils.isEmpty(gameSlotsTotalBet)) {
            this.avgUserBetAmount =
                    BigDecimalUtils.reserved2(BigDecimalUtils.divide(gameSlotsTotalBet, new BigDecimal(this.slotsDau)));
        }
        this.slotsPermeability = BigDecimalUtils.dividePercentage(this.slotsDau, getTotalDauOrDnuByUserType());

        if (!ObjectUtils.isEmpty(goldGameEnterOnlyUserNum) && goldGameEnterOnlyUserNum > 0) {
            this.slotsGoldGameEnterOnlyUserNum = goldGameEnterOnlyUserNum;
        }
        if (!ObjectUtils.isEmpty(slotDeductionTotalAmount)) {
            this.slotDeductionTotalAmount = slotDeductionTotalAmount;
        }
        if (!ObjectUtils.isEmpty(slotAdditionTotalAmount)) {
            this.slotAdditionTotalAmount = slotAdditionTotalAmount;
        }
        if (!ObjectUtils.isEmpty(goldGameSlotsSpinNum)) {
            this.slotsGoldGameAvgUserBetNum =
                    BigDecimalUtils.divide(goldGameSlotsSpinNum, this.slotsGoldGameEnterOnlyUserNum);
        }
        if (!ObjectUtils.isEmpty(goldGameSlotsSpinTotalBet)) {
            this.slotsGoldGameAvgUserBetAmount = BigDecimalUtils
                    .reserved2(BigDecimalUtils.divide(goldGameSlotsSpinTotalBet, new BigDecimal(this.slotsDau)));
        }
    }

    /**
     * 积分墙DAU 积分墙参与数 积分墙渗透率
     *
     * @param showOfferPageUserNum
     * @param clickTaskUserNum
     */
    public void calculateScore(Long showOfferPageUserNum, Long clickTaskUserNum) {
        if (!ObjectUtils.isEmpty(showOfferPageUserNum) && showOfferPageUserNum > 0) {
            this.scoreDau = showOfferPageUserNum;
        }
        if (!ObjectUtils.isEmpty(clickTaskUserNum) && clickTaskUserNum > 0) {
            this.scoreParticipationNum = clickTaskUserNum;
        }
        this.scoreParticipation = BigDecimalUtils.dividePercentage(this.scoreDau, getTotalDauOrDnuByUserType());
    }

    /**
     * 计算 落地页预加载Uv 落地页展现UV 落地页下载按钮点击UV 落地页下载/展现
     *
     * @param webLoadingUserNum
     * @param downloadPagShowUserNum
     * @param downloadPagClickUserNum
     */
    public void calculateDownloading(Long webLoadingUserNum, Long downloadPagShowUserNum,
                                     Long downloadPagClickUserNum) {

        if (!ObjectUtils.isEmpty(webLoadingUserNum) && webLoadingUserNum > 0) {
            this.webLoadingUserNum = webLoadingUserNum;
        }
        if (!ObjectUtils.isEmpty(downloadPagShowUserNum) && downloadPagShowUserNum > 0) {
            this.landingPageShowNum = downloadPagShowUserNum;
        }
        if (!ObjectUtils.isEmpty(downloadPagClickUserNum) && downloadPagClickUserNum > 0) {
            this.landingPageDownloadNum = downloadPagClickUserNum;
        }

        this.landingPageShowRate =
                BigDecimalUtils.dividePercentage(this.landingPageDownloadNum, this.landingPageShowNum);
    }

    /**
     * 计算提现页DAU 提现页渗透率
     *
     * @param showWithdrawPageUserNum
     */
    public void calculateWithdrawPage(Long showWithdrawPageUserNum) {
        if (!ObjectUtils.isEmpty(showWithdrawPageUserNum) && showWithdrawPageUserNum > 0) {
            this.withdrawPageUserNum = showWithdrawPageUserNum;
        }

        this.withdrawPageRate = BigDecimalUtils.dividePercentage(this.withdrawPageUserNum, this.dau);
    }

    public void calculateWithdrawAmount(BigDecimal withdrawTotalAmount) {
        if (!ObjectUtils.isEmpty(withdrawTotalAmount)) {
            this.withdrawTotalAmount = withdrawTotalAmount;
        }
    }

    public void calculatePromotionCost(BigDecimal pkgPromotionTotalCost, Long registerPkgUserNum) {
        if (ObjectUtils.isEmpty(registerPkgUserNum) || ObjectUtils.isEmpty(pkgPromotionTotalCost)) {
            // 总新增用户数为空 或 推广花费对象为空直接返回
            return;
        }
        if (this.dnu == 0L) {
            // 为0不进行赋值处理，使用默认值0
            return;
        }
        if (Objects.equals(this.dnu, registerPkgUserNum) && registerPkgUserNum > 0) {
            this.promotionCost = pkgPromotionTotalCost;
        } else {
            // 计算当前包用户的占比( 包对应的新增用户数/包对应的总新增用户数)
            this.promotionCost = BigDecimalUtils.divide(pkgPromotionTotalCost, registerPkgUserNum)
                    .multiply(BigDecimal.valueOf(this.dnu));
        }

        // 计算新增用户对应的投放金额
        this.promotionAvgCost = BigDecimalUtils.divide(this.promotionCost, new BigDecimal(this.dnu));
    }

    private Long getTotalDauOrDnuByUserType() {
        if (userType == 0) {
            return this.dau;
        }
        return this.dnu;
    }

    /**
     * 计算 导流下载用户 AvgCpi（线下） AvgCpi（线下-提现）
     *
     * @param conversionUserNum
     */
    public void calculateConversion(Long conversionUserNum) {
        if (!ObjectUtils.isEmpty(conversionUserNum) && conversionUserNum > 0) {
            this.offerUserNum = conversionUserNum;
        }
        this.promotionAvgOfferRate = BigDecimalUtils.divide(this.promotionCost, new BigDecimal(this.offerUserNum));
        this.promotionAvgOfferWithdrawRate =
                BigDecimalUtils.divide(this.promotionCost.add(this.withdrawTotalAmount), new BigDecimal(this.offerUserNum));
    }

    /**
     * 计算 导流付费用户数 AvgCps（线下） AvgCps（线下-提现） 导流Revenue(d0) ROI(d0)
     *
     * @param paymentAmount
     * @param paymentUserNum
     */
    public void calculateConversionPayment(BigDecimal paymentAmount, Long paymentUserNum) {
        if (ObjectUtils.isEmpty(paymentUserNum)) {
            return;
        }

        if (!ObjectUtils.isEmpty(paymentUserNum) && paymentUserNum > 0) {
            this.promotionPayUserNum = paymentUserNum;
        }
        if (!ObjectUtils.isEmpty(paymentAmount)) {
            this.promotionPayIncome = paymentAmount;
        }
        this.promotionAvgPayRate = BigDecimalUtils.divide(this.promotionCost, new BigDecimal(this.promotionPayUserNum));
        this.promotionRoi = BigDecimalUtils.divide(this.promotionPayIncome, this.promotionCost);
        this.promotionAvgPayWithdrawRate = BigDecimalUtils.divide(this.promotionCost.add(this.withdrawTotalAmount),
                new BigDecimal(this.promotionPayUserNum));
    }

    public void calculateRetention(Long retention1, Long retention7) {
        if (!ObjectUtils.isEmpty(retention1)) {
            this.d1NewUserRate = BigDecimalUtils.dividePercentage(retention1, this.dnu);
        }
        if (!ObjectUtils.isEmpty(retention7)) {
            this.d7NewUserRate = BigDecimalUtils.dividePercentage(retention7, this.dnu);
        }
    }
}
