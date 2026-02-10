package com.leo.ad.codriver.ads.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("ads_daily_oeta_base_report")
public class AdsDailyOetaBaseReport implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private String version;
    private String pkg;
    private String userSource;
    private Long userType;
    private Long newUserNum;
    private Long secondUserNum;
    private BigDecimal secondUserRetentionRate;
    private Long tertiaryUserNum;
    private BigDecimal tertiaryUserRetentionRate;
    private Long activeUserNum;
    private BigDecimal avgUserOnlineTime;
    private Long shareNewUserNum;
    private BigDecimal shareRate;
    private Long offerUserNum;
    private BigDecimal offerRate;
    private Long offerMysteryBoxUserNum;
    private BigDecimal offerMysteryBoxRate;
    private Long offerJkUserNum;
    private BigDecimal offerJkRate;
    private Long paymentUserNum;
    private BigDecimal paymentRate;
    private Long paymentMysteryBoxUserNum;
    private BigDecimal paymentMysteryBoxRate;
    private Long paymentJkUserNum;
    private BigDecimal paymentJkRate;
    private BigDecimal qpLtv;
    private BigDecimal adRewardIncome;
    private Long adRewardUserNum;
    private Long adRewardShowNum;
    private BigDecimal adRewardEcpm;
    private BigDecimal avgUserAdRewardShowNum;
    private BigDecimal adRewardRate;
    private Long adDirectSoldUserNum;
    private Long adDirectSoldShowNum;
    private BigDecimal avgUserAdDirectSoldShowNum;
    private Long adDirectSoldClickNum;
    private BigDecimal adDirectSoldClickShowRate;
    private BigDecimal adDirectSoldRate;
    private BigDecimal avgUserAdShowNum;
    private BigDecimal avgUserAdInterShowNum;
    private BigDecimal avgUserAdMrecShowNum;

    /**
     * inter广告展示次数
     */
    private Long interShowNum;

    /**
     * ecpm(广告收入/广告展示)x1000
     */
    private BigDecimal interEcpm;

    /**
     * 人均inter广告展示次数
     */
    private BigDecimal interAvgUserShowNum;

    /**
     * inter广告渗透率:观看用户/ 统计用户
     */
    private BigDecimal interRate;

    /**
     * banner广告展示次数
     */
    private Long bannerShowNum;

    /**
     * ecpm(广告收入/广告展示)x1000
     */
    private BigDecimal bannerEcpm;

    /**
     * 人均banner广告展示次数
     */
    private BigDecimal bannerAvgUserShowNum;

    /**
     * banner广告渗透率:观看用户/ 统计用户
     */
    private BigDecimal bannerRate;

    private BigDecimal withdrawAmount;
    private BigDecimal rechargeAmount;
    private BigDecimal withdrawFee;
    private BigDecimal withdrawCost;
    private BigDecimal promotionCost;
    private BigDecimal totalExpenditure;
    private BigDecimal cpi;
    private BigDecimal adRoi;
    private BigDecimal totalRoi;
    private BigDecimal firstDayRoi;
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
