package com.leo.ad.codriver.dws.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * dws每日广告来源用户事件统计
 *
 * @TableName dws_daily_pkg_usrc_ad_conversion_event
 */
@TableName(value = "dws_daily_pkg_usrc_ad_conversion_event")
@Data
public class DwsDailyPkgUsrcAdConversionEvent implements Serializable {
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
     * 用户类型：0活跃用户、1新用户
     */
    private Integer userType;

    /**
     * 用户来源
     */
    private String userSource;

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

    public DwsDailyPkgUsrcAdConversionEvent() {
        this.setClickUserNum(0L);
        this.setClickRecordNum(0L);
        this.setShowUserNum(0L);
        this.setShowRecordNum(0L);
        this.setCreateTime(new Date());
    }

    public static DwsDailyPkgUsrcAdConversionEvent ofNewUserType(Integer dates, String pkg, String source) {
        DwsDailyPkgUsrcAdConversionEvent dwsDailyPkgUsrcAdConversionEvent = new DwsDailyPkgUsrcAdConversionEvent();
        dwsDailyPkgUsrcAdConversionEvent.setPkg(pkg);
        dwsDailyPkgUsrcAdConversionEvent.setDates(dates);
        dwsDailyPkgUsrcAdConversionEvent.setUserSource(source);
        dwsDailyPkgUsrcAdConversionEvent.setUserType(1);
        return dwsDailyPkgUsrcAdConversionEvent;
    }

    public static DwsDailyPkgUsrcAdConversionEvent ofActiveUserType(Integer dates, String pkg, String source) {
        DwsDailyPkgUsrcAdConversionEvent dwsDailyPkgUsrcAdConversionEvent = new DwsDailyPkgUsrcAdConversionEvent();
        dwsDailyPkgUsrcAdConversionEvent.setPkg(pkg);
        dwsDailyPkgUsrcAdConversionEvent.setDates(dates);
        dwsDailyPkgUsrcAdConversionEvent.setUserSource(source);
        dwsDailyPkgUsrcAdConversionEvent.setUserType(0);
        return dwsDailyPkgUsrcAdConversionEvent;
    }

    public void updateAdClick(long userCount, int eventCount) {
        this.setClickUserNum(userCount);
        this.setClickRecordNum(this.getClickRecordNum() + eventCount);
    }

    public void updateAdShow(long userCount, int eventCount) {
        this.setShowUserNum(userCount);
        this.setShowRecordNum(this.getShowRecordNum() + eventCount);
    }
}
