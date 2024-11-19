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
 * @TableName dws_daily_pkg_ver_usrc_online
 */
@TableName(value ="dws_daily_pkg_ver_usrc_online")
@Data
public class DwsDailyPkgVerUsrcOnline implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 在线时长
     */
    private Long totalOnlineTime;

    /**
     * 用户平均在线时长
     */
    private BigDecimal avgUserOnlineTime;

    /**
     * 新用户在线时长
     */
    private Long newUserOnlineTime;

    /**
     * 新用户平均在线时长
     */
    private BigDecimal avgNewUserOnlineTime;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}