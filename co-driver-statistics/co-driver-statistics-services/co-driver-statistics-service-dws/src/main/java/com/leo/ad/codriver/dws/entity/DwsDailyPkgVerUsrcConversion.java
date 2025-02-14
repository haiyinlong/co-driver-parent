package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_ver_usrc_conversion
 */
@TableName(value = "dws_daily_pkg_ver_usrc_conversion")
@Data
public class DwsDailyPkgVerUsrcConversion implements BaseEntity {
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
     * 1导流来源；2盲盒来源
     */
    private Integer sourceType;
    /**
     * 转化用户数
     */
    private Long userNum;

}
