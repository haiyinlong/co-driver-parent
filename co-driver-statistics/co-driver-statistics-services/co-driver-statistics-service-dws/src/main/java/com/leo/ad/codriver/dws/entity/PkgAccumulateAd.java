package com.leo.ad.codriver.dws.entity;

import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import lombok.Data;

import java.math.BigDecimal;

/**
 * PkgAccumulateAd
 *
 * @author HaiYinLong
 * @version 2025/03/07 18:24
 **/
@Data
public class PkgAccumulateAd {
    /**
     * 展示次数
     */
    private Long totalShowCount;

    /**
     * 广告收入
     */
    private BigDecimal totalIncome;

    /**
     * ecpm
     */
    private BigDecimal totalEcpm;

    /**
     * 展示次数
     */
    private Long totalNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal totalNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal totalNotCustomDirectsoldEcpm;

    /**
     * 展示次数
     */
    private Long directsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal directsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal directsoldEcpm;

    /**
     * 展示次数
     */
    private Long customNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal customNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal customNetworkEcpm;

    /**
     * 展示次数
     */
    private Long customDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal customDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal customDirectsoldEcpm;

    /**
     * 展示次数
     */
    private Long bannerShowCount;

    /**
     * 广告收入
     */
    private BigDecimal bannerIncome;

    /**
     * ecpm
     */
    private BigDecimal bannerEcpm;

    /**
     * 展示次数
     */
    private Long bannerDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal bannerDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal bannerDirectsoldEcpm;

    /**
     * 展示次数
     */
    private Long bannerExchangeShowCount;

    /**
     * 广告收入
     */
    private BigDecimal bannerExchangeIncome;

    /**
     * ecpm
     */
    private BigDecimal bannerExchangeEcpm;

    /**
     * 展示次数
     */
    private Long bannerNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal bannerNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal bannerNetworkEcpm;

    /**
     * 展示次数
     */
    private Long bannerCustomNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal bannerCustomNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal bannerCustomNetworkEcpm;

    /**
     * 展示次数
     */
    private Long bannerMintegralBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal bannerMintegralBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal bannerMintegralBiddingEcpm;

    /**
     * 展示次数
     */
    private Long bannerNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal bannerNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal bannerNotCustomDirectsoldEcpm;

    /**
     * 展示次数
     */
    private Long interShowCount;

    /**
     * 广告收入
     */
    private BigDecimal interIncome;

    /**
     * ecpm
     */
    private BigDecimal interEcpm;

    /**
     * 展示次数
     */
    private Long interDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal interDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal interDirectsoldEcpm;

    /**
     * 展示次数
     */
    private Long interExchangeShowCount;

    /**
     * 广告收入
     */
    private BigDecimal interExchangeIncome;

    /**
     * ecpm
     */
    private BigDecimal interExchangeEcpm;

    /**
     * 展示次数
     */
    private Long interNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal interNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal interNetworkEcpm;

    /**
     * 展示次数
     */
    private Long interMintegralBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal interMintegralBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal interMintegralBiddingEcpm;

    /**
     * 展示次数
     */
    private Long interVungleBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal interVungleBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal interVungleBiddingEcpm;

    /**
     * 展示次数
     */
    private Long interNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal interNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal interNotCustomDirectsoldEcpm;

    /**
     * 展示次数
     */
    private Long mrecShowCount;

    /**
     * 广告收入
     */
    private BigDecimal mrecIncome;

    /**
     * ecpm
     */
    private BigDecimal mrecEcpm;

    /**
     * 展示次数
     */
    private Long mrecDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal mrecDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal mrecDirectsoldEcpm;

    /**
     * 展示次数
     */
    private Long mrecExchangeShowCount;

    /**
     * 广告收入
     */
    private BigDecimal mrecExchangeIncome;

    /**
     * ecpm
     */
    private BigDecimal mrecExchangeEcpm;

    /**
     * 展示次数
     */
    private Long mrecCustomNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal mrecCustomNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal mrecCustomNetworkEcpm;

    /**
     * 展示次数
     */
    private Long mrecNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal mrecNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal mrecNotCustomDirectsoldEcpm;

    /**
     * 展示次数
     */
    private Long rewardShowCount;

    /**
     * 广告收入
     */
    private BigDecimal rewardIncome;

    /**
     * ecpm
     */
    private BigDecimal rewardEcpm;

    /**
     * 展示次数
     */
    private Long rewardExchangeShowCount;

    /**
     * 广告收入
     */
    private BigDecimal rewardExchangeIncome;

    /**
     * ecpm
     */
    private BigDecimal rewardExchangeEcpm;

    /**
     * 展示次数
     */
    private Long rewardNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal rewardNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal rewardNetworkEcpm;

    /**
     * 展示次数
     */
    private Long rewardCustomNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal rewardCustomNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal rewardCustomNetworkEcpm;

    /**
     * 展示次数
     */
    private Long rewardFacebookNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal rewardFacebookNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal rewardFacebookNetworkEcpm;

    /**
     * 展示次数
     */
    private Long rewardMintegralBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal rewardMintegralBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal rewardMintegralBiddingEcpm;

    /**
     * 展示次数
     */
    private Long rewardVungleBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal rewardVungleBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal rewardVungleBiddingEcpm;

    /**
     * 展示次数
     */
    private Long rewardNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal rewardNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal rewardNotCustomDirectsoldEcpm;
    /**
     * 累计展示次数
     */
    private Long accumulateTotalShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateTotalIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateTotalEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateTotalNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateTotalNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateTotalNotCustomDirectsoldEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateDirectsoldEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateCustomNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateCustomNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateCustomNetworkEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateCustomDirectsoldEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateBannerShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateBannerIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateBannerEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateBannerDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateBannerDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateBannerDirectsoldEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateBannerExchangeShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateBannerExchangeIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateBannerExchangeEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateBannerNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateBannerNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateBannerNetworkEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateBannerCustomNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateBannerCustomNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateBannerCustomNetworkEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateBannerMintegralBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateBannerMintegralBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateBannerMintegralBiddingEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateBannerNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateBannerNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateBannerNotCustomDirectsoldEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateInterShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateInterIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateInterEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateInterDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateInterDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateInterDirectsoldEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateInterExchangeShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateInterExchangeIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateInterExchangeEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateInterNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateInterNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateInterNetworkEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateInterMintegralBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateInterMintegralBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateInterMintegralBiddingEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateInterVungleBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateInterVungleBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateInterVungleBiddingEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateInterNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateInterNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateInterNotCustomDirectsoldEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateMrecShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateMrecIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateMrecEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateMrecDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateMrecDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateMrecDirectsoldEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateMrecExchangeShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateMrecExchangeIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateMrecExchangeEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateMrecCustomNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateMrecCustomNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateMrecCustomNetworkEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateMrecNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateMrecNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateMrecNotCustomDirectsoldEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateRewardShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateRewardIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateRewardEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateRewardExchangeShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateRewardExchangeIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateRewardExchangeEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateRewardNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateRewardNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateRewardNetworkEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateRewardCustomNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateRewardCustomNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateRewardCustomNetworkEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateRewardFacebookNetworkShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateRewardFacebookNetworkIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateRewardFacebookNetworkEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateRewardMintegralBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateRewardMintegralBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateRewardMintegralBiddingEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateRewardVungleBiddingShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateRewardVungleBiddingIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateRewardVungleBiddingEcpm;

    /**
     * 累计展示次数
     */
    private Long accumulateRewardNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateRewardNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateRewardNotCustomDirectsoldEcpm;

    protected void todayInit() {
        this.totalShowCount = 0L;
        this.totalIncome = BigDecimal.ZERO;
        this.totalEcpm = BigDecimal.ZERO;
        this.totalNotCustomDirectsoldShowCount = 0L;
        this.totalNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.totalNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.directsoldShowCount = 0L;
        this.directsoldIncome = BigDecimal.ZERO;
        this.directsoldEcpm = BigDecimal.ZERO;
        this.customNetworkShowCount = 0L;
        this.customNetworkIncome = BigDecimal.ZERO;
        this.customNetworkEcpm = BigDecimal.ZERO;
        this.customDirectsoldShowCount = 0L;
        this.customDirectsoldIncome = BigDecimal.ZERO;
        this.customDirectsoldEcpm = BigDecimal.ZERO;
        this.bannerShowCount = 0L;
        this.bannerIncome = BigDecimal.ZERO;
        this.bannerEcpm = BigDecimal.ZERO;
        this.bannerDirectsoldShowCount = 0L;
        this.bannerDirectsoldIncome = BigDecimal.ZERO;
        this.bannerDirectsoldEcpm = BigDecimal.ZERO;
        this.bannerExchangeShowCount = 0L;
        this.bannerExchangeIncome = BigDecimal.ZERO;
        this.bannerExchangeEcpm = BigDecimal.ZERO;
        this.bannerNetworkShowCount = 0L;
        this.bannerNetworkIncome = BigDecimal.ZERO;
        this.bannerNetworkEcpm = BigDecimal.ZERO;
        this.bannerCustomNetworkShowCount = 0L;
        this.bannerCustomNetworkIncome = BigDecimal.ZERO;
        this.bannerCustomNetworkEcpm = BigDecimal.ZERO;
        this.bannerMintegralBiddingShowCount = 0L;
        this.bannerMintegralBiddingIncome = BigDecimal.ZERO;
        this.bannerMintegralBiddingEcpm = BigDecimal.ZERO;
        this.bannerNotCustomDirectsoldShowCount = 0L;
        this.bannerNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.bannerNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.interShowCount = 0L;
        this.interIncome = BigDecimal.ZERO;
        this.interEcpm = BigDecimal.ZERO;
        this.interDirectsoldShowCount = 0L;
        this.interDirectsoldIncome = BigDecimal.ZERO;
        this.interDirectsoldEcpm = BigDecimal.ZERO;
        this.interExchangeShowCount = 0L;
        this.interExchangeIncome = BigDecimal.ZERO;
        this.interExchangeEcpm = BigDecimal.ZERO;
        this.interNetworkShowCount = 0L;
        this.interNetworkIncome = BigDecimal.ZERO;
        this.interNetworkEcpm = BigDecimal.ZERO;
        this.interMintegralBiddingShowCount = 0L;
        this.interMintegralBiddingIncome = BigDecimal.ZERO;
        this.interMintegralBiddingEcpm = BigDecimal.ZERO;
        this.interVungleBiddingShowCount = 0L;
        this.interVungleBiddingIncome = BigDecimal.ZERO;
        this.interVungleBiddingEcpm = BigDecimal.ZERO;
        this.interNotCustomDirectsoldShowCount = 0L;
        this.interNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.interNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.mrecShowCount = 0L;
        this.mrecIncome = BigDecimal.ZERO;
        this.mrecEcpm = BigDecimal.ZERO;
        this.mrecDirectsoldShowCount = 0L;
        this.mrecDirectsoldIncome = BigDecimal.ZERO;
        this.mrecDirectsoldEcpm = BigDecimal.ZERO;
        this.mrecExchangeShowCount = 0L;
        this.mrecExchangeIncome = BigDecimal.ZERO;
        this.mrecExchangeEcpm = BigDecimal.ZERO;
        this.mrecCustomNetworkShowCount = 0L;
        this.mrecCustomNetworkIncome = BigDecimal.ZERO;
        this.mrecCustomNetworkEcpm = BigDecimal.ZERO;
        this.mrecNotCustomDirectsoldShowCount = 0L;
        this.mrecNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.mrecNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.rewardShowCount = 0L;
        this.rewardIncome = BigDecimal.ZERO;
        this.rewardEcpm = BigDecimal.ZERO;
        this.rewardExchangeShowCount = 0L;
        this.rewardExchangeIncome = BigDecimal.ZERO;
        this.rewardExchangeEcpm = BigDecimal.ZERO;
        this.rewardNetworkShowCount = 0L;
        this.rewardNetworkIncome = BigDecimal.ZERO;
        this.rewardNetworkEcpm = BigDecimal.ZERO;
        this.rewardCustomNetworkShowCount = 0L;
        this.rewardCustomNetworkIncome = BigDecimal.ZERO;
        this.rewardCustomNetworkEcpm = BigDecimal.ZERO;
        this.rewardFacebookNetworkShowCount = 0L;
        this.rewardFacebookNetworkIncome = BigDecimal.ZERO;
        this.rewardFacebookNetworkEcpm = BigDecimal.ZERO;
        this.rewardMintegralBiddingShowCount = 0L;
        this.rewardMintegralBiddingIncome = BigDecimal.ZERO;
        this.rewardMintegralBiddingEcpm = BigDecimal.ZERO;
        this.rewardVungleBiddingShowCount = 0L;
        this.rewardVungleBiddingIncome = BigDecimal.ZERO;
        this.rewardVungleBiddingEcpm = BigDecimal.ZERO;
        this.rewardNotCustomDirectsoldShowCount = 0L;
        this.rewardNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.rewardNotCustomDirectsoldEcpm = BigDecimal.ZERO;
    }

    protected void accumulateInit() {
        this.accumulateTotalShowCount = 0L;
        this.accumulateTotalIncome = BigDecimal.ZERO;
        this.accumulateTotalEcpm = BigDecimal.ZERO;
        this.accumulateTotalNotCustomDirectsoldShowCount = 0L;
        this.accumulateTotalNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateTotalNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.accumulateDirectsoldShowCount = 0L;
        this.accumulateDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateDirectsoldEcpm = BigDecimal.ZERO;
        this.accumulateCustomNetworkShowCount = 0L;
        this.accumulateCustomNetworkIncome = BigDecimal.ZERO;
        this.accumulateCustomNetworkEcpm = BigDecimal.ZERO;
        this.accumulateCustomDirectsoldShowCount = 0L;
        this.accumulateCustomDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.accumulateBannerShowCount = 0L;
        this.accumulateBannerIncome = BigDecimal.ZERO;
        this.accumulateBannerEcpm = BigDecimal.ZERO;
        this.accumulateBannerDirectsoldShowCount = 0L;
        this.accumulateBannerDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateBannerDirectsoldEcpm = BigDecimal.ZERO;
        this.accumulateBannerExchangeShowCount = 0L;
        this.accumulateBannerExchangeIncome = BigDecimal.ZERO;
        this.accumulateBannerExchangeEcpm = BigDecimal.ZERO;
        this.accumulateBannerNetworkShowCount = 0L;
        this.accumulateBannerNetworkIncome = BigDecimal.ZERO;
        this.accumulateBannerNetworkEcpm = BigDecimal.ZERO;
        this.accumulateBannerCustomNetworkShowCount = 0L;
        this.accumulateBannerCustomNetworkIncome = BigDecimal.ZERO;
        this.accumulateBannerCustomNetworkEcpm = BigDecimal.ZERO;
        this.accumulateBannerMintegralBiddingShowCount = 0L;
        this.accumulateBannerMintegralBiddingIncome = BigDecimal.ZERO;
        this.accumulateBannerMintegralBiddingEcpm = BigDecimal.ZERO;
        this.accumulateBannerNotCustomDirectsoldShowCount = 0L;
        this.accumulateBannerNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateBannerNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.accumulateInterShowCount = 0L;
        this.accumulateInterIncome = BigDecimal.ZERO;
        this.accumulateInterEcpm = BigDecimal.ZERO;
        this.accumulateInterDirectsoldShowCount = 0L;
        this.accumulateInterDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateInterDirectsoldEcpm = BigDecimal.ZERO;
        this.accumulateInterExchangeShowCount = 0L;
        this.accumulateInterExchangeIncome = BigDecimal.ZERO;
        this.accumulateInterExchangeEcpm = BigDecimal.ZERO;
        this.accumulateInterNetworkShowCount = 0L;
        this.accumulateInterNetworkIncome = BigDecimal.ZERO;
        this.accumulateInterNetworkEcpm = BigDecimal.ZERO;
        this.accumulateInterMintegralBiddingShowCount = 0L;
        this.accumulateInterMintegralBiddingIncome = BigDecimal.ZERO;
        this.accumulateInterMintegralBiddingEcpm = BigDecimal.ZERO;
        this.accumulateInterVungleBiddingShowCount = 0L;
        this.accumulateInterVungleBiddingIncome = BigDecimal.ZERO;
        this.accumulateInterVungleBiddingEcpm = BigDecimal.ZERO;
        this.accumulateInterNotCustomDirectsoldShowCount = 0L;
        this.accumulateInterNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateInterNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.accumulateMrecShowCount = 0L;
        this.accumulateMrecIncome = BigDecimal.ZERO;
        this.accumulateMrecEcpm = BigDecimal.ZERO;
        this.accumulateMrecDirectsoldShowCount = 0L;
        this.accumulateMrecDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateMrecDirectsoldEcpm = BigDecimal.ZERO;
        this.accumulateMrecExchangeShowCount = 0L;
        this.accumulateMrecExchangeIncome = BigDecimal.ZERO;
        this.accumulateMrecExchangeEcpm = BigDecimal.ZERO;
        this.accumulateMrecCustomNetworkShowCount = 0L;
        this.accumulateMrecCustomNetworkIncome = BigDecimal.ZERO;
        this.accumulateMrecCustomNetworkEcpm = BigDecimal.ZERO;
        this.accumulateMrecNotCustomDirectsoldShowCount = 0L;
        this.accumulateMrecNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateMrecNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.accumulateRewardShowCount = 0L;
        this.accumulateRewardIncome = BigDecimal.ZERO;
        this.accumulateRewardEcpm = BigDecimal.ZERO;
        this.accumulateRewardExchangeShowCount = 0L;
        this.accumulateRewardExchangeIncome = BigDecimal.ZERO;
        this.accumulateRewardExchangeEcpm = BigDecimal.ZERO;
        this.accumulateRewardNetworkShowCount = 0L;
        this.accumulateRewardNetworkIncome = BigDecimal.ZERO;
        this.accumulateRewardNetworkEcpm = BigDecimal.ZERO;
        this.accumulateRewardCustomNetworkShowCount = 0L;
        this.accumulateRewardCustomNetworkIncome = BigDecimal.ZERO;
        this.accumulateRewardCustomNetworkEcpm = BigDecimal.ZERO;
        this.accumulateRewardFacebookNetworkShowCount = 0L;
        this.accumulateRewardFacebookNetworkIncome = BigDecimal.ZERO;
        this.accumulateRewardFacebookNetworkEcpm = BigDecimal.ZERO;
        this.accumulateRewardMintegralBiddingShowCount = 0L;
        this.accumulateRewardMintegralBiddingIncome = BigDecimal.ZERO;
        this.accumulateRewardMintegralBiddingEcpm = BigDecimal.ZERO;
        this.accumulateRewardVungleBiddingShowCount = 0L;
        this.accumulateRewardVungleBiddingIncome = BigDecimal.ZERO;
        this.accumulateRewardVungleBiddingEcpm = BigDecimal.ZERO;
        this.accumulateRewardNotCustomDirectsoldShowCount = 0L;
        this.accumulateRewardNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.accumulateRewardNotCustomDirectsoldEcpm = BigDecimal.ZERO;
    }

    public void calculateToday(DwdUserAdRecord dwdUserAdRecord) {
        // 计算汇总数据
        handleTotal(dwdUserAdRecord);
        handleTotalNotCustomDirectsold(dwdUserAdRecord);
        // 根据各个广告类型进行汇总
        handleDirectsold(dwdUserAdRecord);
        handleCustomNetwork(dwdUserAdRecord);
        handleCustomDirectsold(dwdUserAdRecord);
        // banner
        handleBanner(dwdUserAdRecord);
        handleBannerNotCustomDirectsold(dwdUserAdRecord);
        handleBannerDirectsold(dwdUserAdRecord);
        handleBannerCustomNetwork(dwdUserAdRecord);
        handleBannerExchange(dwdUserAdRecord);
        handleBannerNetwork(dwdUserAdRecord);
        handleBannerMintegralBidding(dwdUserAdRecord);
        // Inter 插屏
        handleInter(dwdUserAdRecord);
        handleInterNotCustomDirectsold(dwdUserAdRecord);
        handleInterDirectsold(dwdUserAdRecord);
        handleInterExchange(dwdUserAdRecord);
        handleInterNetwork(dwdUserAdRecord);
        handleInterMintegralBidding(dwdUserAdRecord);
        handleInterVungleBidding(dwdUserAdRecord);
        // Mrec 原生
        handleMrec(dwdUserAdRecord);
        handleMrecNotCustomDirectsold(dwdUserAdRecord);
        handleMrecDirectsold(dwdUserAdRecord);
        handleMrecCustomNetwork(dwdUserAdRecord);
        handleMrecExchange(dwdUserAdRecord);
        // Reward 激励
        handleReward(dwdUserAdRecord);
        handleRewardNotCustomDirectsold(dwdUserAdRecord);
        handleRewardFacebookNetwork(dwdUserAdRecord);
        handleRewardCustomNetwork(dwdUserAdRecord);
        handleRewardExchange(dwdUserAdRecord);
        handleRewardNetwork(dwdUserAdRecord);
        handleRewardMintegralBidding(dwdUserAdRecord);
        handleRewardVungleBidding(dwdUserAdRecord);
    }

    private void handleTotal(DwdUserAdRecord dwdUserAdRecord) {
        this.totalShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.totalIncome = BigDecimalUtils.add(this.totalIncome, dwdUserAdRecord.getRevenue());
        this.totalEcpm = calculateEcpm(this.totalIncome, this.totalShowCount);
    }

    private void handleTotalNotCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (dwdUserAdRecord.isNetworkApplovinDirectsold() || dwdUserAdRecord.isNetworkCustomNetworkSdk()) {
            return;
        }
        this.totalNotCustomDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.totalNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.totalNotCustomDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.totalNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.totalNotCustomDirectsoldIncome, this.totalNotCustomDirectsoldShowCount);
    }

    private void handleDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isNetworkApplovinDirectsold()) {
            return;
        }
        this.directsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.directsoldIncome = BigDecimalUtils.add(this.directsoldIncome, dwdUserAdRecord.getRevenue());
        this.directsoldEcpm = calculateEcpm(this.directsoldIncome, this.directsoldShowCount);
    }

    private void handleCustomNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isNetworkCustomNetworkSdk()) {
            return;
        }
        this.customNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.customNetworkIncome = BigDecimalUtils.add(this.customNetworkIncome, dwdUserAdRecord.getRevenue());
        this.customNetworkEcpm = this.calculateEcpm(this.customNetworkIncome, this.customNetworkShowCount);
    }

    private void handleCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isNetworkApplovinDirectsold() && dwdUserAdRecord.isNetworkCustomNetworkSdk())) {
            return;
        }
        this.customDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.customDirectsoldIncome = BigDecimalUtils.add(this.customDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.customDirectsoldEcpm = this.calculateEcpm(this.customDirectsoldIncome, this.customDirectsoldShowCount);
    }

    private void handleBanner(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isBanner()) {
            return;
        }
        this.bannerShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerIncome = BigDecimalUtils.add(this.bannerIncome, dwdUserAdRecord.getRevenue());
        this.bannerEcpm = this.calculateEcpm(this.bannerIncome, this.bannerShowCount);
    }

    private void handleBannerNotCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isBanner()) {
            return;
        }

        if (dwdUserAdRecord.isNetworkApplovinDirectsold() || dwdUserAdRecord.isNetworkCustomNetworkSdk()) {
            return;
        }
        this.bannerNotCustomDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.bannerNotCustomDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.bannerNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.bannerNotCustomDirectsoldIncome, this.bannerNotCustomDirectsoldShowCount);
    }

    private void handleBannerDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isBanner() && dwdUserAdRecord.isNetworkApplovinDirectsold())) {
            return;
        }
        this.bannerDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerDirectsoldIncome = BigDecimalUtils.add(this.bannerDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.bannerDirectsoldEcpm = this.calculateEcpm(this.bannerDirectsoldIncome, this.bannerDirectsoldShowCount);
    }

    private void handleBannerCustomNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isBanner() && dwdUserAdRecord.isNetworkCustomNetworkSdk())) {
            return;
        }
        this.bannerCustomNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerCustomNetworkIncome =
            BigDecimalUtils.add(this.bannerCustomNetworkIncome, dwdUserAdRecord.getRevenue());
        this.bannerCustomNetworkEcpm =
            this.calculateEcpm(this.bannerCustomNetworkIncome, this.bannerCustomNetworkShowCount);
    }

    private void handleBannerExchange(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isBanner() && dwdUserAdRecord.isApplovinExchange())) {
            return;
        }
        this.bannerExchangeShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerExchangeIncome = BigDecimalUtils.add(this.bannerExchangeIncome, dwdUserAdRecord.getRevenue());
        this.bannerExchangeEcpm = this.calculateEcpm(this.bannerExchangeIncome, this.bannerExchangeShowCount);
    }

    private void handleBannerNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isBanner() && dwdUserAdRecord.isApplovinNetwork())) {
            return;
        }
        this.bannerNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerNetworkIncome = BigDecimalUtils.add(this.bannerNetworkIncome, dwdUserAdRecord.getRevenue());
        this.bannerNetworkEcpm = this.calculateEcpm(this.bannerNetworkIncome, this.bannerNetworkShowCount);
    }

    private void handleBannerMintegralBidding(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isBanner() && dwdUserAdRecord.isMintegralBidding())) {
            return;
        }
        this.bannerMintegralBiddingShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerMintegralBiddingIncome =
            BigDecimalUtils.add(this.bannerMintegralBiddingIncome, dwdUserAdRecord.getRevenue());
        this.bannerMintegralBiddingEcpm =
            this.calculateEcpm(this.bannerMintegralBiddingIncome, this.bannerMintegralBiddingShowCount);
    }

    private void handleInter(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isInter()) {
            return;
        }
        this.interShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interIncome = BigDecimalUtils.add(this.interIncome, dwdUserAdRecord.getRevenue());
        this.interEcpm = this.calculateEcpm(this.interIncome, this.interShowCount);
    }

    private void handleInterNotCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isInter()) {
            return;
        }

        if (dwdUserAdRecord.isNetworkApplovinDirectsold() || dwdUserAdRecord.isNetworkCustomNetworkSdk()) {
            return;
        }
        this.interNotCustomDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.interNotCustomDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.interNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.interNotCustomDirectsoldIncome, this.interNotCustomDirectsoldShowCount);
    }

    private void handleInterDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isInter() && dwdUserAdRecord.isNetworkApplovinDirectsold())) {
            return;
        }
        this.interDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interDirectsoldIncome = BigDecimalUtils.add(this.interDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.interDirectsoldEcpm = this.calculateEcpm(this.interDirectsoldIncome, this.interDirectsoldShowCount);
    }

    private void handleInterExchange(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isInter() && dwdUserAdRecord.isApplovinExchange())) {
            return;
        }
        this.interExchangeShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interExchangeIncome = BigDecimalUtils.add(this.interExchangeIncome, dwdUserAdRecord.getRevenue());
        this.interExchangeEcpm = this.calculateEcpm(this.interExchangeIncome, this.interExchangeShowCount);
    }

    private void handleInterNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isInter() && dwdUserAdRecord.isApplovinNetwork())) {
            return;
        }
        this.interNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interNetworkIncome = BigDecimalUtils.add(this.interNetworkIncome, dwdUserAdRecord.getRevenue());
        this.interNetworkEcpm = this.calculateEcpm(this.interNetworkIncome, this.interNetworkShowCount);
    }

    private void handleInterVungleBidding(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isInter() && dwdUserAdRecord.isVungleBidding())) {
            return;
        }
        this.interVungleBiddingShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interVungleBiddingIncome =
            BigDecimalUtils.add(this.interVungleBiddingIncome, dwdUserAdRecord.getRevenue());
        this.interVungleBiddingEcpm =
            this.calculateEcpm(this.interVungleBiddingIncome, this.interVungleBiddingShowCount);
    }

    private void handleInterMintegralBidding(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isInter() && dwdUserAdRecord.isMintegralBidding())) {
            return;
        }
        this.interMintegralBiddingShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interMintegralBiddingIncome =
            BigDecimalUtils.add(this.interMintegralBiddingIncome, dwdUserAdRecord.getRevenue());
        this.interMintegralBiddingEcpm =
            this.calculateEcpm(this.interMintegralBiddingIncome, this.interMintegralBiddingShowCount);
    }

    private void handleMrec(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isMrec()) {
            return;
        }
        this.mrecShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecIncome = BigDecimalUtils.add(this.mrecIncome, dwdUserAdRecord.getRevenue());
        this.mrecEcpm = this.calculateEcpm(this.mrecIncome, this.mrecShowCount);
    }

    private void handleMrecNotCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isMrec()) {
            return;
        }

        if (dwdUserAdRecord.isNetworkApplovinDirectsold() || dwdUserAdRecord.isNetworkCustomNetworkSdk()) {
            return;
        }
        this.mrecNotCustomDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.mrecNotCustomDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.mrecNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.mrecNotCustomDirectsoldIncome, this.mrecNotCustomDirectsoldShowCount);
    }

    private void handleMrecDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isMrec() && dwdUserAdRecord.isNetworkApplovinDirectsold())) {
            return;
        }
        this.mrecDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecDirectsoldIncome = BigDecimalUtils.add(this.mrecDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.mrecDirectsoldEcpm = this.calculateEcpm(this.mrecDirectsoldIncome, this.mrecDirectsoldShowCount);
    }

    private void handleMrecCustomNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isMrec() && dwdUserAdRecord.isNetworkCustomNetworkSdk())) {
            return;
        }
        this.mrecCustomNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecCustomNetworkIncome = BigDecimalUtils.add(this.mrecCustomNetworkIncome, dwdUserAdRecord.getRevenue());
        this.mrecCustomNetworkEcpm = this.calculateEcpm(this.mrecCustomNetworkIncome, this.mrecCustomNetworkShowCount);
    }

    private void handleMrecExchange(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isMrec() && dwdUserAdRecord.isApplovinExchange())) {
            return;
        }
        this.mrecExchangeShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecExchangeIncome = BigDecimalUtils.add(this.mrecExchangeIncome, dwdUserAdRecord.getRevenue());
        this.mrecExchangeEcpm = this.calculateEcpm(this.mrecExchangeIncome, this.mrecExchangeShowCount);
    }

    private void handleReward(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isAdFormatReward()) {
            return;
        }
        this.rewardShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardIncome = BigDecimalUtils.add(this.rewardIncome, dwdUserAdRecord.getRevenue());
        this.rewardEcpm = this.calculateEcpm(this.rewardIncome, this.rewardShowCount);
    }

    private void handleRewardNotCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isAdFormatReward()) {
            return;
        }

        if (dwdUserAdRecord.isNetworkApplovinDirectsold() || dwdUserAdRecord.isNetworkCustomNetworkSdk()) {
            return;
        }
        this.rewardNotCustomDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.rewardNotCustomDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.rewardNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.rewardNotCustomDirectsoldIncome, this.rewardNotCustomDirectsoldShowCount);
    }

    private void handleRewardFacebookNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isAdFormatReward() && dwdUserAdRecord.isFacebookNetwork())) {
            return;
        }
        this.rewardFacebookNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardFacebookNetworkIncome =
            BigDecimalUtils.add(this.rewardFacebookNetworkIncome, dwdUserAdRecord.getRevenue());
        this.rewardFacebookNetworkEcpm =
            this.calculateEcpm(this.rewardFacebookNetworkIncome, this.rewardFacebookNetworkShowCount);
    }

    private void handleRewardCustomNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isAdFormatReward() && dwdUserAdRecord.isNetworkCustomNetworkSdk())) {
            return;
        }
        this.rewardCustomNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardCustomNetworkIncome =
            BigDecimalUtils.add(this.rewardCustomNetworkIncome, dwdUserAdRecord.getRevenue());
        this.rewardCustomNetworkEcpm =
            this.calculateEcpm(this.rewardCustomNetworkIncome, this.rewardCustomNetworkShowCount);
    }

    private void handleRewardExchange(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isAdFormatReward() && dwdUserAdRecord.isApplovinExchange())) {
            return;
        }
        this.rewardExchangeShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardExchangeIncome = BigDecimalUtils.add(this.rewardExchangeIncome, dwdUserAdRecord.getRevenue());
        this.rewardExchangeEcpm = this.calculateEcpm(this.rewardExchangeIncome, this.rewardExchangeShowCount);
    }

    private void handleRewardNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isAdFormatReward() && dwdUserAdRecord.isApplovinNetwork())) {
            return;
        }
        this.rewardNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardNetworkIncome = BigDecimalUtils.add(this.rewardNetworkIncome, dwdUserAdRecord.getRevenue());
        this.rewardNetworkEcpm = this.calculateEcpm(this.rewardNetworkIncome, this.rewardNetworkShowCount);
    }

    private void handleRewardMintegralBidding(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isAdFormatReward() && dwdUserAdRecord.isMintegralBidding())) {
            return;
        }
        this.rewardMintegralBiddingShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardMintegralBiddingIncome =
            BigDecimalUtils.add(this.rewardMintegralBiddingIncome, dwdUserAdRecord.getRevenue());
        this.rewardMintegralBiddingEcpm =
            this.calculateEcpm(this.rewardMintegralBiddingIncome, this.rewardMintegralBiddingShowCount);
    }

    private void handleRewardVungleBidding(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isAdFormatReward() && dwdUserAdRecord.isVungleBidding())) {
            return;
        }
        this.rewardVungleBiddingShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardVungleBiddingIncome =
            BigDecimalUtils.add(this.rewardVungleBiddingIncome, dwdUserAdRecord.getRevenue());
        this.rewardVungleBiddingEcpm =
            this.calculateEcpm(this.rewardVungleBiddingIncome, this.rewardVungleBiddingShowCount);
    }

    /**
     * (广告收入 / 曝光量) * 1000
     *
     * @param income
     * @param showNum
     * @return
     */
    private BigDecimal calculateEcpm(BigDecimal income, Long showNum) {
        return BigDecimalUtils.multiply(BigDecimalUtils.divide(income, showNum), BigDecimal.valueOf(1000));
    }

    protected void addAccumulate(PkgAccumulateAd advertisingValue) {
        this.accumulateTotalShowCount += advertisingValue.getTotalShowCount();
        this.accumulateTotalIncome =
            BigDecimalUtils.add(this.getAccumulateTotalIncome(), advertisingValue.getTotalIncome());
        this.accumulateTotalEcpm = this.calculateEcpm(this.accumulateTotalIncome, this.accumulateTotalShowCount);
        this.accumulateTotalNotCustomDirectsoldShowCount += advertisingValue.getTotalNotCustomDirectsoldShowCount();
        this.accumulateTotalNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateTotalNotCustomDirectsoldIncome,
                advertisingValue.getTotalNotCustomDirectsoldIncome());
        this.accumulateTotalNotCustomDirectsoldEcpm = this.calculateEcpm(this.accumulateTotalNotCustomDirectsoldIncome,
            accumulateTotalNotCustomDirectsoldShowCount);

        this.accumulateDirectsoldShowCount += advertisingValue.getDirectsoldShowCount();
        this.accumulateDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateDirectsoldIncome, advertisingValue.getDirectsoldIncome());
        this.accumulateDirectsoldEcpm =
            this.calculateEcpm(this.accumulateDirectsoldIncome, accumulateDirectsoldShowCount);

        this.accumulateCustomNetworkShowCount += advertisingValue.getCustomNetworkShowCount();
        this.accumulateCustomNetworkIncome =
            BigDecimalUtils.add(this.accumulateCustomNetworkIncome, advertisingValue.getCustomNetworkIncome());
        this.accumulateCustomNetworkEcpm =
            this.calculateEcpm(this.accumulateCustomNetworkIncome, accumulateCustomNetworkShowCount);
        this.accumulateCustomDirectsoldShowCount += advertisingValue.getCustomDirectsoldShowCount();
        this.accumulateCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateCustomDirectsoldIncome, advertisingValue.getCustomDirectsoldIncome());
        this.accumulateCustomDirectsoldEcpm =
            this.calculateEcpm(this.accumulateCustomDirectsoldIncome, accumulateCustomDirectsoldShowCount);
        this.accumulateBannerShowCount += advertisingValue.getBannerShowCount();
        this.accumulateBannerIncome =
            BigDecimalUtils.add(this.accumulateBannerIncome, advertisingValue.getBannerIncome());
        this.accumulateBannerEcpm = this.calculateEcpm(this.accumulateBannerIncome, accumulateBannerShowCount);
        this.accumulateBannerDirectsoldShowCount += advertisingValue.getBannerDirectsoldShowCount();
        this.accumulateBannerDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateBannerDirectsoldIncome, advertisingValue.getBannerDirectsoldIncome());
        this.accumulateBannerDirectsoldEcpm =
            this.calculateEcpm(this.accumulateBannerDirectsoldIncome, accumulateBannerDirectsoldShowCount);
        this.accumulateBannerExchangeShowCount += advertisingValue.getBannerExchangeShowCount();
        this.accumulateBannerExchangeIncome =
            BigDecimalUtils.add(this.accumulateBannerExchangeIncome, advertisingValue.getBannerExchangeIncome());
        this.accumulateBannerExchangeEcpm =
            this.calculateEcpm(this.accumulateBannerExchangeIncome, accumulateBannerExchangeShowCount);
        this.accumulateBannerNetworkShowCount += advertisingValue.getBannerNetworkShowCount();
        this.accumulateBannerNetworkIncome =
            BigDecimalUtils.add(this.accumulateBannerNetworkIncome, advertisingValue.getBannerNetworkIncome());
        this.accumulateBannerNetworkEcpm =
            this.calculateEcpm(this.accumulateBannerNetworkIncome, accumulateBannerNetworkShowCount);
        this.accumulateBannerCustomNetworkShowCount += advertisingValue.getBannerCustomNetworkShowCount();
        this.accumulateBannerCustomNetworkIncome = BigDecimalUtils.add(this.accumulateBannerCustomNetworkIncome,
            advertisingValue.getBannerCustomNetworkIncome());
        this.accumulateBannerCustomNetworkEcpm =
            this.calculateEcpm(this.accumulateBannerCustomNetworkIncome, accumulateBannerCustomNetworkShowCount);
        this.accumulateBannerMintegralBiddingShowCount += advertisingValue.getBannerMintegralBiddingShowCount();
        this.accumulateBannerMintegralBiddingIncome = BigDecimalUtils.add(this.accumulateBannerMintegralBiddingIncome,
            advertisingValue.getBannerMintegralBiddingIncome());
        this.accumulateBannerMintegralBiddingEcpm =
            this.calculateEcpm(this.accumulateBannerMintegralBiddingIncome, accumulateBannerMintegralBiddingShowCount);
        this.accumulateBannerNotCustomDirectsoldShowCount += advertisingValue.getBannerNotCustomDirectsoldShowCount();
        this.accumulateBannerNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateBannerNotCustomDirectsoldIncome,
                advertisingValue.getBannerNotCustomDirectsoldIncome());
        this.accumulateBannerNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.accumulateBannerNotCustomDirectsoldIncome,
                accumulateBannerNotCustomDirectsoldShowCount);
        this.accumulateInterShowCount += advertisingValue.getInterShowCount();
        this.accumulateInterIncome = BigDecimalUtils.add(this.accumulateInterIncome, advertisingValue.getInterIncome());
        this.accumulateInterEcpm = this.calculateEcpm(this.accumulateInterIncome, accumulateInterShowCount);
        this.accumulateInterDirectsoldShowCount += advertisingValue.getInterDirectsoldShowCount();
        this.accumulateInterDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateInterDirectsoldIncome, advertisingValue.getInterDirectsoldIncome());
        this.accumulateInterDirectsoldEcpm =
            this.calculateEcpm(this.accumulateInterDirectsoldIncome, accumulateInterDirectsoldShowCount);
        this.accumulateInterExchangeShowCount += advertisingValue.getInterExchangeShowCount();
        this.accumulateInterExchangeIncome =
            BigDecimalUtils.add(this.accumulateInterExchangeIncome, advertisingValue.getInterExchangeIncome());
        this.accumulateInterExchangeEcpm =
            this.calculateEcpm(this.accumulateInterExchangeIncome, accumulateInterExchangeShowCount);
        this.accumulateInterNetworkShowCount += advertisingValue.getInterNetworkShowCount();
        this.accumulateInterNetworkIncome =
            BigDecimalUtils.add(this.accumulateInterNetworkIncome, advertisingValue.getInterNetworkIncome());
        this.accumulateInterNetworkEcpm =
            this.calculateEcpm(this.accumulateInterNetworkIncome, accumulateInterNetworkShowCount);
        this.accumulateInterMintegralBiddingShowCount += advertisingValue.getInterMintegralBiddingShowCount();
        this.accumulateInterMintegralBiddingIncome = BigDecimalUtils.add(this.accumulateInterMintegralBiddingIncome,
            advertisingValue.getInterMintegralBiddingIncome());
        this.accumulateInterMintegralBiddingEcpm =
            this.calculateEcpm(this.accumulateInterMintegralBiddingIncome, accumulateInterMintegralBiddingShowCount);
        this.accumulateInterVungleBiddingShowCount += advertisingValue.getInterVungleBiddingShowCount();
        this.accumulateInterVungleBiddingIncome = BigDecimalUtils.add(this.accumulateInterVungleBiddingIncome,
            advertisingValue.getInterVungleBiddingIncome());
        this.accumulateInterVungleBiddingEcpm =
            this.calculateEcpm(this.accumulateInterVungleBiddingIncome, accumulateInterVungleBiddingShowCount);
        this.accumulateInterNotCustomDirectsoldShowCount += advertisingValue.getInterNotCustomDirectsoldShowCount();
        this.accumulateInterNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateInterNotCustomDirectsoldIncome,
                advertisingValue.getInterNotCustomDirectsoldIncome());
        this.accumulateInterNotCustomDirectsoldEcpm = this.calculateEcpm(this.accumulateInterNotCustomDirectsoldIncome,
            accumulateInterNotCustomDirectsoldShowCount);
        this.accumulateMrecShowCount += advertisingValue.getMrecShowCount();
        this.accumulateMrecIncome = BigDecimalUtils.add(this.accumulateMrecIncome, advertisingValue.getMrecIncome());
        this.accumulateMrecEcpm = this.calculateEcpm(this.accumulateMrecIncome, accumulateMrecShowCount);
        this.accumulateMrecDirectsoldShowCount += advertisingValue.getMrecDirectsoldShowCount();
        this.accumulateMrecDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateMrecDirectsoldIncome, advertisingValue.getMrecDirectsoldIncome());
        this.accumulateMrecDirectsoldEcpm =
            this.calculateEcpm(this.accumulateMrecDirectsoldIncome, accumulateMrecDirectsoldShowCount);
        this.accumulateMrecExchangeShowCount += advertisingValue.getMrecExchangeShowCount();
        this.accumulateMrecExchangeIncome =
            BigDecimalUtils.add(this.accumulateMrecExchangeIncome, advertisingValue.getMrecExchangeIncome());
        this.accumulateMrecExchangeEcpm =
            this.calculateEcpm(this.accumulateMrecExchangeIncome, accumulateMrecExchangeShowCount);
        this.accumulateMrecCustomNetworkShowCount += advertisingValue.getMrecCustomNetworkShowCount();
        this.accumulateMrecCustomNetworkIncome =
            BigDecimalUtils.add(this.accumulateMrecCustomNetworkIncome, advertisingValue.getMrecCustomNetworkIncome());
        this.accumulateMrecCustomNetworkEcpm =
            this.calculateEcpm(this.accumulateMrecCustomNetworkIncome, accumulateMrecCustomNetworkShowCount);
        this.accumulateMrecNotCustomDirectsoldShowCount += advertisingValue.getMrecNotCustomDirectsoldShowCount();
        this.accumulateMrecNotCustomDirectsoldIncome = BigDecimalUtils.add(this.accumulateMrecNotCustomDirectsoldIncome,
            advertisingValue.getMrecNotCustomDirectsoldIncome());
        this.accumulateMrecNotCustomDirectsoldEcpm = this.calculateEcpm(this.accumulateMrecNotCustomDirectsoldIncome,
            accumulateMrecNotCustomDirectsoldShowCount);
        this.accumulateRewardShowCount += advertisingValue.getRewardShowCount();
        this.accumulateRewardIncome =
            BigDecimalUtils.add(this.accumulateRewardIncome, advertisingValue.getRewardIncome());
        this.accumulateRewardEcpm = this.calculateEcpm(this.accumulateRewardIncome, accumulateRewardShowCount);
        this.accumulateRewardExchangeShowCount += advertisingValue.getRewardExchangeShowCount();
        this.accumulateRewardExchangeIncome =
            BigDecimalUtils.add(this.accumulateRewardExchangeIncome, advertisingValue.getRewardExchangeIncome());
        this.accumulateRewardExchangeEcpm =
            this.calculateEcpm(this.accumulateRewardExchangeIncome, accumulateRewardExchangeShowCount);
        this.accumulateRewardNetworkShowCount += advertisingValue.getRewardNetworkShowCount();
        this.accumulateRewardNetworkIncome =
            BigDecimalUtils.add(this.accumulateRewardNetworkIncome, advertisingValue.getRewardNetworkIncome());
        this.accumulateRewardNetworkEcpm =
            this.calculateEcpm(this.accumulateRewardNetworkIncome, accumulateRewardNetworkShowCount);
        this.accumulateRewardCustomNetworkShowCount += advertisingValue.getRewardCustomNetworkShowCount();
        this.accumulateRewardCustomNetworkIncome = BigDecimalUtils.add(this.accumulateRewardCustomNetworkIncome,
            advertisingValue.getRewardCustomNetworkIncome());
        this.accumulateRewardCustomNetworkEcpm =
            this.calculateEcpm(this.accumulateRewardCustomNetworkIncome, accumulateRewardCustomNetworkShowCount);
        this.accumulateRewardFacebookNetworkShowCount += advertisingValue.getRewardFacebookNetworkShowCount();
        this.accumulateRewardFacebookNetworkIncome = BigDecimalUtils.add(this.accumulateRewardFacebookNetworkIncome,
            advertisingValue.getRewardFacebookNetworkIncome());
        this.accumulateRewardFacebookNetworkEcpm =
            this.calculateEcpm(this.accumulateRewardFacebookNetworkIncome, accumulateRewardFacebookNetworkShowCount);
        this.accumulateRewardMintegralBiddingShowCount += advertisingValue.getRewardMintegralBiddingShowCount();
        this.accumulateRewardMintegralBiddingIncome = BigDecimalUtils.add(this.accumulateRewardMintegralBiddingIncome,
            advertisingValue.getRewardMintegralBiddingIncome());
        this.accumulateRewardMintegralBiddingEcpm =
            this.calculateEcpm(this.accumulateRewardMintegralBiddingIncome, accumulateRewardMintegralBiddingShowCount);
        this.accumulateRewardVungleBiddingShowCount += advertisingValue.getRewardVungleBiddingShowCount();
        this.accumulateRewardVungleBiddingIncome = BigDecimalUtils.add(this.accumulateRewardVungleBiddingIncome,
            advertisingValue.getRewardVungleBiddingIncome());
        this.accumulateRewardVungleBiddingEcpm =
            this.calculateEcpm(this.accumulateRewardVungleBiddingIncome, accumulateRewardVungleBiddingShowCount);
        this.accumulateRewardNotCustomDirectsoldShowCount += advertisingValue.getRewardNotCustomDirectsoldShowCount();
        this.accumulateRewardNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateRewardNotCustomDirectsoldIncome,
                advertisingValue.getRewardNotCustomDirectsoldIncome());
        this.accumulateRewardNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.accumulateRewardNotCustomDirectsoldIncome,
                accumulateRewardNotCustomDirectsoldShowCount);
    }

    protected void copyAdvertisingValue(PkgAccumulateAd advertisingValue) {
        this.totalShowCount = advertisingValue.getTotalShowCount();
        this.totalIncome = advertisingValue.getTotalIncome();
        this.totalEcpm = advertisingValue.getTotalEcpm();
        this.totalNotCustomDirectsoldShowCount = advertisingValue.getTotalNotCustomDirectsoldShowCount();
        this.totalNotCustomDirectsoldIncome = advertisingValue.getTotalNotCustomDirectsoldIncome();
        this.totalNotCustomDirectsoldEcpm = advertisingValue.getTotalNotCustomDirectsoldEcpm();
        this.directsoldShowCount = advertisingValue.getDirectsoldShowCount();
        this.directsoldIncome = advertisingValue.getDirectsoldIncome();
        this.directsoldEcpm = advertisingValue.getDirectsoldEcpm();
        this.customNetworkShowCount = advertisingValue.getCustomNetworkShowCount();
        this.customNetworkIncome = advertisingValue.getCustomNetworkIncome();
        this.customNetworkEcpm = advertisingValue.getCustomNetworkEcpm();
        this.customDirectsoldShowCount = advertisingValue.getCustomDirectsoldShowCount();
        this.customDirectsoldIncome = advertisingValue.getCustomDirectsoldIncome();
        this.customDirectsoldEcpm = advertisingValue.getCustomDirectsoldEcpm();
        this.bannerShowCount = advertisingValue.getBannerShowCount();
        this.bannerIncome = advertisingValue.getBannerIncome();
        this.bannerEcpm = advertisingValue.getBannerEcpm();
        this.bannerDirectsoldShowCount = advertisingValue.getBannerDirectsoldShowCount();
        this.bannerDirectsoldIncome = advertisingValue.getBannerDirectsoldIncome();
        this.bannerDirectsoldEcpm = advertisingValue.getBannerDirectsoldEcpm();
        this.bannerExchangeShowCount = advertisingValue.getBannerExchangeShowCount();
        this.bannerExchangeIncome = advertisingValue.getBannerExchangeIncome();
        this.bannerExchangeEcpm = advertisingValue.getBannerExchangeEcpm();
        this.bannerNetworkShowCount = advertisingValue.getBannerNetworkShowCount();
        this.bannerNetworkIncome = advertisingValue.getBannerNetworkIncome();
        this.bannerNetworkEcpm = advertisingValue.getBannerNetworkEcpm();
        this.bannerCustomNetworkShowCount = advertisingValue.getBannerCustomNetworkShowCount();
        this.bannerCustomNetworkIncome = advertisingValue.getBannerCustomNetworkIncome();
        this.bannerCustomNetworkEcpm = advertisingValue.getBannerCustomNetworkEcpm();
        this.bannerMintegralBiddingShowCount = advertisingValue.getBannerMintegralBiddingShowCount();
        this.bannerMintegralBiddingIncome = advertisingValue.getBannerMintegralBiddingIncome();
        this.bannerMintegralBiddingEcpm = advertisingValue.getBannerMintegralBiddingEcpm();
        this.bannerNotCustomDirectsoldShowCount = advertisingValue.getBannerNotCustomDirectsoldShowCount();
        this.bannerNotCustomDirectsoldIncome = advertisingValue.getBannerNotCustomDirectsoldIncome();
        this.bannerNotCustomDirectsoldEcpm = advertisingValue.getBannerNotCustomDirectsoldEcpm();
        this.interShowCount = advertisingValue.getInterShowCount();
        this.interIncome = advertisingValue.getInterIncome();
        this.interEcpm = advertisingValue.getInterEcpm();
        this.interDirectsoldShowCount = advertisingValue.getInterDirectsoldShowCount();
        this.interDirectsoldIncome = advertisingValue.getInterDirectsoldIncome();
        this.interDirectsoldEcpm = advertisingValue.getInterDirectsoldEcpm();
        this.interExchangeShowCount = advertisingValue.getInterExchangeShowCount();
        this.interExchangeIncome = advertisingValue.getInterExchangeIncome();
        this.interExchangeEcpm = advertisingValue.getInterExchangeEcpm();
        this.interNetworkShowCount = advertisingValue.getInterNetworkShowCount();
        this.interNetworkIncome = advertisingValue.getInterNetworkIncome();
        this.interNetworkEcpm = advertisingValue.getInterNetworkEcpm();
        this.interMintegralBiddingShowCount = advertisingValue.getInterMintegralBiddingShowCount();
        this.interMintegralBiddingIncome = advertisingValue.getInterMintegralBiddingIncome();
        this.interMintegralBiddingEcpm = advertisingValue.getInterMintegralBiddingEcpm();
        this.interVungleBiddingShowCount = advertisingValue.getInterVungleBiddingShowCount();
        this.interVungleBiddingIncome = advertisingValue.getInterVungleBiddingIncome();
        this.interVungleBiddingEcpm = advertisingValue.getInterVungleBiddingEcpm();
        this.interNotCustomDirectsoldShowCount = advertisingValue.getInterNotCustomDirectsoldShowCount();
        this.interNotCustomDirectsoldIncome = advertisingValue.getInterNotCustomDirectsoldIncome();
        this.interNotCustomDirectsoldEcpm = advertisingValue.getInterNotCustomDirectsoldEcpm();
        this.mrecShowCount = advertisingValue.getMrecShowCount();
        this.mrecIncome = advertisingValue.getMrecIncome();
        this.mrecEcpm = advertisingValue.getMrecEcpm();
        this.mrecDirectsoldShowCount = advertisingValue.getMrecDirectsoldShowCount();
        this.mrecDirectsoldIncome = advertisingValue.getMrecDirectsoldIncome();
        this.mrecDirectsoldEcpm = advertisingValue.getMrecDirectsoldEcpm();
        this.mrecExchangeShowCount = advertisingValue.getMrecExchangeShowCount();
        this.mrecExchangeIncome = advertisingValue.getMrecExchangeIncome();
        this.mrecExchangeEcpm = advertisingValue.getMrecExchangeEcpm();
        this.mrecCustomNetworkShowCount = advertisingValue.getMrecCustomNetworkShowCount();
        this.mrecCustomNetworkIncome = advertisingValue.getMrecCustomNetworkIncome();
        this.mrecCustomNetworkEcpm = advertisingValue.getMrecCustomNetworkEcpm();
        this.mrecNotCustomDirectsoldShowCount = advertisingValue.getMrecNotCustomDirectsoldShowCount();
        this.mrecNotCustomDirectsoldIncome = advertisingValue.getMrecNotCustomDirectsoldIncome();
        this.mrecNotCustomDirectsoldEcpm = advertisingValue.getMrecNotCustomDirectsoldEcpm();
        this.rewardShowCount = advertisingValue.getRewardShowCount();
        this.rewardIncome = advertisingValue.getRewardIncome();
        this.rewardEcpm = advertisingValue.getRewardEcpm();
        this.rewardExchangeShowCount = advertisingValue.getRewardExchangeShowCount();
        this.rewardExchangeIncome = advertisingValue.getRewardExchangeIncome();
        this.rewardExchangeEcpm = advertisingValue.getRewardExchangeEcpm();
        this.rewardNetworkShowCount = advertisingValue.getRewardNetworkShowCount();
        this.rewardNetworkIncome = advertisingValue.getRewardNetworkIncome();
        this.rewardNetworkEcpm = advertisingValue.getRewardNetworkEcpm();
        this.rewardCustomNetworkShowCount = advertisingValue.getRewardCustomNetworkShowCount();
        this.rewardCustomNetworkIncome = advertisingValue.getRewardCustomNetworkIncome();
        this.rewardCustomNetworkEcpm = advertisingValue.getRewardCustomNetworkEcpm();
        this.rewardFacebookNetworkShowCount = advertisingValue.getRewardFacebookNetworkShowCount();
        this.rewardFacebookNetworkIncome = advertisingValue.getRewardFacebookNetworkIncome();
        this.rewardFacebookNetworkEcpm = advertisingValue.getRewardFacebookNetworkEcpm();
        this.rewardMintegralBiddingShowCount = advertisingValue.getRewardMintegralBiddingShowCount();
        this.rewardMintegralBiddingIncome = advertisingValue.getRewardMintegralBiddingIncome();
        this.rewardMintegralBiddingEcpm = advertisingValue.getRewardMintegralBiddingEcpm();
        this.rewardVungleBiddingShowCount = advertisingValue.getRewardVungleBiddingShowCount();
        this.rewardVungleBiddingIncome = advertisingValue.getRewardVungleBiddingIncome();
        this.rewardVungleBiddingEcpm = advertisingValue.getRewardVungleBiddingEcpm();
        this.rewardNotCustomDirectsoldShowCount = advertisingValue.getRewardNotCustomDirectsoldShowCount();
        this.rewardNotCustomDirectsoldIncome = advertisingValue.getRewardNotCustomDirectsoldIncome();
        this.rewardNotCustomDirectsoldEcpm = advertisingValue.getRewardNotCustomDirectsoldEcpm();
    }

    protected void addAccumulate() {
        this.accumulateTotalShowCount += this.getTotalShowCount();
        this.accumulateTotalIncome = BigDecimalUtils.add(this.getAccumulateTotalIncome(), this.getTotalIncome());
        this.accumulateTotalEcpm = BigDecimalUtils.add(this.getAccumulateTotalEcpm(), this.getTotalEcpm());
        this.accumulateTotalNotCustomDirectsoldShowCount += this.getTotalNotCustomDirectsoldShowCount();
        this.accumulateTotalNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateTotalNotCustomDirectsoldIncome,
                this.getTotalNotCustomDirectsoldIncome());
        this.accumulateTotalNotCustomDirectsoldEcpm =
            BigDecimalUtils.add(this.accumulateTotalNotCustomDirectsoldEcpm, this.getTotalNotCustomDirectsoldEcpm());
        this.accumulateDirectsoldShowCount += this.getDirectsoldShowCount();
        this.accumulateDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateDirectsoldIncome, this.getDirectsoldIncome());
        this.accumulateDirectsoldEcpm = BigDecimalUtils.add(this.accumulateDirectsoldEcpm, this.getDirectsoldEcpm());
        this.accumulateCustomNetworkShowCount += this.getCustomNetworkShowCount();
        this.accumulateCustomNetworkIncome =
            BigDecimalUtils.add(this.accumulateCustomNetworkIncome, this.getCustomNetworkIncome());
        this.accumulateCustomNetworkEcpm =
            BigDecimalUtils.add(this.accumulateCustomNetworkEcpm, this.getCustomNetworkEcpm());
        this.accumulateCustomDirectsoldShowCount += this.getCustomDirectsoldShowCount();
        this.accumulateCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateCustomDirectsoldIncome, this.getCustomDirectsoldIncome());
        this.accumulateCustomDirectsoldEcpm =
            BigDecimalUtils.add(this.accumulateCustomDirectsoldEcpm, this.getCustomDirectsoldEcpm());
        this.accumulateBannerShowCount += this.getBannerShowCount();
        this.accumulateBannerIncome = BigDecimalUtils.add(this.accumulateBannerIncome, this.getBannerIncome());
        this.accumulateBannerEcpm = BigDecimalUtils.add(this.accumulateBannerEcpm, this.getBannerEcpm());
        this.accumulateBannerDirectsoldShowCount += this.getBannerDirectsoldShowCount();
        this.accumulateBannerDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateBannerDirectsoldIncome, this.getBannerDirectsoldIncome());
        this.accumulateBannerDirectsoldEcpm =
            BigDecimalUtils.add(this.accumulateBannerDirectsoldEcpm, this.getBannerDirectsoldEcpm());
        this.accumulateBannerExchangeShowCount += this.getBannerExchangeShowCount();
        this.accumulateBannerExchangeIncome =
            BigDecimalUtils.add(this.accumulateBannerExchangeIncome, this.getBannerExchangeIncome());
        this.accumulateBannerExchangeEcpm =
            BigDecimalUtils.add(this.accumulateBannerExchangeEcpm, this.getBannerExchangeEcpm());
        this.accumulateBannerNetworkShowCount += this.getBannerNetworkShowCount();
        this.accumulateBannerNetworkIncome =
            BigDecimalUtils.add(this.accumulateBannerNetworkIncome, this.getBannerNetworkIncome());
        this.accumulateBannerNetworkEcpm =
            BigDecimalUtils.add(this.accumulateBannerNetworkEcpm, this.getBannerNetworkEcpm());
        this.accumulateBannerCustomNetworkShowCount += this.getBannerCustomNetworkShowCount();
        this.accumulateBannerCustomNetworkIncome =
            BigDecimalUtils.add(this.accumulateBannerCustomNetworkIncome, this.getBannerCustomNetworkIncome());
        this.accumulateBannerCustomNetworkEcpm =
            BigDecimalUtils.add(this.accumulateBannerCustomNetworkEcpm, this.getBannerCustomNetworkEcpm());
        this.accumulateBannerMintegralBiddingShowCount += this.getBannerMintegralBiddingShowCount();
        this.accumulateBannerMintegralBiddingIncome =
            BigDecimalUtils.add(this.accumulateBannerMintegralBiddingIncome, this.getBannerMintegralBiddingIncome());
        this.accumulateBannerMintegralBiddingEcpm =
            BigDecimalUtils.add(this.accumulateBannerMintegralBiddingEcpm, this.getBannerMintegralBiddingEcpm());
        this.accumulateBannerNotCustomDirectsoldShowCount += this.getBannerNotCustomDirectsoldShowCount();
        this.accumulateBannerNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateBannerNotCustomDirectsoldIncome,
                this.getBannerNotCustomDirectsoldIncome());
        this.accumulateBannerNotCustomDirectsoldEcpm =
            BigDecimalUtils.add(this.accumulateBannerNotCustomDirectsoldEcpm, this.getBannerNotCustomDirectsoldEcpm());
        this.accumulateInterShowCount += this.getInterShowCount();
        this.accumulateInterIncome = BigDecimalUtils.add(this.accumulateInterIncome, this.getInterIncome());
        this.accumulateInterEcpm = BigDecimalUtils.add(this.accumulateInterEcpm, this.getInterEcpm());
        this.accumulateInterDirectsoldShowCount += this.getInterDirectsoldShowCount();
        this.accumulateInterDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateInterDirectsoldIncome, this.getInterDirectsoldIncome());
        this.accumulateInterDirectsoldEcpm =
            BigDecimalUtils.add(this.accumulateInterDirectsoldEcpm, this.getInterDirectsoldEcpm());
        this.accumulateInterExchangeShowCount += this.getInterExchangeShowCount();
        this.accumulateInterExchangeIncome =
            BigDecimalUtils.add(this.accumulateInterExchangeIncome, this.getInterExchangeIncome());
        this.accumulateInterExchangeEcpm =
            BigDecimalUtils.add(this.accumulateInterExchangeEcpm, this.getInterExchangeEcpm());
        this.accumulateInterNetworkShowCount += this.getInterNetworkShowCount();
        this.accumulateInterNetworkIncome =
            BigDecimalUtils.add(this.accumulateInterNetworkIncome, this.getInterNetworkIncome());
        this.accumulateInterNetworkEcpm =
            BigDecimalUtils.add(this.accumulateInterNetworkEcpm, this.getInterNetworkEcpm());
        this.accumulateInterMintegralBiddingShowCount += this.getInterMintegralBiddingShowCount();
        this.accumulateInterMintegralBiddingIncome =
            BigDecimalUtils.add(this.accumulateInterMintegralBiddingIncome, this.getInterMintegralBiddingIncome());
        this.accumulateInterMintegralBiddingEcpm =
            BigDecimalUtils.add(this.accumulateInterMintegralBiddingEcpm, this.getInterMintegralBiddingEcpm());
        this.accumulateInterVungleBiddingShowCount += this.getInterVungleBiddingShowCount();
        this.accumulateInterVungleBiddingIncome =
            BigDecimalUtils.add(this.accumulateInterVungleBiddingIncome, this.getInterVungleBiddingIncome());
        this.accumulateInterVungleBiddingEcpm =
            BigDecimalUtils.add(this.accumulateInterVungleBiddingEcpm, this.getInterVungleBiddingEcpm());
        this.accumulateInterNotCustomDirectsoldShowCount += this.getInterNotCustomDirectsoldShowCount();
        this.accumulateInterNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateInterNotCustomDirectsoldIncome,
                this.getInterNotCustomDirectsoldIncome());
        this.accumulateInterNotCustomDirectsoldEcpm =
            BigDecimalUtils.add(this.accumulateInterNotCustomDirectsoldEcpm, this.getInterNotCustomDirectsoldEcpm());
        this.accumulateMrecShowCount += this.getMrecShowCount();
        this.accumulateMrecIncome = BigDecimalUtils.add(this.accumulateMrecIncome, this.getMrecIncome());
        this.accumulateMrecEcpm = BigDecimalUtils.add(this.accumulateMrecEcpm, this.getMrecEcpm());
        this.accumulateMrecDirectsoldShowCount += this.getMrecDirectsoldShowCount();
        this.accumulateMrecDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateMrecDirectsoldIncome, this.getMrecDirectsoldIncome());
        this.accumulateMrecDirectsoldEcpm =
            BigDecimalUtils.add(this.accumulateMrecDirectsoldEcpm, this.getMrecDirectsoldEcpm());
        this.accumulateMrecExchangeShowCount += this.getMrecExchangeShowCount();
        this.accumulateMrecExchangeIncome =
            BigDecimalUtils.add(this.accumulateMrecExchangeIncome, this.getMrecExchangeIncome());
        this.accumulateMrecExchangeEcpm =
            BigDecimalUtils.add(this.accumulateMrecExchangeEcpm, this.getMrecExchangeEcpm());
        this.accumulateMrecCustomNetworkShowCount += this.getMrecCustomNetworkShowCount();
        this.accumulateMrecCustomNetworkIncome =
            BigDecimalUtils.add(this.accumulateMrecCustomNetworkIncome, this.getMrecCustomNetworkIncome());
        this.accumulateMrecCustomNetworkEcpm =
            BigDecimalUtils.add(this.accumulateMrecCustomNetworkEcpm, this.getMrecCustomNetworkEcpm());
        this.accumulateMrecNotCustomDirectsoldShowCount += this.getMrecNotCustomDirectsoldShowCount();
        this.accumulateMrecNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateMrecNotCustomDirectsoldIncome, this.getMrecNotCustomDirectsoldIncome());
        this.accumulateMrecNotCustomDirectsoldEcpm =
            BigDecimalUtils.add(this.accumulateMrecNotCustomDirectsoldEcpm, this.getMrecNotCustomDirectsoldEcpm());
        this.accumulateRewardShowCount += this.getRewardShowCount();
        this.accumulateRewardIncome = BigDecimalUtils.add(this.accumulateRewardIncome, this.getRewardIncome());
        this.accumulateRewardEcpm = BigDecimalUtils.add(this.accumulateRewardEcpm, this.getRewardEcpm());
        this.accumulateRewardExchangeShowCount += this.getRewardExchangeShowCount();
        this.accumulateRewardExchangeIncome =
            BigDecimalUtils.add(this.accumulateRewardExchangeIncome, this.getRewardExchangeIncome());
        this.accumulateRewardExchangeEcpm =
            BigDecimalUtils.add(this.accumulateRewardExchangeEcpm, this.getRewardExchangeEcpm());
        this.accumulateRewardNetworkShowCount += this.getRewardNetworkShowCount();
        this.accumulateRewardNetworkIncome =
            BigDecimalUtils.add(this.accumulateRewardNetworkIncome, this.getRewardNetworkIncome());
        this.accumulateRewardNetworkEcpm =
            BigDecimalUtils.add(this.accumulateRewardNetworkEcpm, this.getRewardNetworkEcpm());
        this.accumulateRewardCustomNetworkShowCount += this.getRewardCustomNetworkShowCount();
        this.accumulateRewardCustomNetworkIncome =
            BigDecimalUtils.add(this.accumulateRewardCustomNetworkIncome, this.getRewardCustomNetworkIncome());
        this.accumulateRewardCustomNetworkEcpm =
            BigDecimalUtils.add(this.accumulateRewardCustomNetworkEcpm, this.getRewardCustomNetworkEcpm());
        this.accumulateRewardFacebookNetworkShowCount += this.getRewardFacebookNetworkShowCount();
        this.accumulateRewardFacebookNetworkIncome =
            BigDecimalUtils.add(this.accumulateRewardFacebookNetworkIncome, this.getRewardFacebookNetworkIncome());
        this.accumulateRewardFacebookNetworkEcpm =
            BigDecimalUtils.add(this.accumulateRewardFacebookNetworkEcpm, this.getRewardFacebookNetworkEcpm());
        this.accumulateRewardMintegralBiddingShowCount += this.getRewardMintegralBiddingShowCount();
        this.accumulateRewardMintegralBiddingIncome =
            BigDecimalUtils.add(this.accumulateRewardMintegralBiddingIncome, this.getRewardMintegralBiddingIncome());
        this.accumulateRewardMintegralBiddingEcpm =
            BigDecimalUtils.add(this.accumulateRewardMintegralBiddingEcpm, this.getRewardMintegralBiddingEcpm());
        this.accumulateRewardVungleBiddingShowCount += this.getRewardVungleBiddingShowCount();
        this.accumulateRewardVungleBiddingIncome =
            BigDecimalUtils.add(this.accumulateRewardVungleBiddingIncome, this.getRewardVungleBiddingIncome());
        this.accumulateRewardVungleBiddingEcpm =
            BigDecimalUtils.add(this.accumulateRewardVungleBiddingEcpm, this.getRewardVungleBiddingEcpm());
        this.accumulateRewardNotCustomDirectsoldShowCount += this.getRewardNotCustomDirectsoldShowCount();
        this.accumulateRewardNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.accumulateRewardNotCustomDirectsoldIncome,
                this.getRewardNotCustomDirectsoldIncome());
        this.accumulateRewardNotCustomDirectsoldEcpm =
            BigDecimalUtils.add(this.accumulateRewardNotCustomDirectsoldEcpm, this.getRewardNotCustomDirectsoldEcpm());
    }
}
