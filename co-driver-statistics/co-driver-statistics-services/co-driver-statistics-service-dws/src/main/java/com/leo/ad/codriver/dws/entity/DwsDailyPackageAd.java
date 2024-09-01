package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;

import lombok.Data;

@Data
@TableName("dws_daily_package_ad")
public class DwsDailyPackageAd {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private String version;
    private String pkg;
    private String country;
    private Long allUserTotalUserNum;
    private BigDecimal allUserTotalIncome;
    private BigDecimal allUserEcpm;
    private Long allUserTotalShowNum;
    private Long allUserNoSoldUserNum;
    private BigDecimal allUserNoSoldIncome;
    private BigDecimal allUserNoSoldEcpm;
    private Long allUserNoSoldShowNum;
    private Long allUserSoldUserNum;
    private BigDecimal allUserSoldIncome;
    private BigDecimal allUserSoldEcpm;
    private Long allUserSoldShowNum;
    private Long allUserNoBannerUserNum;
    private BigDecimal allUserNoBannerIncome;
    private BigDecimal allUserNoBannerEcpm;
    private Long allUserNoBannerShowNum;
    private Long allUserRewardUserNum;
    private BigDecimal allUserRewardIncome;
    private BigDecimal allUserRewardEcpm;
    private Long allUserRewardShowNum;
    private Long allUserInterUserNum;
    private BigDecimal allUserInterIncome;
    private BigDecimal allUserInterEcpm;
    private Long allUserInterShowNum;
    private Long allUserMrecUserNum;
    private BigDecimal allUserMrecIncome;
    private BigDecimal allUserMrecEcpm;
    private Long allUserMrecShowNum;
    private Long newUserTotalUserNum;
    private BigDecimal newUserTotalIncome;
    private BigDecimal newUserEcpm;
    private Long newUserTotalShowNum;
    private Long newUserNoSoldUserNum;
    private BigDecimal newUserNoSoldIncome;
    private BigDecimal newUserNoSoldEcpm;
    private Long newUserNoSoldShowNum;
    private Long newUserSoldUserNum;
    private BigDecimal newUserSoldIncome;
    private BigDecimal newUserSoldEcpm;
    private Long newUserSoldShowNum;
    private Long newUserNoBannerUserNum;
    private BigDecimal newUserNoBannerIncome;
    private BigDecimal newUserNoBannerEcpm;
    private Long newUserNoBannerShowNum;
    private Long newUserRewardUserNum;
    private BigDecimal newUserRewardIncome;
    private BigDecimal newUserRewardEcpm;
    private Long newUserRewardShowNum;
    private Long newUserInterUserNum;
    private BigDecimal newUserInterIncome;
    private BigDecimal newUserInterEcpm;
    private Long newUserInterShowNum;
    private Long newUserMrecUserNum;
    private BigDecimal newUserMrecIncome;
    private BigDecimal newUserMrecEcpm;
    private Long newUserMrecShowNum;
    private Date createTime;

    public DwsDailyPackageAd() {
        this.allUserTotalUserNum = 0L;
        this.allUserTotalIncome = BigDecimal.ZERO;
        this.allUserEcpm = BigDecimal.ZERO;
        this.allUserTotalShowNum = 0L;
        this.allUserNoSoldUserNum = 0L;
        this.allUserNoSoldIncome = BigDecimal.ZERO;
        this.allUserNoSoldEcpm = BigDecimal.ZERO;
        this.allUserNoSoldShowNum = 0L;
        this.allUserSoldUserNum = 0L;
        this.allUserSoldIncome = BigDecimal.ZERO;
        this.allUserSoldEcpm = BigDecimal.ZERO;
        this.allUserSoldShowNum = 0L;
        this.allUserNoBannerUserNum = 0L;
        this.allUserNoBannerIncome = BigDecimal.ZERO;
        this.allUserNoBannerEcpm = BigDecimal.ZERO;
        this.allUserNoBannerShowNum = 0L;
        this.allUserRewardUserNum = 0L;
        this.allUserRewardIncome = BigDecimal.ZERO;
        this.allUserRewardEcpm = BigDecimal.ZERO;
        this.allUserRewardShowNum = 0L;
        this.allUserInterUserNum = 0L;
        this.allUserInterIncome = BigDecimal.ZERO;
        this.allUserInterEcpm = BigDecimal.ZERO;
        this.allUserInterShowNum = 0L;
        this.allUserMrecUserNum = 0L;
        this.allUserMrecIncome = BigDecimal.ZERO;
        this.allUserMrecEcpm = BigDecimal.ZERO;
        this.allUserMrecShowNum = 0L;
        this.newUserTotalUserNum = 0L;
        this.newUserTotalIncome = BigDecimal.ZERO;
        this.newUserEcpm = BigDecimal.ZERO;
        this.newUserTotalShowNum = 0L;
        this.newUserNoSoldUserNum = 0L;
        this.newUserNoSoldIncome = BigDecimal.ZERO;
        this.newUserNoSoldEcpm = BigDecimal.ZERO;
        this.newUserNoSoldShowNum = 0L;
        this.newUserSoldUserNum = 0L;
        this.newUserSoldIncome = BigDecimal.ZERO;
        this.newUserSoldEcpm = BigDecimal.ZERO;
        this.newUserSoldShowNum = 0L;
        this.newUserNoBannerUserNum = 0L;
        this.newUserNoBannerIncome = BigDecimal.ZERO;
        this.newUserNoBannerEcpm = BigDecimal.ZERO;
        this.newUserNoBannerShowNum = 0L;
        this.newUserRewardUserNum = 0L;
        this.newUserRewardIncome = BigDecimal.ZERO;
        this.newUserRewardEcpm = BigDecimal.ZERO;
        this.newUserRewardShowNum = 0L;
        this.newUserInterUserNum = 0L;
        this.newUserInterIncome = BigDecimal.ZERO;
        this.newUserInterEcpm = BigDecimal.ZERO;
        this.newUserInterShowNum = 0L;
        this.newUserMrecUserNum = 0L;
        this.newUserMrecIncome = BigDecimal.ZERO;
        this.newUserMrecEcpm = BigDecimal.ZERO;
        this.newUserMrecShowNum = 0L;
    }

    public String getUniqueId() {
        return String.format("%s_%s_%s", pkg, version, country);
    }

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
}
