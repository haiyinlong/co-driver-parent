package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName dws_daily_pkg_ver_usrc_login
 */
@TableName(value ="dws_daily_pkg_ver_usrc_login")
@Data
public class DwsDailyPkgVerUsrcLogin implements Serializable {
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
     * 登录用户数
     */
    private Long userNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}