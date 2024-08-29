package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
    private Long allUserTotalShowNum;
    private Long allUserNoSoldUserNum;
    private BigDecimal allUserNoSoldIncome;
    private Long allUserNoSoldShowNum;
    private Long allUserNoBannerUserNum;
    private BigDecimal allUserNoBannerIncome;
    private Long allUserNoBannerShowNum;
    private Long allUserRewardUserNum;
    private BigDecimal allUserRewardIncome;
    private Long allUserRewardShowNum;
    private Long allUserInterUserNum;
    private BigDecimal allUserInterIncome;
    private Long allUserInterShowNum;
    private Long allUserMrecUserNum;
    private BigDecimal allUserMrecIncome;
    private Long allUserMrecShowNum;
    private Long newUserTotalUserNum;
    private BigDecimal newUserTotalIncome;
    private Long newUserTotalShowNum;
    private Long newUserNoSoldUserNum;
    private BigDecimal newUserNoSoldIncome;
    private Long newUserNoSoldShowNum;
    private Long newUserNoBannerUserNum;
    private BigDecimal newUserNoBannerIncome;
    private Long newUserNoBannerShowNum;
    private Long newUserRewardUserNum;
    private BigDecimal newUserRewardIncome;
    private Long newUserRewardShowNum;
    private Long newUserInterUserNum;
    private BigDecimal newUserInterIncome;
    private Long newUserInterShowNum;
    private Long newUserMrecUserNum;
    private BigDecimal newUserMrecIncome;
    private Long newUserMrecShowNum;
    private Date createTime;

    public DwsDailyPackageAd() {
        this.allUserTotalUserNum = 0L;
        this.allUserTotalIncome = BigDecimal.ZERO;
        this.allUserTotalShowNum = 0L;
        this.allUserNoSoldUserNum = 0L;
        this.allUserNoSoldIncome = BigDecimal.ZERO;
        this.allUserNoSoldShowNum = 0L;
        this.allUserNoBannerUserNum = 0L;
        this.allUserNoBannerIncome = BigDecimal.ZERO;
        this.allUserNoBannerShowNum = 0L;
        this.allUserRewardUserNum = 0L;
        this.allUserRewardIncome = BigDecimal.ZERO;
        this.allUserRewardShowNum = 0L;
        this.allUserInterUserNum = 0L;
        this.allUserInterIncome = BigDecimal.ZERO;
        this.allUserInterShowNum = 0L;
        this.allUserMrecUserNum = 0L;
        this.allUserMrecIncome = BigDecimal.ZERO;
        this.allUserMrecShowNum = 0L;
        this.newUserTotalUserNum = 0L;
        this.newUserTotalIncome = BigDecimal.ZERO;
        this.newUserTotalShowNum = 0L;
        this.newUserNoSoldUserNum = 0L;
        this.newUserNoSoldIncome = BigDecimal.ZERO;
        this.newUserNoSoldShowNum = 0L;
        this.newUserNoBannerUserNum = 0L;
        this.newUserNoBannerIncome = BigDecimal.ZERO;
        this.newUserNoBannerShowNum = 0L;
        this.newUserRewardUserNum = 0L;
        this.newUserRewardIncome = BigDecimal.ZERO;
        this.newUserRewardShowNum = 0L;
        this.newUserInterUserNum = 0L;
        this.newUserInterIncome = BigDecimal.ZERO;
        this.newUserInterShowNum = 0L;
        this.newUserMrecUserNum = 0L;
        this.newUserMrecIncome = BigDecimal.ZERO;
        this.newUserMrecShowNum = 0L;
    }

    public String getUniqueId() {
        return String.format("%s_%s_%s", pkg, version, country);
    }

    public void init() {
        this.createTime = new Date();
    }
}
