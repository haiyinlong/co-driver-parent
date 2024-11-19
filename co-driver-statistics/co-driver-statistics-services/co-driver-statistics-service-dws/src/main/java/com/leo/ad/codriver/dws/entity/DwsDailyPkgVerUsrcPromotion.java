package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName dws_daily_pkg_ver_usrc_promotion
 */
@TableName(value ="dws_daily_pkg_ver_usrc_promotion")
@Data
public class DwsDailyPkgVerUsrcPromotion implements Serializable {
    /**
     * 主键id
     */
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
     * 应用版本
     */
    private String version;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}