package com.leo.ad.codriver.dws.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.dwd.entity.DwdUserOnline;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_cohort_pkg_online
 */
@TableName(value = "dws_daily_cohort_pkg_online")
@Data
public class DwsDailyCohortPkgOnline implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期
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
     * 在线时长
     */
    private Long totalOnlineTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public DwsDailyCohortPkgOnline(Integer dates, String pkg, Integer registerDates, Integer cohortDay) {
        this.dates = dates;
        this.pkg = pkg;
        this.registerDates = registerDates;
        this.cohortDay = cohortDay;
        this.createTime = LocalDateTime.now();
        this.totalOnlineTime = 0L;
    }

    public static DwsDailyCohortPkgOnline of(int dates, Integer registerDates, Integer registerDay, String pkg) {
        return new DwsDailyCohortPkgOnline(dates, pkg, registerDates, registerDay);
    }

    public void calculate(DwdUserOnline dwdUserOnline) {
        this.totalOnlineTime += dwdUserOnline.getOnlineTime();
    }
}
