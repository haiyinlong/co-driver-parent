package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName dws_daily_pkg_ver_usrc_register
 */
@TableName(value ="dws_daily_pkg_ver_usrc_register")
@Data
public class DwsDailyPkgVerUsrcRegister implements Serializable {
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}