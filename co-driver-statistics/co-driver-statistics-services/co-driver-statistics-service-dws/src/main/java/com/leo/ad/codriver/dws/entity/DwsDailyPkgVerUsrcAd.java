package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_ver_usrc_ad
 */
@TableName(value = "dws_daily_pkg_ver_usrc_ad")
@Data
public class DwsDailyPkgVerUsrcAd implements BaseEntity {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 活跃总用户数量
     */
    private Long allUserTotalUserNum;

    /**
     * 活跃广告收入
     */
    private BigDecimal allUserTotalIncome;

    /**
     * 活跃广告关卡数量
     */
    private Long allUserTotalShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal allUserEcpm;

    /**
     * 活跃总用户数量
     */
    private Long allUserNoSoldUserNum;

    /**
     * 活跃广告收入
     */
    private BigDecimal allUserNoSoldIncome;

    /**
     * 活跃广告关卡数量
     */
    private Long allUserNoSoldShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal allUserNoSoldEcpm;

    /**
     * 活跃总用户数量
     */
    private Long allUserSoldUserNum;

    /**
     * 活跃广告收入
     */
    private BigDecimal allUserSoldIncome;

    /**
     * 活跃广告关卡数量
     */
    private Long allUserSoldShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal allUserSoldEcpm;

    /**
     * 活跃总用户数量
     */
    private Long allUserNoBannerUserNum;

    /**
     * 活跃广告收入
     */
    private BigDecimal allUserNoBannerIncome;

    /**
     * 活跃广告关卡数量
     */
    private Long allUserNoBannerShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal allUserNoBannerEcpm;

    /**
     * 活跃激励总用户数量
     */
    private Long allUserRewardUserNum;

    /**
     * 活跃激励广告收入
     */
    private BigDecimal allUserRewardIncome;

    /**
     * 活跃激励广告展示数量
     */
    private Long allUserRewardShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal allUserRewardEcpm;

    /**
     * 活跃插屏总用户数量
     */
    private Long allUserInterUserNum;

    /**
     * 活跃插屏广告收入
     */
    private BigDecimal allUserInterIncome;

    /**
     * 活跃插屏广告展示数量
     */
    private Long allUserInterShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal allUserInterEcpm;

    /**
     * 活跃总用户数量
     */
    private Long allUserMrecUserNum;

    /**
     * 活跃广告收入
     */
    private BigDecimal allUserMrecIncome;

    /**
     * 活跃广告关卡数量
     */
    private Long allUserMrecShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal allUserMrecEcpm;

    /**
     * 新增用户总用户数量
     */
    private Long newUserTotalUserNum;

    /**
     * 新增用户广告收入
     */
    private BigDecimal newUserTotalIncome;

    /**
     * 新增用户广告展示数量
     */
    private Long newUserTotalShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal newUserEcpm;

    /**
     * 新增用户总用户数量
     */
    private Long newUserNoSoldUserNum;

    /**
     * 新增用户广告收入
     */
    private BigDecimal newUserNoSoldIncome;

    /**
     * 新增用户广告展示数量
     */
    private Long newUserNoSoldShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal newUserNoSoldEcpm;

    /**
     * 新增用户总用户数量
     */
    private Long newUserSoldUserNum;

    /**
     * 新增用户广告收入
     */
    private BigDecimal newUserSoldIncome;

    /**
     * 新增用户广告展示数量
     */
    private Long newUserSoldShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal newUserSoldEcpm;

    /**
     * 新增用户总用户数量
     */
    private Long newUserNoBannerUserNum;

    /**
     * 新增用户广告收入
     */
    private BigDecimal newUserNoBannerIncome;

    /**
     * 新增用户广告展示数量
     */
    private Long newUserNoBannerShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal newUserNoBannerEcpm;

    /**
     * 新增用户激励总用户数量
     */
    private Long newUserRewardUserNum;

    /**
     * 新增用户激励广告收入
     */
    private BigDecimal newUserRewardIncome;

    /**
     * 新增用户激励广告展示数量
     */
    private Long newUserRewardShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal newUserRewardEcpm;

    /**
     * 新增用户插屏总用户数量
     */
    private Long newUserInterUserNum;

    /**
     * 新增用户插屏广告收入
     */
    private BigDecimal newUserInterIncome;

    /**
     * 新增用户插屏广告展示数量
     */
    private Long newUserInterShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal newUserInterEcpm;

    /**
     * 新增用户总用户数量
     */
    private Long newUserMrecUserNum;

    /**
     * 新增用户广告收入
     */
    private BigDecimal newUserMrecIncome;

    /**
     * 新增用户广告展示数量
     */
    private Long newUserMrecShowNum;

    /**
     * (广告收入/广告展示)x 1000
     */
    private BigDecimal newUserMrecEcpm;

    /**
     * 创建时间
     */
    private Date createTime;

    public void initAndCalculateEcpm() {
        this.allUserEcpm = calculateEcpm(allUserTotalIncome, allUserTotalShowNum);
        this.allUserNoSoldEcpm = calculateEcpm(allUserNoSoldIncome, allUserNoSoldShowNum);
        this.allUserSoldEcpm = calculateEcpm(allUserSoldIncome, allUserSoldShowNum);
        this.allUserNoBannerEcpm = calculateEcpm(allUserNoBannerIncome, allUserNoBannerShowNum);
        this.allUserRewardEcpm = calculateEcpm(allUserRewardIncome, allUserRewardShowNum);
        this.allUserInterEcpm = calculateEcpm(allUserInterIncome, allUserInterShowNum);
        this.allUserMrecEcpm = calculateEcpm(allUserMrecIncome, allUserMrecShowNum);
        this.newUserEcpm = calculateEcpm(newUserTotalIncome, newUserTotalShowNum);
        this.newUserNoSoldEcpm = calculateEcpm(newUserNoSoldIncome, newUserNoSoldShowNum);
        this.newUserSoldEcpm = calculateEcpm(newUserSoldIncome, newUserSoldShowNum);
        this.newUserNoBannerEcpm = calculateEcpm(newUserNoBannerIncome, newUserNoBannerShowNum);
        this.newUserRewardEcpm = calculateEcpm(newUserRewardIncome, newUserRewardShowNum);
        this.newUserInterEcpm = calculateEcpm(newUserInterIncome, newUserInterShowNum);
        this.newUserMrecEcpm = calculateEcpm(newUserMrecIncome, newUserMrecShowNum);
        this.createTime = new Date();
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

    public String getUniqueId() {
        return String.format("%s_%s_%s", pkg, version, userSource);
    }
}
