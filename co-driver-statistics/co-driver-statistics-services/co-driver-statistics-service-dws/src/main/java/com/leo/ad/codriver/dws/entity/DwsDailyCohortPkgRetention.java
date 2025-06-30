package com.leo.ad.codriver.dws.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.dwd.entity.DwdUserLoginRecord;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_cohort_pkg_retention
 */
@TableName(value = "dws_daily_cohort_pkg_retention")
@Data
public class DwsDailyCohortPkgRetention implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活跃(统计)日期
     */
    private Integer dates;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 注册日期
     */
    private Integer registerDates;

    /**
     * 同期群天数:d0,d1,d2
     */
    private Integer cohortDay;

    /**
     * 留存用户数
     */
    private Integer retentionUserNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private Set<Long> totalUser;

    public DwsDailyCohortPkgRetention(Integer dates, String pkg, Integer registerDates, Integer cohortDay) {
        this.dates = dates;
        this.pkg = pkg;
        this.registerDates = registerDates;
        this.cohortDay = cohortDay;
        this.createTime = LocalDateTime.now();
        this.retentionUserNum = 0;
        this.totalUser = new HashSet<>();
    }

    public static DwsDailyCohortPkgRetention of(int dates, Integer registerDates, Integer registerDay, String pkg) {
        return new DwsDailyCohortPkgRetention(dates, pkg, registerDates, registerDay);
    }

    public void calculate(DwdUserLoginRecord dwdUserLoginRecord) {
        if (null == dwdUserLoginRecord) {
            return;
        }
        // 计算总用户数
        totalUser.add(dwdUserLoginRecord.getUserId());
        retentionUserNum = totalUser.size();
    }
}
