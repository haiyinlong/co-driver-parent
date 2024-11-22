package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.dws.dto.PkgVerAdDTO;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * dws广告汇总统计，有新增的广告商就新增字段
 *
 * @TableName dws_daily_pkg_ver_advertising
 */
@TableName(value = "dws_daily_pkg_ver_advertising")
@Data
public class DwsDailyPkgVerAdvertising implements BaseEntity {
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
     * 应用版本
     */
    private String version;

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
     * 用户数
     */
    private Integer customNetworkUserNum;

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
     * 用户数
     */
    private Integer customDirectsoldUserNum;

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
     * 用户数
     */
    private Integer bannerUserNum;

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
     * 用户数
     */
    private Integer bannerDirectsoldUserNum;

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
     * 用户数
     */
    private Integer bannerExchangeUserNum;

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
     * 用户数
     */
    private Integer bannerNetworkUserNum;

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
     * 用户数
     */
    private Integer bannerCustomNetworkUserNum;

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
     * 用户数
     */
    private Integer bannerMintegralBiddingUserNum;

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
     * 用户数
     */
    private Integer bannerNotCustomDirectsoldUserNum;

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
     * 用户数
     */
    private Integer interUserNum;

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
     * 用户数
     */
    private Integer interDirectsoldUserNum;

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
     * 用户数
     */
    private Integer interExchangeUserNum;

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
     * 用户数
     */
    private Integer interNetworkUserNum;

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
     * 用户数
     */
    private Integer interMintegralBiddingUserNum;

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
     * 用户数
     */
    private Integer interVungleBiddingUserNum;

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
     * 用户数
     */
    private Integer interNotCustomDirectsoldUserNum;

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
     * 用户数
     */
    private Integer mreUserNum;

    /**
     * 展示次数
     */
    private Integer mreShowCount;

    /**
     * 广告收入
     */
    private BigDecimal mreIncome;

    /**
     * ecpm
     */
    private BigDecimal mreEcpm;

    /**
     * 用户数
     */
    private Integer mrecDirectsoldUserNum;

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
     * 用户数
     */
    private Integer mrecExchangeUserNum;

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
     * 用户数
     */
    private Integer mrecCustomNetworkUserNum;

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
     * 用户数
     */
    private Integer mrecNotCustomDirectsoldUserNum;

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
     * 用户数
     */
    private Integer rewardUserNum;

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
     * 用户数
     */
    private Integer rewardExchangeUserNum;

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
     * 用户数
     */
    private Integer rewardNetworkUserNum;

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
     * 用户数
     */
    private Integer rewardCustomNetworkUserNum;

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
     * 用户数
     */
    private Integer rewardFacebookNetworkUserNum;

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
     * 用户数
     */
    private Integer rewardMintegralBiddingUserNum;

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
     * 用户数
     */
    private Integer rewardVungleBiddingUserNum;

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
     * 用户数
     */
    private Integer rewardNotCustomDirectsoldUserNum;

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
     * 创建时间
     */
    private Date createTime;

    public DwsDailyPkgVerAdvertising() {
        this.directsoldUserNum = 0;
        this.directsoldShowCount = 0;
        this.directsoldIncome = BigDecimal.ZERO;
        this.directsoldEcpm = BigDecimal.ZERO;
        this.customNetworkUserNum = 0;
        this.customNetworkShowCount = 0;
        this.customNetworkIncome = BigDecimal.ZERO;
        this.customNetworkEcpm = BigDecimal.ZERO;
        this.customDirectsoldUserNum = 0;
        this.customDirectsoldShowCount = 0;
        this.customDirectsoldIncome = BigDecimal.ZERO;
        this.customDirectsoldEcpm = BigDecimal.ZERO;
        this.bannerUserNum = 0;
        this.bannerShowCount = 0;
        this.bannerIncome = BigDecimal.ZERO;
        this.bannerEcpm = BigDecimal.ZERO;
        this.bannerDirectsoldUserNum = 0;
        this.bannerDirectsoldShowCount = 0;
        this.bannerDirectsoldIncome = BigDecimal.ZERO;
        this.bannerDirectsoldEcpm = BigDecimal.ZERO;
        this.bannerExchangeUserNum = 0;
        this.bannerExchangeShowCount = 0;
        this.bannerExchangeIncome = BigDecimal.ZERO;
        this.bannerExchangeEcpm = BigDecimal.ZERO;
        this.bannerNetworkUserNum = 0;
        this.bannerNetworkShowCount = 0;
        this.bannerNetworkIncome = BigDecimal.ZERO;
        this.bannerNetworkEcpm = BigDecimal.ZERO;
        this.bannerCustomNetworkUserNum = 0;
        this.bannerCustomNetworkShowCount = 0;
        this.bannerCustomNetworkIncome = BigDecimal.ZERO;
        this.bannerCustomNetworkEcpm = BigDecimal.ZERO;
        this.bannerMintegralBiddingUserNum = 0;
        this.bannerMintegralBiddingShowCount = 0;
        this.bannerMintegralBiddingIncome = BigDecimal.ZERO;
        this.bannerMintegralBiddingEcpm = BigDecimal.ZERO;
        this.bannerNotCustomDirectsoldUserNum = 0;
        this.bannerNotCustomDirectsoldShowCount = 0;
        this.bannerNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.bannerNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.interUserNum = 0;
        this.interShowCount = 0;
        this.interIncome = BigDecimal.ZERO;
        this.interEcpm = BigDecimal.ZERO;
        this.interDirectsoldUserNum = 0;
        this.interDirectsoldShowCount = 0;
        this.interDirectsoldIncome = BigDecimal.ZERO;
        this.interDirectsoldEcpm = BigDecimal.ZERO;
        this.interExchangeUserNum = 0;
        this.interExchangeShowCount = 0;
        this.interExchangeIncome = BigDecimal.ZERO;
        this.interExchangeEcpm = BigDecimal.ZERO;
        this.interNetworkUserNum = 0;
        this.interNetworkShowCount = 0;
        this.interNetworkIncome = BigDecimal.ZERO;
        this.interNetworkEcpm = BigDecimal.ZERO;
        this.interMintegralBiddingUserNum = 0;
        this.interMintegralBiddingShowCount = 0;
        this.interMintegralBiddingIncome = BigDecimal.ZERO;
        this.interMintegralBiddingEcpm = BigDecimal.ZERO;
        this.interVungleBiddingUserNum = 0;
        this.interVungleBiddingShowCount = 0;
        this.interVungleBiddingIncome = BigDecimal.ZERO;
        this.interVungleBiddingEcpm = BigDecimal.ZERO;
        this.interNotCustomDirectsoldUserNum = 0;
        this.interNotCustomDirectsoldShowCount = 0;
        this.interNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.interNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.mreUserNum = 0;
        this.mreShowCount = 0;
        this.mreIncome = BigDecimal.ZERO;
        this.mreEcpm = BigDecimal.ZERO;
        this.mrecDirectsoldUserNum = 0;
        this.mrecDirectsoldShowCount = 0;
        this.mrecDirectsoldIncome = BigDecimal.ZERO;
        this.mrecDirectsoldEcpm = BigDecimal.ZERO;
        this.mrecExchangeUserNum = 0;
        this.mrecExchangeShowCount = 0;
        this.mrecExchangeIncome = BigDecimal.ZERO;
        this.mrecExchangeEcpm = BigDecimal.ZERO;
        this.mrecCustomNetworkUserNum = 0;
        this.mrecCustomNetworkShowCount = 0;
        this.mrecCustomNetworkIncome = BigDecimal.ZERO;
        this.mrecCustomNetworkEcpm = BigDecimal.ZERO;
        this.mrecNotCustomDirectsoldUserNum = 0;
        this.mrecNotCustomDirectsoldShowCount = 0;
        this.mrecNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.mrecNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.rewardUserNum = 0;
        this.rewardShowCount = 0;
        this.rewardIncome = BigDecimal.ZERO;
        this.rewardEcpm = BigDecimal.ZERO;
        this.rewardExchangeUserNum = 0;
        this.rewardExchangeShowCount = 0;
        this.rewardExchangeIncome = BigDecimal.ZERO;
        this.rewardExchangeEcpm = BigDecimal.ZERO;
        this.rewardNetworkUserNum = 0;
        this.rewardNetworkShowCount = 0;
        this.rewardNetworkIncome = BigDecimal.ZERO;
        this.rewardNetworkEcpm = BigDecimal.ZERO;
        this.rewardCustomNetworkUserNum = 0;
        this.rewardCustomNetworkShowCount = 0;
        this.rewardCustomNetworkIncome = BigDecimal.ZERO;
        this.rewardCustomNetworkEcpm = BigDecimal.ZERO;
        this.rewardFacebookNetworkUserNum = 0;
        this.rewardFacebookNetworkShowCount = 0;
        this.rewardFacebookNetworkIncome = BigDecimal.ZERO;
        this.rewardFacebookNetworkEcpm = BigDecimal.ZERO;
        this.rewardMintegralBiddingUserNum = 0;
        this.rewardMintegralBiddingShowCount = 0;
        this.rewardMintegralBiddingIncome = BigDecimal.ZERO;
        this.rewardMintegralBiddingEcpm = BigDecimal.ZERO;
        this.rewardVungleBiddingUserNum = 0;
        this.rewardVungleBiddingShowCount = 0;
        this.rewardVungleBiddingIncome = BigDecimal.ZERO;
        this.rewardVungleBiddingEcpm = BigDecimal.ZERO;
        this.rewardNotCustomDirectsoldUserNum = 0;
        this.rewardNotCustomDirectsoldShowCount = 0;
        this.rewardNotCustomDirectsoldIncome = BigDecimal.ZERO;
        this.rewardNotCustomDirectsoldEcpm = BigDecimal.ZERO;
        this.createTime = new Date();
    }

    public static DwsDailyPkgVerAdvertising of(Long id, Integer dates, String pkg, String version, Integer userType) {
        DwsDailyPkgVerAdvertising dwsDailyPkgVerAdvertising = new DwsDailyPkgVerAdvertising();
        dwsDailyPkgVerAdvertising.setId(id);
        dwsDailyPkgVerAdvertising.setDates(dates);
        dwsDailyPkgVerAdvertising.setPkg(pkg);
        dwsDailyPkgVerAdvertising.setVersion(version);
        dwsDailyPkgVerAdvertising.setUserType(userType);
        return dwsDailyPkgVerAdvertising;
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
     * @param adFormatMap 广告平台及数据集合
     */
    public void initData(Map<String, List<PkgVerAdDTO>> adFormatMap) {
        // 各个ad平台的数据汇总；
        // 各个ad平台的各个维度的数据汇总；
        // 各个ad平台去除 DIRECTSOLD 、 CUSTOM_NETWORK 的汇总数据；
        // 总平台汇总 DIRECTSOLD 的数据；
        // 总平台汇总 CUSTOM_NETWORK 的数据；
        // 总平台汇总 DIRECTSOLD 、 CUSTOM_NETWORK 的数据；

        adFormatMap.values().forEach(networkMap -> {
            // 相同网络维度汇总
            networkMap.size();
            for (PkgVerAdDTO pkgVerAdDTO : networkMap) {
                pkgVerAdDTO.getUserNum();
                pkgVerAdDTO.getAdShowCount();
                pkgVerAdDTO.getAdIncome();
            }
        });
    }
}
