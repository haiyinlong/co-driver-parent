package com.leo.ad.codriver.dws.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * dws注册日期90天广告汇总统计
 *
 * @TableName dws_register_90_days_accumulate_pkg_ver_usrc_ad
 */
@TableName(value = "dws_register_90_days_accumulate_pkg_ver_usrc_ad")
@Data
public class DwsRegister90DaysAccumulatePkgVerUsrcAd implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

    /**
     * 注册日期
     */
    private Integer registerDates;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 提现时注册天（自然天）
     */
    private Integer registerDay;

    /**
     * 展示次数
     */
    private Integer totalShowCount;

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
    private Integer totalNotCustomDirectsoldShowCount;

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
    private Integer directsoldShowCount;

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
    private Integer customNetworkShowCount;

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
    private Integer customDirectsoldShowCount;

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
    private Integer bannerShowCount;

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
    private Integer bannerDirectsoldShowCount;

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
    private Integer bannerExchangeShowCount;

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
    private Integer bannerNetworkShowCount;

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
    private Integer bannerCustomNetworkShowCount;

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
    private Integer bannerMintegralBiddingShowCount;

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
    private Integer bannerNotCustomDirectsoldShowCount;

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
    private Integer interShowCount;

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
    private Integer interDirectsoldShowCount;

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
    private Integer interExchangeShowCount;

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
    private Integer interNetworkShowCount;

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
    private Integer interMintegralBiddingShowCount;

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
    private Integer interVungleBiddingShowCount;

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
    private Integer interNotCustomDirectsoldShowCount;

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
    private Integer mrecShowCount;

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
    private Integer mrecDirectsoldShowCount;

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
    private Integer mrecExchangeShowCount;

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
    private Integer mrecCustomNetworkShowCount;

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
    private Integer mrecNotCustomDirectsoldShowCount;

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
    private Integer rewardShowCount;

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
    private Integer rewardExchangeShowCount;

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
    private Integer rewardNetworkShowCount;

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
    private Integer rewardCustomNetworkShowCount;

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
    private Integer rewardFacebookNetworkShowCount;

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
    private Integer rewardMintegralBiddingShowCount;

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
    private Integer rewardVungleBiddingShowCount;

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
    private Integer rewardNotCustomDirectsoldShowCount;

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
    private Integer accumulateTotalShowCount;

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
    private Integer accumulateTotalNotCustomDirectsoldShowCount;

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
    private Integer accumulateDirectsoldShowCount;

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
    private Integer accumulateCustomNetworkShowCount;

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
    private Integer accumulateCustomDirectsoldShowCount;

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
    private Integer accumulateBannerShowCount;

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
    private Integer accumulateBannerDirectsoldShowCount;

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
    private Integer accumulateBannerExchangeShowCount;

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
    private Integer accumulateBannerNetworkShowCount;

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
    private Integer accumulateBannerCustomNetworkShowCount;

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
    private Integer accumulateBannerMintegralBiddingShowCount;

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
    private Integer accumulateBannerNotCustomDirectsoldShowCount;

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
    private Integer accumulateInterShowCount;

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
    private Integer accumulateInterDirectsoldShowCount;

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
    private Integer accumulateInterExchangeShowCount;

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
    private Integer accumulateInterNetworkShowCount;

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
    private Integer accumulateInterMintegralBiddingShowCount;

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
    private Integer accumulateInterVungleBiddingShowCount;

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
    private Integer accumulateInterNotCustomDirectsoldShowCount;

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
    private Integer accumulateMrecShowCount;

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
    private Integer accumulateMrecDirectsoldShowCount;

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
    private Integer accumulateMrecExchangeShowCount;

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
    private Integer accumulateMrecCustomNetworkShowCount;

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
    private Integer accumulateMrecNotCustomDirectsoldShowCount;

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
    private Integer accumulateRewardShowCount;

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
    private Integer accumulateRewardExchangeShowCount;

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
    private Integer accumulateRewardNetworkShowCount;

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
    private Integer accumulateRewardCustomNetworkShowCount;

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
    private Integer accumulateRewardFacebookNetworkShowCount;

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
    private Integer accumulateRewardMintegralBiddingShowCount;

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
    private Integer accumulateRewardVungleBiddingShowCount;

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
    private Integer accumulateRewardNotCustomDirectsoldShowCount;

    /**
     * 广告收入
     */
    private BigDecimal accumulateRewardNotCustomDirectsoldIncome;

    /**
     * ecpm
     */
    private BigDecimal accumulateRewardNotCustomDirectsoldEcpm;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
