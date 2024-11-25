package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * dws广告汇总统计，有新增的广告商就新增字段
 *
 * @TableName dws_daily_pkg_advertising
 */
@TableName(value = "dws_daily_pkg_advertising")
@Data
public class DwsDailyPkgAdvertising implements BaseEntity {
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
     * 包名
     */
    private String pkg;

    /**
     * 用户类型：0活跃用户、1新用户
     */
    private Integer userType;

    /**
     * 用户数
     */
    private Integer directsoldUserNum;

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
     * 用户数
     */
    private Integer customNetworkUserNum;

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
     * 用户数
     */
    private Integer customDirectsoldUserNum;

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
     * 用户数
     */
    private Integer bannerUserNum;

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
     * 用户数
     */
    private Integer bannerDirectsoldUserNum;

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
     * 用户数
     */
    private Integer bannerExchangeUserNum;

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
     * 用户数
     */
    private Integer bannerNetworkUserNum;

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
     * 用户数
     */
    private Integer bannerCustomNetworkUserNum;

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
     * 用户数
     */
    private Integer bannerMintegralBiddingUserNum;

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
     * 用户数
     */
    private Integer bannerNotCustomDirectsoldUserNum;

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
     * 用户数
     */
    private Integer interUserNum;

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
     * 用户数
     */
    private Integer interDirectsoldUserNum;

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
     * 用户数
     */
    private Integer interExchangeUserNum;

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
     * 用户数
     */
    private Integer interNetworkUserNum;

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
     * 用户数
     */
    private Integer interMintegralBiddingUserNum;

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
     * 用户数
     */
    private Integer interVungleBiddingUserNum;

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
     * 用户数
     */
    private Integer interNotCustomDirectsoldUserNum;

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
     * 用户数
     */
    private Integer mrecUserNum;

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
     * 用户数
     */
    private Integer mrecDirectsoldUserNum;

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
     * 用户数
     */
    private Integer mrecExchangeUserNum;

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
     * 用户数
     */
    private Integer mrecCustomNetworkUserNum;

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
     * 用户数
     */
    private Integer mrecNotCustomDirectsoldUserNum;

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
     * 用户数
     */
    private Integer rewardUserNum;

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
     * 用户数
     */
    private Integer rewardExchangeUserNum;

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
     * 用户数
     */
    private Integer rewardNetworkUserNum;

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
     * 用户数
     */
    private Integer rewardCustomNetworkUserNum;

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
     * 用户数
     */
    private Integer rewardFacebookNetworkUserNum;

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
     * 用户数
     */
    private Integer rewardMintegralBiddingUserNum;

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
     * 用户数
     */
    private Integer rewardVungleBiddingUserNum;

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
     * 用户数
     */
    private Integer rewardNotCustomDirectsoldUserNum;

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
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private Set<Long> directsoldUser;
    @TableField(exist = false)
    private Set<Long> customNetworkUser;
    @TableField(exist = false)
    private Set<Long> customDirectsoldUser;
    @TableField(exist = false)
    private Set<Long> bannerUser;
    @TableField(exist = false)
    private Set<Long> bannerDirectsoldUser;
    @TableField(exist = false)
    private Set<Long> bannerExchangeUser;
    @TableField(exist = false)
    private Set<Long> bannerNetworkUser;
    @TableField(exist = false)
    private Set<Long> bannerCustomNetworkUser;
    @TableField(exist = false)
    private Set<Long> bannerMintegralBiddingUser;
    @TableField(exist = false)
    private Set<Long> bannerNotCustomDirectsoldUser;
    @TableField(exist = false)
    private Set<Long> interUser;
    @TableField(exist = false)
    private Set<Long> interDirectsoldUser;
    @TableField(exist = false)
    private Set<Long> interExchangeUser;
    @TableField(exist = false)
    private Set<Long> interNetworkUser;
    @TableField(exist = false)
    private Set<Long> interVungleBiddingUser;
    @TableField(exist = false)
    private Set<Long> interMintegralBiddingUser;
    @TableField(exist = false)
    private Set<Long> interNotCustomDirectsoldUser;
    @TableField(exist = false)
    private Set<Long> mrecUser;
    @TableField(exist = false)
    private Set<Long> mrecDirectsoldUser;
    @TableField(exist = false)
    private Set<Long> mrecExchangeUser;
    @TableField(exist = false)
    private Set<Long> mrecCustomNetworkUser;
    @TableField(exist = false)
    private Set<Long> mrecNotCustomDirectsoldUser;
    @TableField(exist = false)
    private Set<Long> rewardUser;
    @TableField(exist = false)
    private Set<Long> rewardNotCustomDirectsoldUser;
    @TableField(exist = false)
    private Set<Long> rewardExchangeUser;
    @TableField(exist = false)
    private Set<Long> rewardNetworkUser;
    @TableField(exist = false)
    private Set<Long> rewardCustomNetworkUser;
    @TableField(exist = false)
    private Set<Long> rewardFacebookNetworkUser;
    @TableField(exist = false)
    private Set<Long> rewardMintegralBiddingUser;
    @TableField(exist = false)
    private Set<Long> rewardVungleBiddingUser;

    public DwsDailyPkgAdvertising() {
        this.directsoldUserNum = 0;
        this.directsoldShowCount = 0L;
        this.directsoldIncome = BigDecimal.ZERO;
        this.directsoldEcpm = BigDecimal.ZERO;
        this.customNetworkUserNum = 0;
        this.customNetworkShowCount = 0L;
        this.customNetworkIncome = BigDecimal.ZERO;
        this.customNetworkEcpm = BigDecimal.ZERO;
        this.customDirectsoldUserNum = 0;
        this.customDirectsoldShowCount = 0L;
        this.customDirectsoldIncome = BigDecimal.ZERO;
        this.customDirectsoldEcpm = BigDecimal.ZERO;
        this.bannerUserNum = 0;
        this.bannerShowCount = 0L;
        this.bannerIncome = BigDecimal.ZERO;
        this.bannerEcpm = BigDecimal.ZERO;
        this.bannerDirectsoldUserNum = 0;
        this.bannerDirectsoldShowCount = 0L;
        this.bannerDirectsoldIncome = BigDecimal.ZERO;
        this.bannerDirectsoldEcpm = BigDecimal.ZERO;
        this.bannerExchangeUserNum = 0;
        this.bannerExchangeShowCount = 0L;
        this.bannerExchangeIncome = BigDecimal.ZERO;
        this.bannerExchangeEcpm = BigDecimal.ZERO;
        this.bannerNetworkUserNum = 0;
        this.bannerNetworkShowCount = 0L;
        this.bannerNetworkIncome = BigDecimal.ZERO;
        this.bannerNetworkEcpm = BigDecimal.ZERO;
        this.bannerCustomNetworkUserNum = 0;
        this.bannerCustomNetworkShowCount = 0L;
        this.bannerCustomNetworkIncome = BigDecimal.ZERO;
        this.bannerCustomNetworkEcpm = BigDecimal.ZERO;
        this.bannerMintegralBiddingUserNum = 0;
        this.bannerMintegralBiddingShowCount = 0L;
        this.bannerMintegralBiddingIncome = BigDecimal.ZERO;
        this.bannerMintegralBiddingEcpm = BigDecimal.ZERO;
        this.bannerNotCustomDirectsoldUserNum = 0;
        this.bannerNotCustomDirectsoldShowCount = 0L;
        this.bannerNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.bannerNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.interUserNum = 0;
        this.interShowCount = 0L;
        this.interIncome = BigDecimal.ZERO;
        this.interEcpm = BigDecimal.ZERO;
        this.interDirectsoldUserNum = 0;
        this.interDirectsoldShowCount = 0L;
        this.interDirectsoldIncome = BigDecimal.ZERO;
        this.interDirectsoldEcpm = BigDecimal.ZERO;
        this.interExchangeUserNum = 0;
        this.interExchangeShowCount = 0L;
        this.interExchangeIncome = BigDecimal.ZERO;
        this.interExchangeEcpm = BigDecimal.ZERO;
        this.interNetworkUserNum = 0;
        this.interNetworkShowCount = 0L;
        this.interNetworkIncome = BigDecimal.ZERO;
        this.interNetworkEcpm = BigDecimal.ZERO;
        this.interMintegralBiddingUserNum = 0;
        this.interMintegralBiddingShowCount = 0L;
        this.interMintegralBiddingIncome = BigDecimal.ZERO;
        this.interMintegralBiddingEcpm = BigDecimal.ZERO;
        this.interVungleBiddingUserNum = 0;
        this.interVungleBiddingShowCount = 0L;
        this.interVungleBiddingIncome = BigDecimal.ZERO;
        this.interVungleBiddingEcpm = BigDecimal.ZERO;
        this.interNotCustomDirectsoldUserNum = 0;
        this.interNotCustomDirectsoldShowCount = 0L;
        this.interNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.interNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.mrecUserNum = 0;
        this.mrecShowCount = 0L;
        this.mrecIncome = BigDecimal.ZERO;
        this.mrecEcpm = BigDecimal.ZERO;
        this.mrecDirectsoldUserNum = 0;
        this.mrecDirectsoldShowCount = 0L;
        this.mrecDirectsoldIncome = BigDecimal.ZERO;
        this.mrecDirectsoldEcpm = BigDecimal.ZERO;
        this.mrecExchangeUserNum = 0;
        this.mrecExchangeShowCount = 0L;
        this.mrecExchangeIncome = BigDecimal.ZERO;
        this.mrecExchangeEcpm = BigDecimal.ZERO;
        this.mrecCustomNetworkUserNum = 0;
        this.mrecCustomNetworkShowCount = 0L;
        this.mrecCustomNetworkIncome = BigDecimal.ZERO;
        this.mrecCustomNetworkEcpm = BigDecimal.ZERO;
        this.mrecNotCustomDirectsoldUserNum = 0;
        this.mrecNotCustomDirectsoldShowCount = 0L;
        this.mrecNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.mrecNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.rewardUserNum = 0;
        this.rewardShowCount = 0L;
        this.rewardIncome = BigDecimal.ZERO;
        this.rewardEcpm = BigDecimal.ZERO;
        this.rewardExchangeUserNum = 0;
        this.rewardExchangeShowCount = 0L;
        this.rewardExchangeIncome = BigDecimal.ZERO;
        this.rewardExchangeEcpm = BigDecimal.ZERO;
        this.rewardNetworkUserNum = 0;
        this.rewardNetworkShowCount = 0L;
        this.rewardNetworkIncome = BigDecimal.ZERO;
        this.rewardNetworkEcpm = BigDecimal.ZERO;
        this.rewardCustomNetworkUserNum = 0;
        this.rewardCustomNetworkShowCount = 0L;
        this.rewardCustomNetworkIncome = BigDecimal.ZERO;
        this.rewardCustomNetworkEcpm = BigDecimal.ZERO;
        this.rewardFacebookNetworkUserNum = 0;
        this.rewardFacebookNetworkShowCount = 0L;
        this.rewardFacebookNetworkIncome = BigDecimal.ZERO;
        this.rewardFacebookNetworkEcpm = BigDecimal.ZERO;
        this.rewardMintegralBiddingUserNum = 0;
        this.rewardMintegralBiddingShowCount = 0L;
        this.rewardMintegralBiddingIncome = BigDecimal.ZERO;
        this.rewardMintegralBiddingEcpm = BigDecimal.ZERO;
        this.rewardVungleBiddingUserNum = 0;
        this.rewardVungleBiddingShowCount = 0L;
        this.rewardVungleBiddingIncome = BigDecimal.ZERO;
        this.rewardVungleBiddingEcpm = BigDecimal.ZERO;
        this.rewardNotCustomDirectsoldUserNum = 0;
        this.rewardNotCustomDirectsoldShowCount = 0L;
        this.rewardNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.rewardNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.createTime = new Date();
    }

    public static DwsDailyPkgAdvertising of(Integer dates, String pkg, Integer userType) {
        DwsDailyPkgAdvertising dwsDailyPkgAdvertising = new DwsDailyPkgAdvertising();
        dwsDailyPkgAdvertising.setDates(dates);
        dwsDailyPkgAdvertising.setPkg(pkg);
        dwsDailyPkgAdvertising.setUserType(userType);
        return dwsDailyPkgAdvertising;
    }

    /**
     * 初始化数据<br>
     * 各个ad平台的各个维度的数据汇总； <br>
     * 各个ad平台的数据汇总； <br>
     * 各个ad平台去除 DIRECTSOLD 、 CUSTOM_NETWORK 的汇总数据；<br>
     * 总平台汇总 DIRECTSOLD 的数据； <br>
     * 总平台汇总 CUSTOM_NETWORK 的数据；<br>
     * 总平台汇总 DIRECTSOLD 、 CUSTOM_NETWORK 的数据；
     *
     * @param dwdUserAdRecord 广告对象
     */
    public void calculate(DwdUserAdRecord dwdUserAdRecord) {
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
        // Inter
        handleInter(dwdUserAdRecord);
        handleInterNotCustomDirectsold(dwdUserAdRecord);
        handleInterDirectsold(dwdUserAdRecord);
        handleInterExchange(dwdUserAdRecord);
        handleInterNetwork(dwdUserAdRecord);
        handleInterMintegralBidding(dwdUserAdRecord);
        handleInterVungleBidding(dwdUserAdRecord);
        // Mrec
        handleMrec(dwdUserAdRecord);
        handleMrecNotCustomDirectsold(dwdUserAdRecord);
        handleMrecDirectsold(dwdUserAdRecord);
        handleMrecCustomNetwork(dwdUserAdRecord);
        handleMrecExchange(dwdUserAdRecord);
        // Reward
        handleReward(dwdUserAdRecord);
        handleRewardNotCustomDirectsold(dwdUserAdRecord);
        handleRewardFacebookNetwork(dwdUserAdRecord);
        handleRewardCustomNetwork(dwdUserAdRecord);
        handleRewardExchange(dwdUserAdRecord);
        handleRewardNetwork(dwdUserAdRecord);
        handleRewardMintegralBidding(dwdUserAdRecord);
        handleRewardVungleBidding(dwdUserAdRecord);
    }

    private void handleDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isApplovinDirectsold()) {
            return;
        }
        if (CollectionUtils.isEmpty(this.directsoldUser)) {
            this.directsoldUser = new HashSet<>();
        }
        this.directsoldUser.add(dwdUserAdRecord.getUserId());
        this.directsoldUserNum = this.directsoldUser.size();
        this.directsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.directsoldIncome = BigDecimalUtils.add(this.directsoldIncome, dwdUserAdRecord.getRevenue());
        this.directsoldEcpm = calculateEcpm(this.directsoldIncome, this.directsoldShowCount);
    }

    private void handleCustomNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isCustomNetworkSdk()) {
            return;
        }
        if (CollectionUtils.isEmpty(this.customNetworkUser)) {
            this.customNetworkUser = new HashSet<>();
        }
        this.customNetworkUser.add(dwdUserAdRecord.getUserId());
        this.customNetworkUserNum = this.customNetworkUser.size();
        this.customNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.customNetworkIncome = BigDecimalUtils.add(this.customNetworkIncome, dwdUserAdRecord.getRevenue());
        this.customNetworkEcpm = this.calculateEcpm(this.customNetworkIncome, this.customNetworkShowCount);
    }

    private void handleCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isApplovinDirectsold() && dwdUserAdRecord.isCustomNetworkSdk())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.customDirectsoldUser)) {
            this.customDirectsoldUser = new HashSet<>();
        }
        this.customDirectsoldUser.add(dwdUserAdRecord.getUserId());
        this.customDirectsoldUserNum = this.customDirectsoldUser.size();
        this.customDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.customDirectsoldIncome = BigDecimalUtils.add(this.customDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.customDirectsoldEcpm = this.calculateEcpm(this.customDirectsoldIncome, this.customDirectsoldShowCount);
    }

    private void handleBanner(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isBanner()) {
            return;
        }
        if (CollectionUtils.isEmpty(this.bannerUser)) {
            this.bannerUser = new HashSet<>();
        }
        this.bannerUser.add(dwdUserAdRecord.getUserId());
        this.bannerUserNum = this.bannerUser.size();
        this.bannerShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerIncome = BigDecimalUtils.add(this.bannerIncome, dwdUserAdRecord.getRevenue());
        this.bannerEcpm = this.calculateEcpm(this.bannerIncome, this.bannerShowCount);
    }

    private void handleBannerNotCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isBanner()) {
            return;
        }

        if (dwdUserAdRecord.isApplovinDirectsold() || dwdUserAdRecord.isCustomNetworkSdk()) {
            return;
        }
        if (CollectionUtils.isEmpty(this.bannerNotCustomDirectsoldUser)) {
            this.bannerNotCustomDirectsoldUser = new HashSet<>();
        }
        this.bannerNotCustomDirectsoldUser.add(dwdUserAdRecord.getUserId());
        this.bannerNotCustomDirectsoldUserNum = this.bannerNotCustomDirectsoldUser.size();
        this.bannerNotCustomDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.bannerNotCustomDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.bannerNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.bannerNotCustomDirectsoldIncome, this.bannerNotCustomDirectsoldShowCount);
    }

    private void handleBannerDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isBanner() && dwdUserAdRecord.isApplovinDirectsold())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.bannerDirectsoldUser)) {
            this.bannerDirectsoldUser = new HashSet<>();
        }
        this.bannerDirectsoldUser.add(dwdUserAdRecord.getUserId());
        this.bannerDirectsoldUserNum = this.bannerDirectsoldUser.size();
        this.bannerDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerDirectsoldIncome = BigDecimalUtils.add(this.bannerDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.bannerDirectsoldEcpm = this.calculateEcpm(this.bannerDirectsoldIncome, this.bannerDirectsoldShowCount);
    }

    private void handleBannerCustomNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isBanner() && dwdUserAdRecord.isCustomNetworkSdk())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.bannerCustomNetworkUser)) {
            this.bannerCustomNetworkUser = new HashSet<>();
        }
        this.bannerCustomNetworkUser.add(dwdUserAdRecord.getUserId());
        this.bannerCustomNetworkUserNum = this.bannerCustomNetworkUser.size();
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
        if (CollectionUtils.isEmpty(this.bannerExchangeUser)) {
            this.bannerExchangeUser = new HashSet<>();
        }
        this.bannerExchangeUser.add(dwdUserAdRecord.getUserId());
        this.bannerExchangeUserNum = this.bannerExchangeUser.size();
        this.bannerExchangeShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerExchangeIncome = BigDecimalUtils.add(this.bannerExchangeIncome, dwdUserAdRecord.getRevenue());
        this.bannerExchangeEcpm = this.calculateEcpm(this.bannerExchangeIncome, this.bannerExchangeShowCount);
    }

    private void handleBannerNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isBanner() && dwdUserAdRecord.isApplovinNetwork())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.bannerNetworkUser)) {
            this.bannerNetworkUser = new HashSet<>();
        }
        this.bannerNetworkUser.add(dwdUserAdRecord.getUserId());
        this.bannerNetworkUserNum = this.bannerNetworkUser.size();
        this.bannerNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.bannerNetworkIncome = BigDecimalUtils.add(this.bannerNetworkIncome, dwdUserAdRecord.getRevenue());
        this.bannerNetworkEcpm = this.calculateEcpm(this.bannerNetworkIncome, this.bannerNetworkShowCount);
    }

    private void handleBannerMintegralBidding(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isBanner() && dwdUserAdRecord.isMintegralBidding())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.bannerMintegralBiddingUser)) {
            this.bannerMintegralBiddingUser = new HashSet<>();
        }
        this.bannerMintegralBiddingUser.add(dwdUserAdRecord.getUserId());
        this.bannerMintegralBiddingUserNum = this.bannerMintegralBiddingUser.size();
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
        if (CollectionUtils.isEmpty(this.interUser)) {
            this.interUser = new HashSet<>();
        }
        this.interUser.add(dwdUserAdRecord.getUserId());
        this.interUserNum = this.interUser.size();
        this.interShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interIncome = BigDecimalUtils.add(this.interIncome, dwdUserAdRecord.getRevenue());
        this.interEcpm = this.calculateEcpm(this.interIncome, this.interShowCount);
    }

    private void handleInterNotCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isInter()) {
            return;
        }

        if (dwdUserAdRecord.isApplovinDirectsold() || dwdUserAdRecord.isCustomNetworkSdk()) {
            return;
        }
        if (CollectionUtils.isEmpty(this.interNotCustomDirectsoldUser)) {
            this.interNotCustomDirectsoldUser = new HashSet<>();
        }
        this.interNotCustomDirectsoldUser.add(dwdUserAdRecord.getUserId());
        this.interNotCustomDirectsoldUserNum = this.interNotCustomDirectsoldUser.size();
        this.interNotCustomDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.interNotCustomDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.interNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.interNotCustomDirectsoldIncome, this.interNotCustomDirectsoldShowCount);
    }

    private void handleInterDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isInter() && dwdUserAdRecord.isApplovinDirectsold())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.interDirectsoldUser)) {
            this.interDirectsoldUser = new HashSet<>();
        }
        this.interDirectsoldUser.add(dwdUserAdRecord.getUserId());
        this.interDirectsoldUserNum = this.interDirectsoldUser.size();
        this.interDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interDirectsoldIncome = BigDecimalUtils.add(this.interDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.interDirectsoldEcpm = this.calculateEcpm(this.interDirectsoldIncome, this.interDirectsoldShowCount);
    }

    private void handleInterExchange(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isInter() && dwdUserAdRecord.isApplovinExchange())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.interExchangeUser)) {
            this.interExchangeUser = new HashSet<>();
        }
        this.interExchangeUser.add(dwdUserAdRecord.getUserId());
        this.interExchangeUserNum = this.interExchangeUser.size();
        this.interExchangeShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interExchangeIncome = BigDecimalUtils.add(this.interExchangeIncome, dwdUserAdRecord.getRevenue());
        this.interExchangeEcpm = this.calculateEcpm(this.interExchangeIncome, this.interExchangeShowCount);
    }

    private void handleInterNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isInter() && dwdUserAdRecord.isApplovinNetwork())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.interNetworkUser)) {
            this.interNetworkUser = new HashSet<>();
        }
        this.interNetworkUser.add(dwdUserAdRecord.getUserId());
        this.interNetworkUserNum = this.interNetworkUser.size();
        this.interNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.interNetworkIncome = BigDecimalUtils.add(this.interNetworkIncome, dwdUserAdRecord.getRevenue());
        this.interNetworkEcpm = this.calculateEcpm(this.interNetworkIncome, this.interNetworkShowCount);
    }

    private void handleInterVungleBidding(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isInter() && dwdUserAdRecord.isVungleBidding())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.interVungleBiddingUser)) {
            this.interVungleBiddingUser = new HashSet<>();
        }
        this.interVungleBiddingUser.add(dwdUserAdRecord.getUserId());
        this.interVungleBiddingUserNum = this.interVungleBiddingUser.size();
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
        if (CollectionUtils.isEmpty(this.interMintegralBiddingUser)) {
            this.interMintegralBiddingUser = new HashSet<>();
        }
        this.interMintegralBiddingUser.add(dwdUserAdRecord.getUserId());
        this.interMintegralBiddingUserNum = this.interMintegralBiddingUser.size();
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
        if (CollectionUtils.isEmpty(this.mrecUser)) {
            this.mrecUser = new HashSet<>();
        }
        this.mrecUser.add(dwdUserAdRecord.getUserId());
        this.mrecUserNum = this.mrecUser.size();
        this.mrecShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecIncome = BigDecimalUtils.add(this.mrecIncome, dwdUserAdRecord.getRevenue());
        this.mrecEcpm = this.calculateEcpm(this.mrecIncome, this.mrecShowCount);
    }

    private void handleMrecNotCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isMrec()) {
            return;
        }

        if (dwdUserAdRecord.isApplovinDirectsold() || dwdUserAdRecord.isCustomNetworkSdk()) {
            return;
        }
        if (CollectionUtils.isEmpty(this.mrecNotCustomDirectsoldUser)) {
            this.mrecNotCustomDirectsoldUser = new HashSet<>();
        }
        this.mrecNotCustomDirectsoldUser.add(dwdUserAdRecord.getUserId());
        this.mrecNotCustomDirectsoldUserNum = this.mrecNotCustomDirectsoldUser.size();
        this.mrecNotCustomDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.mrecNotCustomDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.mrecNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.mrecNotCustomDirectsoldIncome, this.mrecNotCustomDirectsoldShowCount);
    }

    private void handleMrecDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isMrec() && dwdUserAdRecord.isApplovinDirectsold())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.mrecDirectsoldUser)) {
            this.mrecDirectsoldUser = new HashSet<>();
        }
        this.mrecDirectsoldUser.add(dwdUserAdRecord.getUserId());
        this.mrecDirectsoldUserNum = this.mrecDirectsoldUser.size();
        this.mrecDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecDirectsoldIncome = BigDecimalUtils.add(this.mrecDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.mrecDirectsoldEcpm = this.calculateEcpm(this.mrecDirectsoldIncome, this.mrecDirectsoldShowCount);
    }

    private void handleMrecCustomNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isMrec() && dwdUserAdRecord.isCustomNetworkSdk())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.mrecCustomNetworkUser)) {
            this.mrecCustomNetworkUser = new HashSet<>();
        }
        this.mrecCustomNetworkUser.add(dwdUserAdRecord.getUserId());
        this.mrecCustomNetworkUserNum = this.mrecCustomNetworkUser.size();
        this.mrecCustomNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecCustomNetworkIncome = BigDecimalUtils.add(this.mrecCustomNetworkIncome, dwdUserAdRecord.getRevenue());
        this.mrecCustomNetworkEcpm = this.calculateEcpm(this.mrecCustomNetworkIncome, this.mrecCustomNetworkShowCount);
    }

    private void handleMrecExchange(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isMrec() && dwdUserAdRecord.isApplovinExchange())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.mrecExchangeUser)) {
            this.mrecExchangeUser = new HashSet<>();
        }
        this.mrecExchangeUser.add(dwdUserAdRecord.getUserId());
        this.mrecExchangeUserNum = this.mrecExchangeUser.size();
        this.mrecExchangeShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.mrecExchangeIncome = BigDecimalUtils.add(this.mrecExchangeIncome, dwdUserAdRecord.getRevenue());
        this.mrecExchangeEcpm = this.calculateEcpm(this.mrecExchangeIncome, this.mrecExchangeShowCount);
    }

    private void handleReward(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isReward()) {
            return;
        }
        if (CollectionUtils.isEmpty(this.rewardUser)) {
            this.rewardUser = new HashSet<>();
        }
        this.rewardUser.add(dwdUserAdRecord.getUserId());
        this.rewardUserNum = this.rewardUser.size();
        this.rewardShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardIncome = BigDecimalUtils.add(this.rewardIncome, dwdUserAdRecord.getRevenue());
        this.rewardEcpm = this.calculateEcpm(this.rewardIncome, this.rewardShowCount);
    }

    private void handleRewardNotCustomDirectsold(DwdUserAdRecord dwdUserAdRecord) {
        if (!dwdUserAdRecord.isReward()) {
            return;
        }

        if (dwdUserAdRecord.isApplovinDirectsold() || dwdUserAdRecord.isCustomNetworkSdk()) {
            return;
        }
        if (CollectionUtils.isEmpty(this.rewardNotCustomDirectsoldUser)) {
            this.rewardNotCustomDirectsoldUser = new HashSet<>();
        }
        this.rewardNotCustomDirectsoldUser.add(dwdUserAdRecord.getUserId());
        this.rewardNotCustomDirectsoldUserNum = this.rewardNotCustomDirectsoldUser.size();
        this.rewardNotCustomDirectsoldShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardNotCustomDirectsoldIncome =
            BigDecimalUtils.add(this.rewardNotCustomDirectsoldIncome, dwdUserAdRecord.getRevenue());
        this.rewardNotCustomDirectsoldEcpm =
            this.calculateEcpm(this.rewardNotCustomDirectsoldIncome, this.rewardNotCustomDirectsoldShowCount);
    }

    private void handleRewardFacebookNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isReward() && dwdUserAdRecord.isFacebookNetwork())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.rewardFacebookNetworkUser)) {
            this.rewardFacebookNetworkUser = new HashSet<>();
        }
        this.rewardFacebookNetworkUser.add(dwdUserAdRecord.getUserId());
        this.rewardFacebookNetworkUserNum = this.rewardFacebookNetworkUser.size();
        this.rewardFacebookNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardFacebookNetworkIncome =
            BigDecimalUtils.add(this.rewardFacebookNetworkIncome, dwdUserAdRecord.getRevenue());
        this.rewardFacebookNetworkEcpm =
            this.calculateEcpm(this.rewardFacebookNetworkIncome, this.rewardFacebookNetworkShowCount);
    }

    private void handleRewardCustomNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isReward() && dwdUserAdRecord.isCustomNetworkSdk())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.rewardCustomNetworkUser)) {
            this.rewardCustomNetworkUser = new HashSet<>();
        }
        this.rewardCustomNetworkUser.add(dwdUserAdRecord.getUserId());
        this.rewardCustomNetworkUserNum = this.rewardCustomNetworkUser.size();
        this.rewardCustomNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardCustomNetworkIncome =
            BigDecimalUtils.add(this.rewardCustomNetworkIncome, dwdUserAdRecord.getRevenue());
        this.rewardCustomNetworkEcpm =
            this.calculateEcpm(this.rewardCustomNetworkIncome, this.rewardCustomNetworkShowCount);
    }

    private void handleRewardExchange(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isReward() && dwdUserAdRecord.isApplovinExchange())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.rewardExchangeUser)) {
            this.rewardExchangeUser = new HashSet<>();
        }
        this.rewardExchangeUser.add(dwdUserAdRecord.getUserId());
        this.rewardExchangeUserNum = this.rewardExchangeUser.size();
        this.rewardExchangeShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardExchangeIncome = BigDecimalUtils.add(this.rewardExchangeIncome, dwdUserAdRecord.getRevenue());
        this.rewardExchangeEcpm = this.calculateEcpm(this.rewardExchangeIncome, this.rewardExchangeShowCount);
    }

    private void handleRewardNetwork(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isReward() && dwdUserAdRecord.isApplovinNetwork())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.rewardNetworkUser)) {
            this.rewardNetworkUser = new HashSet<>();
        }
        this.rewardNetworkUser.add(dwdUserAdRecord.getUserId());
        this.rewardNetworkUserNum = this.rewardNetworkUser.size();
        this.rewardNetworkShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardNetworkIncome = BigDecimalUtils.add(this.rewardNetworkIncome, dwdUserAdRecord.getRevenue());
        this.rewardNetworkEcpm = this.calculateEcpm(this.rewardNetworkIncome, this.rewardNetworkShowCount);
    }

    private void handleRewardMintegralBidding(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isReward() && dwdUserAdRecord.isMintegralBidding())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.rewardMintegralBiddingUser)) {
            this.rewardMintegralBiddingUser = new HashSet<>();
        }
        this.rewardMintegralBiddingUser.add(dwdUserAdRecord.getUserId());
        this.rewardMintegralBiddingUserNum = this.rewardMintegralBiddingUser.size();
        this.rewardMintegralBiddingShowCount += LongUtils.getDefault(dwdUserAdRecord.getAdExhibit());
        this.rewardMintegralBiddingIncome =
            BigDecimalUtils.add(this.rewardMintegralBiddingIncome, dwdUserAdRecord.getRevenue());
        this.rewardMintegralBiddingEcpm =
            this.calculateEcpm(this.rewardMintegralBiddingIncome, this.rewardMintegralBiddingShowCount);
    }

    private void handleRewardVungleBidding(DwdUserAdRecord dwdUserAdRecord) {
        if (!(dwdUserAdRecord.isReward() && dwdUserAdRecord.isVungleBidding())) {
            return;
        }
        if (CollectionUtils.isEmpty(this.rewardVungleBiddingUser)) {
            this.rewardVungleBiddingUser = new HashSet<>();
        }
        this.rewardVungleBiddingUser.add(dwdUserAdRecord.getUserId());
        this.rewardVungleBiddingUserNum = this.rewardVungleBiddingUser.size();
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

}
