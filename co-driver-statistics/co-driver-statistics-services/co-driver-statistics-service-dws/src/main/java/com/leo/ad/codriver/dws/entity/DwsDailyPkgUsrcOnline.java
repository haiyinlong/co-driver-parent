package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_usrc_online
 */
@TableName(value = "dws_daily_pkg_usrc_online")
@Data
public class DwsDailyPkgUsrcOnline implements BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

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
