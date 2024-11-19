package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName dws_daily_pkg_ver_usrc_conversion
 */
@TableName(value ="dws_daily_pkg_ver_usrc_conversion")
@Data
public class DwsDailyPkgVerUsrcConversion implements Serializable {
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
     * 用户类型：活跃用户、新用户
     */
    private Integer userType;

    /**
     * 转化用户数
     */
    private Long userNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}