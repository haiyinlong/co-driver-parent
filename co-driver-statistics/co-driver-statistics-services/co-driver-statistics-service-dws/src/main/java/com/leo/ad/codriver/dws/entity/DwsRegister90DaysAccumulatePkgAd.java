package com.leo.ad.codriver.dws.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * dws注册日期90天广告汇总统计
 *
 * @TableName dws_register_90_days_accumulate_pkg_ad
 */
@TableName(value = "dws_register_90_days_accumulate_pkg_ad")
@Data
public class DwsRegister90DaysAccumulatePkgAd extends PkgAdIncome implements Serializable, BaseEntity {
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
     * 提现时注册天（自然天）
     */
    private Integer registerDay;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public DwsRegister90DaysAccumulatePkgAd() {
        this.todayInit();
        this.accumulateInit();
    }

    public static String getPkgAdUniqueKey(DwdUserAdRecord dwdUserAdRecord) {
        return dwdUserAdRecord.getPkg() + dwdUserAdRecord.getRegisterDay();
    }

    public static String getPkgAdUniqueKey(DwsRegister90DaysAccumulatePkgAd pkgAd) {
        return pkgAd.getPkg() + pkgAd.getRegisterDay();
    }

    public static DwsRegister90DaysAccumulatePkgAd of(Integer dates, String pkg, Integer registerDates,
        Integer registerDay) {
        DwsRegister90DaysAccumulatePkgAd dwsRegister90DaysAccumulatePkgAd = new DwsRegister90DaysAccumulatePkgAd();
        dwsRegister90DaysAccumulatePkgAd.setDates(dates);
        dwsRegister90DaysAccumulatePkgAd.setPkg(pkg);
        dwsRegister90DaysAccumulatePkgAd.setRegisterDates(registerDates);
        dwsRegister90DaysAccumulatePkgAd.setRegisterDay(registerDay);
        dwsRegister90DaysAccumulatePkgAd.setCreateTime(LocalDateTime.now());
        return dwsRegister90DaysAccumulatePkgAd;
    }

    public DwsRegister90DaysAccumulatePkgAd convertToday(Integer dates) {
        this.id = null;
        this.dates = dates;
        this.todayInit();
        // 注册天数 + 1 ，上一天数据转当天
        this.registerDay += 1;
        this.createTime = LocalDateTime.now();
        return this;
    }

    public DwsRegister90DaysAccumulatePkgAd
        calculateAccumulate(DwsRegister90DaysAccumulatePkgAd dwsRegister90DaysAccumulatePkgAd) {
        this.id = dwsRegister90DaysAccumulatePkgAd.getId();
        this.copyAdvertisingValue(dwsRegister90DaysAccumulatePkgAd);
        this.addAccumulate(dwsRegister90DaysAccumulatePkgAd);
        return this;
    }

    public String getPkgAdUniqueKey() {
        return this.getPkg() + this.getRegisterDay();
    }

}
