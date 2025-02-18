package com.leo.ad.codriver.dws.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * dws每日用户来源版本事件统计
 *
 * @TableName dws_daily_pkg_ver_usrc_ad_conversion_event
 */
@TableName(value = "dws_daily_pkg_ver_usrc_ad_conversion_event")
@Data
public class DwsDailyPkgVerUsrcAdConversionEvent implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
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
     * 用户来源
     */
    private String userSource;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 用户类型：0活跃用户、1新用户
     */
    private Integer userType;

    /**
     * 广告点击用户数
     */
    private Long clickUserNum;

    /**
     * 广告点击次数
     */
    private Long clickRecordNum;

    /**
     * 看广告用户数
     */
    private Long showUserNum;

    /**
     * 看广告次数
     */
    private Long showRecordNum;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public DwsDailyPkgVerUsrcAdConversionEvent() {
        this.setClickUserNum(0L);
        this.setClickRecordNum(0L);
        this.setShowUserNum(0L);
        this.setShowRecordNum(0L);
        this.setCreateTime(new Date());
    }

    public static DwsDailyPkgVerUsrcAdConversionEvent ofNewUserType(Integer dates, String pkg, String version,
        String source) {
        DwsDailyPkgVerUsrcAdConversionEvent dwsDailyPkgVerAdConversionEvent = new DwsDailyPkgVerUsrcAdConversionEvent();
        dwsDailyPkgVerAdConversionEvent.setDates(dates);
        dwsDailyPkgVerAdConversionEvent.setPkg(pkg);
        dwsDailyPkgVerAdConversionEvent.setVersion(version);
        dwsDailyPkgVerAdConversionEvent.setUserType(1);
        dwsDailyPkgVerAdConversionEvent.setUserSource(source);
        return dwsDailyPkgVerAdConversionEvent;
    }

    public static DwsDailyPkgVerUsrcAdConversionEvent ofActiveUserType(Integer dates, String pkg, String version,
        String source) {
        DwsDailyPkgVerUsrcAdConversionEvent dwsDailyPkgVerAdConversionEvent = new DwsDailyPkgVerUsrcAdConversionEvent();
        dwsDailyPkgVerAdConversionEvent.setDates(dates);
        dwsDailyPkgVerAdConversionEvent.setPkg(pkg);
        dwsDailyPkgVerAdConversionEvent.setVersion(version);
        dwsDailyPkgVerAdConversionEvent.setUserType(0);
        dwsDailyPkgVerAdConversionEvent.setUserSource(source);
        return dwsDailyPkgVerAdConversionEvent;
    }

    public void updateAdClick(long userCount, int eventCount) {
        this.setClickUserNum(this.getClickUserNum() + userCount);
        this.setClickRecordNum(this.getClickRecordNum() + eventCount);
    }

    public void updateAdShow(long userCount, int eventCount) {
        this.setShowUserNum(this.getShowUserNum() + userCount);
        this.setShowRecordNum(this.getShowRecordNum() + eventCount);
    }
}
