package com.leo.ad.codriver.dws.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.dwd.entity.DwdUserConversion;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_cohort_pkg_conversion
 */
@TableName(value = "dws_daily_cohort_pkg_conversion")
@Data
public class DwsDailyCohortPkgConversion implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 统计/转化日期(取最新)
     */
    private Integer dates;

    /**
     * 注册日期
     */
    private Integer registerDates;

    /**
     * 同期群天数:d0,d1,d2
     */
    private Integer cohortDay;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 导流转化用户数
     */
    private Integer riverUserNum;

    /**
     * 盲盒转化用户数
     */
    private Integer mysteryBoxUserNum;

    /**
     * 总转化用户数
     */
    private Integer totalUserNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private Set<Long> totalUser;
    @TableField(exist = false)
    private Set<Long> riverUser;
    @TableField(exist = false)
    private Set<Long> mysteryBoxUser;

    public DwsDailyCohortPkgConversion(Integer dates, Integer registerDates, Integer cohortDay, String pkg) {
        this.dates = dates;
        this.registerDates = registerDates;
        this.cohortDay = cohortDay;
        this.pkg = pkg;
        this.totalUserNum = 0;
        this.riverUserNum = 0;
        this.mysteryBoxUserNum = 0;
        this.createTime = LocalDateTime.now();
        this.totalUser = new HashSet<>();
        this.riverUser = new HashSet<>();
        this.mysteryBoxUser = new HashSet<>();
    }

    public static DwsDailyCohortPkgConversion of(Integer dates, Integer registerDates, Integer registerDay,
        String pkg) {
        return new DwsDailyCohortPkgConversion(dates, registerDates, registerDay, pkg);
    }

    public void calculate(DwdUserConversion dwdUserConversion) {
        if (null == dwdUserConversion) {
            return;
        }
        // 计算总用户数
        totalUser.add(dwdUserConversion.getUserId());
        totalUserNum = totalUser.size();
        // 计算导流用户数
        if (1 == dwdUserConversion.getExtraType()) {
            riverUser.add(dwdUserConversion.getUserId());
            riverUserNum = riverUser.size();
        } else if (2 == dwdUserConversion.getExtraType()) {
            // 计算盲盒用户数
            mysteryBoxUser.add(dwdUserConversion.getUserId());
            mysteryBoxUserNum = mysteryBoxUser.size();
        }
    }

}
