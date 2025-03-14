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
 */
@TableName(value = "dws_daily_pkg_accumulate_ad")
@Data
public class DwsDailyPkgAccumulateRegister extends PkgAccumulateAd
    implements Serializable, BaseEntity, PkgAccumulateRegister {
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

    public DwsDailyPkgAccumulateRegister() {
        this.todayInit();
        this.accumulateInit();
    }

    public static String getPkgAdUniqueKey(DwdUserAdRecord dwdUserAdRecord) {
        return dwdUserAdRecord.getPkg() + dwdUserAdRecord.getRegisterDay();
    }

    public String getPkgAdUniqueKey() {
        return this.getPkg() + this.getRegisterDay();
    }

    public static DwsDailyPkgAccumulateRegister of(Integer dates, String pkg, Integer registerDates,
        Integer registerDay) {
        DwsDailyPkgAccumulateRegister dwsDailyPkgAccumulateAd = new DwsDailyPkgAccumulateRegister();
        dwsDailyPkgAccumulateAd.setDates(dates);
        dwsDailyPkgAccumulateAd.setPkg(pkg);
        dwsDailyPkgAccumulateAd.setRegisterDates(registerDates);
        dwsDailyPkgAccumulateAd.setRegisterDay(registerDay);
        dwsDailyPkgAccumulateAd.setCreateTime(LocalDateTime.now());
        return dwsDailyPkgAccumulateAd;
    }

    public DwsDailyPkgAccumulateRegister convertToday(Integer dates) {
        this.id = null;
        this.dates = dates;
        this.todayInit();
        // 注册天数 + 1 ，上一天数据转当天
        this.registerDay += 1;
        this.createTime = LocalDateTime.now();
        return this;
    }

    public DwsDailyPkgAccumulateRegister calculateAccumulate(DwsDailyPkgAccumulateRegister dwsDailyPkgAccumulateAd) {
        this.id = dwsDailyPkgAccumulateAd.getId();
        this.copyAdvertisingValue(dwsDailyPkgAccumulateAd);
        this.addAccumulate(dwsDailyPkgAccumulateAd);
        return this;
    }

    public void calculateAccumulate() {
        this.addAccumulate();
    }
}
