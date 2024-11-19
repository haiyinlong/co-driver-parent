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
 * @TableName dws_daily_pkg_ver_usrc_qp_ltv
 */
@TableName(value ="dws_daily_pkg_ver_usrc_qp_ltv")
@Data
public class DwsDailyPkgVerUsrcQpLtv implements Serializable {
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}