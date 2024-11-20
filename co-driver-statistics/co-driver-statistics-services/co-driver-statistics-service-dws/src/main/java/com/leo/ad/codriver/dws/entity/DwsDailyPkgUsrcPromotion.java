package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_usrc_promotion
 */
@TableName(value = "dws_daily_pkg_usrc_promotion")
@Data
public class DwsDailyPkgUsrcPromotion implements BaseEntity {
    /**
     * 主键id
     */
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
     * 用户来源
     */
    private String userSource;

    /**
     * 包注册用户数
     */
    private Long pkgUserNum;

    /**
     * 平均版本推广花费
     */
    private BigDecimal cost;

    /**
     * 平均版本推广花费(美元)
     */
    private BigDecimal changeCost;

    /**
     * 注册用户数
     */
    private Long userNum;

    /**
     * 用户占比
     */
    private BigDecimal userRate;

    /**
     * 包推广总花费
     */
    private BigDecimal pkgCost;

    /**
     * 包推广总花费(美元)
     */
    private BigDecimal pkgChangeCost;

    /**
     * 推广花费/新增(美元)
     */
    private BigDecimal cpi;

    /**
     * 创建时间
     */
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
