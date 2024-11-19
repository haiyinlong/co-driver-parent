package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_ver_usrc_qp_ltv
 */
@TableName(value = "dws_daily_pkg_ver_usrc_qp_ltv")
@Data
public class DwsDailyPkgVerUsrcQpLtv implements BaseEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 统计/转化日期(取最新)
     */
    private Integer dates;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 转化时最新的版本
     */
    private String version;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 用户数量
     */
    private Long activeUserNum;

    /**
     * ltv
     */
    private BigDecimal activeEventLtv;

    /**
     * 用户ltv
     */
    private BigDecimal activeUserLtv;

    /**
     * 用户数量
     */
    private Long newUserNum;

    /**
     * ltv
     */
    private BigDecimal newUserEventLtv;

    /**
     * 用户ltv
     */
    private BigDecimal newUserLtv;

    /**
     * 创建时间
     */
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
