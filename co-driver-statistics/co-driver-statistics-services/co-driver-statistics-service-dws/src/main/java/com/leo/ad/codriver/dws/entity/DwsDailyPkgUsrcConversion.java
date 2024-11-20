package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_usrc_conversion
 */
@TableName(value = "dws_daily_pkg_usrc_conversion")
@Data
public class DwsDailyPkgUsrcConversion implements BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
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

}
