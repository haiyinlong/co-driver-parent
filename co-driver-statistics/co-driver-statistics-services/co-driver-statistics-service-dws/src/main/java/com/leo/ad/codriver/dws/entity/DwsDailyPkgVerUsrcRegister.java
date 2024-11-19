package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_ver_usrc_register
 */
@TableName(value = "dws_daily_pkg_ver_usrc_register")
@Data
public class DwsDailyPkgVerUsrcRegister implements BaseEntity {
    /**
     * 主键
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
     * 注册用户数
     */
    private Long userNum;

}
