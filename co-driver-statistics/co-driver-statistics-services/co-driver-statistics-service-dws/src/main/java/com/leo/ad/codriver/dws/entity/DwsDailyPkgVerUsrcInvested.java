package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * dws推广花费
 *
 * @TableName dws_daily_pkg_ver_usrc_invested
 */
@TableName(value = "dws_daily_pkg_ver_usrc_invested")
@Data
public class DwsDailyPkgVerUsrcInvested implements BaseEntity {
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
     * 应用版本
     */
    private String version;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 推广花费 卢比
     */
    private BigDecimal totalInrCost;

    /**
     * 推广花费(美元)
     */
    private BigDecimal totalDollarCost;

    /**
     * 推广花费 卢比
     */
    private BigDecimal mtgInrCost;

    /**
     * 推广花费(美元)
     */
    private BigDecimal mtgDollarCost;

    /**
     * 推广花费 卢比
     */
    private BigDecimal fbInrCost;

    /**
     * 推广花费(美元)
     */
    private BigDecimal fbDollarCost;

    /**
     * 推广花费 卢比
     */
    private BigDecimal ggInrCost;

    /**
     * 推广花费(美元)
     */
    private BigDecimal ggDollarCost;

    /**
     * 创建时间
     */
    private Date createTime;

}
