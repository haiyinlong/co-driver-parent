package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.dwd.entity.DwdQpLtvRecord;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_cohort_pkg_qp_ltv
 */
@TableName(value = "dws_daily_cohort_pkg_qp_ltv")
@Data
public class DwsDailyCohortPkgQpLtv implements BaseEntity {
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
     * 包名
     */
    private String pkg;

    /**
     * 同期群天数:d0,d1,d2
     */
    private Integer cohortDay;

    /**
     * 导流用户数量
     */
    private Integer riverUserNum;

    /**
     * 导流event_ltv
     */
    private BigDecimal riverEventLtv;

    /**
     * 导流用户ltv
     */
    private BigDecimal riverUserLtv;
    /**
     * 盲盒用户数量
     */
    private Integer mysteryBoxUserNum;

    /**
     * 盲盒event_ltv
     */
    private BigDecimal mysteryBoxEventLtv;

    /**
     * 盲盒用户ltv
     */
    private BigDecimal mysteryBoxUserLtv;
    /**
     * 总用户数量
     */
    private Integer totalUserNum;

    /**
     * 总event_ltv
     */
    private BigDecimal totalEventLtv;

    /**
     * 总用户ltv
     */
    private BigDecimal totalUserLtv;

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

    public DwsDailyCohortPkgQpLtv(Integer dates, Integer registerDates, String pkg, Integer cohortDay) {
        this.dates = dates;
        this.registerDates = registerDates;
        this.pkg = pkg;
        this.cohortDay = cohortDay;
        this.createTime = LocalDateTime.now();
        this.riverUserNum = 0;
        this.mysteryBoxUserNum = 0;
        this.totalUserNum = 0;
        this.riverEventLtv = BigDecimal.ZERO;
        this.mysteryBoxEventLtv = BigDecimal.ZERO;
        this.totalEventLtv = BigDecimal.ZERO;
        this.riverUserLtv = BigDecimal.ZERO;
        this.mysteryBoxUserLtv = BigDecimal.ZERO;
        this.totalUserLtv = BigDecimal.ZERO;
        this.totalUser = new HashSet<>();
        this.riverUser = new HashSet<>();
        this.mysteryBoxUser = new HashSet<>();
    }

    public static DwsDailyCohortPkgQpLtv of(Integer dates, Integer registerDates, String pkg, Integer cohortDay) {
        return new DwsDailyCohortPkgQpLtv(dates, registerDates, pkg, cohortDay);
    }

    public void calculate(DwdQpLtvRecord dwdQpLtvRecord) {
        if (null == dwdQpLtvRecord) {
            return;
        }
        // 计算总用户数
        totalUser.add(dwdQpLtvRecord.getUserId());
        totalUserNum = totalUser.size();
        totalEventLtv = BigDecimalUtils.add(totalEventLtv, dwdQpLtvRecord.getEventLtv());
        totalUserLtv = BigDecimalUtils.add(totalUserLtv, dwdQpLtvRecord.getUserLtv());
        // 计算导流用户数
        if (1 == dwdQpLtvRecord.getSourceType()) {
            riverUser.add(dwdQpLtvRecord.getUserId());
            riverUserNum = riverUser.size();
            riverEventLtv = BigDecimalUtils.add(riverEventLtv, dwdQpLtvRecord.getEventLtv());
            riverUserLtv = BigDecimalUtils.add(riverUserLtv, dwdQpLtvRecord.getUserLtv());
        } else if (2 == dwdQpLtvRecord.getSourceType()) {
            // 计算盲盒用户数
            mysteryBoxUser.add(dwdQpLtvRecord.getUserId());
            mysteryBoxUserNum = mysteryBoxUser.size();
            mysteryBoxEventLtv = BigDecimalUtils.add(mysteryBoxEventLtv, dwdQpLtvRecord.getEventLtv());
            mysteryBoxUserLtv = BigDecimalUtils.add(mysteryBoxUserLtv, dwdQpLtvRecord.getUserLtv());
        }
    }
}
