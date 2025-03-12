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
 * @TableName dws_register_90_days_accumulate_pkg_ver_usrc_ad
 */
@TableName(value = "dws_register_90_days_accumulate_pkg_ver_usrc_ad")
@Data
public class DwsRegister90DaysAccumulatePkgVerUsrcAd extends PkgAdIncome implements Serializable, BaseEntity {
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
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public DwsRegister90DaysAccumulatePkgVerUsrcAd() {
        this.todayInit();
        this.accumulateInit();
    }

    public static String getPkgAdUniqueKey(DwdUserAdRecord dwdUserAdRecord) {
        return dwdUserAdRecord.getPkg() + dwdUserAdRecord.getVersion() + dwdUserAdRecord.getUserSource()
            + dwdUserAdRecord.getRegisterDay();
    }

    public String getPkgAdUniqueKey() {
        return this.getPkg() + this.getVersion() + this.getUserSource() + this.getRegisterDay();
    }

    public static DwsRegister90DaysAccumulatePkgVerUsrcAd of(Integer dates, String pkg, String version,
        String userSource, Integer registerDates, Integer registerDay) {
        DwsRegister90DaysAccumulatePkgVerUsrcAd dwsRegister90DaysAccumulatePkgAd =
            new DwsRegister90DaysAccumulatePkgVerUsrcAd();
        dwsRegister90DaysAccumulatePkgAd.setDates(dates);
        dwsRegister90DaysAccumulatePkgAd.setPkg(pkg);
        dwsRegister90DaysAccumulatePkgAd.setVersion(version);
        dwsRegister90DaysAccumulatePkgAd.setUserSource(userSource);
        dwsRegister90DaysAccumulatePkgAd.setRegisterDates(registerDates);
        dwsRegister90DaysAccumulatePkgAd.setRegisterDay(registerDay);
        dwsRegister90DaysAccumulatePkgAd.setCreateTime(LocalDateTime.now());
        return dwsRegister90DaysAccumulatePkgAd;
    }

    public DwsRegister90DaysAccumulatePkgVerUsrcAd convertToday(Integer dates) {
        this.id = null;
        this.dates = dates;
        this.todayInit();
        // 注册天数 + 1 ，上一天数据转当天
        this.registerDay += 1;
        this.createTime = LocalDateTime.now();
        return this;
    }

    public DwsRegister90DaysAccumulatePkgVerUsrcAd
        calculateAccumulate(DwsRegister90DaysAccumulatePkgVerUsrcAd dwsRegister90DaysAccumulatePkgAd) {
        this.id = dwsRegister90DaysAccumulatePkgAd.getId();
        this.copyAdvertisingValue(dwsRegister90DaysAccumulatePkgAd);
        this.addAccumulate(dwsRegister90DaysAccumulatePkgAd);
        return this;
    }
}
