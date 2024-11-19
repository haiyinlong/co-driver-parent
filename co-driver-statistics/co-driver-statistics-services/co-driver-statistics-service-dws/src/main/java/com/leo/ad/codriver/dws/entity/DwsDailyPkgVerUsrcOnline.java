package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_ver_usrc_online
 */
@TableName(value = "dws_daily_pkg_ver_usrc_online")
@Data
public class DwsDailyPkgVerUsrcOnline implements BaseEntity {
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

    public void init() {
        this.createTime = new Date();
    }
}
