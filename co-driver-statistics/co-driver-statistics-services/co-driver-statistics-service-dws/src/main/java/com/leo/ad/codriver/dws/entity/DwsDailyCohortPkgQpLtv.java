package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.dwd.entity.DwdQpLtvRecord;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @TableName dws_daily_cohort_pkg_qp_ltv
 */
@Slf4j
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
    private Long cohortDay;

    /**
     * 导流用户数量
     */
    private Long riverUserNum;

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
    private Long mysteryBoxUserNum;

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
    private Long totalUserNum;

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

    public DwsDailyCohortPkgQpLtv() {}

    public DwsDailyCohortPkgQpLtv(Integer dates, Integer registerDates, String pkg, Integer cohortDay) {
        this.dates = dates;
        this.registerDates = registerDates;
        this.pkg = pkg;
        this.cohortDay = Long.valueOf(cohortDay);
        this.createTime = LocalDateTime.now();
        this.riverUserNum = 0L;
        this.mysteryBoxUserNum = 0L;
        this.totalUserNum = 0L;
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

    public void calculate(DwdQpLtvRecord dwdQpLtvRecord, BigDecimal indianToDollar) {
        if (null == dwdQpLtvRecord) {
            return;
        }
        // 计算总用户数
        BigDecimal eventLtv = getEventLtv(dwdQpLtvRecord.getEvent(), indianToDollar);
        totalUser.add(dwdQpLtvRecord.getUserId());
        totalUserNum = (long)totalUser.size();
        totalEventLtv = BigDecimalUtils.add(totalEventLtv, dwdQpLtvRecord.getEventLtv());
        totalEventLtv = BigDecimalUtils.add(totalEventLtv, eventLtv);
        totalUserLtv = BigDecimalUtils.add(totalUserLtv, dwdQpLtvRecord.getUserLtv());
        // 计算导流用户数
        if (1 == dwdQpLtvRecord.getSourceType()) {
            riverUser.add(dwdQpLtvRecord.getUserId());
            riverUserNum = (long)riverUser.size();
            riverEventLtv = BigDecimalUtils.add(riverEventLtv, dwdQpLtvRecord.getEventLtv());
            riverEventLtv = BigDecimalUtils.add(riverEventLtv, eventLtv);
            riverUserLtv = BigDecimalUtils.add(riverUserLtv, dwdQpLtvRecord.getUserLtv());
        } else if (2 == dwdQpLtvRecord.getSourceType()) {
            // 计算盲盒用户数
            mysteryBoxUser.add(dwdQpLtvRecord.getUserId());
            mysteryBoxUserNum = (long)mysteryBoxUser.size();
            mysteryBoxEventLtv = BigDecimalUtils.add(mysteryBoxEventLtv, dwdQpLtvRecord.getEventLtv());
            mysteryBoxEventLtv = BigDecimalUtils.add(mysteryBoxEventLtv, eventLtv);
            mysteryBoxUserLtv = BigDecimalUtils.add(mysteryBoxUserLtv, dwdQpLtvRecord.getUserLtv());
        }
    }

    public static BigDecimal getEventLtv(String eventStr, BigDecimal indianToDollar) {
        if (ObjectUtils.isEmpty(eventStr)) {
            return BigDecimal.ZERO;
        }
        if (eventStr.startsWith("recharge_") && eventStr.indexOf("_") > 0) {
            BigDecimal eventLtvIn = BigDecimal.ZERO;
            String eventLtvValue = eventStr.split("_")[1];
            try {
                eventLtvIn = new BigDecimal(eventLtvValue);
            } catch (Exception e) {
                log.error("转化数值异常，" + eventStr + " 截取后的值:" + eventLtvValue, e);
            }
            return BigDecimalUtils.divide(eventLtvIn, indianToDollar, 8);
        }
        return BigDecimal.ZERO;
    }
}
