package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_usrc_retention
 */
@TableName(value = "dws_daily_pkg_usrc_retention")
@Data
public class DwsDailyPkgUsrcRetention implements BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活跃(统计)日期
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
     * 注册日期
     */
    private Integer registerDates;

    /**
     * 注册天
     */
    private Long registerDay;

    /**
     * 留存用户数
     */
    private Long retentionUserNum;

}
