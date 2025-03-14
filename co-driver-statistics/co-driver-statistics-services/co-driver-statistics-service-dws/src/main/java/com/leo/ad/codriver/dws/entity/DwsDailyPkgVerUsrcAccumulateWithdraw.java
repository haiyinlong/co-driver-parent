package com.leo.ad.codriver.dws.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * dws每日提现累计汇总统计
 *
 * @TableName dws_daily_pkg_ver_usrc_accumulate_withdraw
 */
@TableName(value = "dws_daily_pkg_ver_usrc_accumulate_withdraw")
@Data
public class DwsDailyPkgVerUsrcAccumulateWithdraw extends PkgAccumulateWithdraw
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

    public String getUniqueKey() {
        return pkg + version + userSource + registerDay;
    }

    public DwsDailyPkgVerUsrcAccumulateWithdraw
        calculateAccumulate(DwsDailyPkgVerUsrcAccumulateWithdraw todayWithdraw) {
        this.id = todayWithdraw.getId();
        this.accumulate(todayWithdraw);
        return this;
    }

    public void calculateAccumulate() {
        this.accumulate();
    }

    public DwsDailyPkgVerUsrcAccumulateWithdraw convertToday(Integer dates) {
        this.id = null;
        this.dates = dates;
        this.todayInit();
        // 注册天数 + 1 ，上一天数据转当天
        this.registerDay += 1;
        this.createTime = LocalDateTime.now();
        return this;
    }
}
