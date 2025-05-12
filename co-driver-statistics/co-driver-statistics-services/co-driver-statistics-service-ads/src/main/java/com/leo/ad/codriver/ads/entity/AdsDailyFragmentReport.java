package com.leo.ad.codriver.ads.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * ads每日包碎片
 *
 * @TableName ads_daily_fragment_report
 */
@TableName(value = "ads_daily_fragment_report")
@Data
public class AdsDailyFragmentReport implements BaseEntity {
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
     * 碎片
     */
    private Integer fragment;

    /**
     * 产出数量
     */
    private Integer fragmentOutputNum;

    /**
     * 消耗数量
     */
    private Integer fragmentExpendNum;

    /**
     * 用户碎片剩余数量
     */
    private Integer fragmentEffectiveNum;

    /**
     * 碎片拥有人数
     */
    private Integer fragmentUserNum;

    /**
     * 碎片人均数量
     */
    private BigDecimal fragmentPerCapitaNum;

    /**
     * 碎片1-100的人数
     */
    private Integer fragment1To100UserNum;

    /**
     * 碎片1-100的人数占比
     */
    private BigDecimal fragment1To100UserRatio;

    /**
     * 碎片100-300的人数
     */
    private Integer fragment100To300UserNum;

    /**
     * 碎片100-300的人数占比
     */
    private BigDecimal fragment100To300UserRatio;

    /**
     * 碎片300-500的人数
     */
    private Integer fragment300To500UserNum;

    /**
     * 碎片300-500的人数占比
     */
    private BigDecimal fragment300To500UserRatio;

    /**
     * 碎片500-700的人数
     */
    private Integer fragment500To700UserNum;

    /**
     * 碎片500-700的人数占比
     */
    private BigDecimal fragment500To700UserRatio;

    /**
     * 碎片700-800的人数
     */
    private Integer fragment700To800UserNum;

    /**
     * 碎片700-800的人数占比
     */
    private BigDecimal fragment700To800UserRatio;

    /**
     * 碎片800-900的人数
     */
    private Integer fragment800To900UserNum;

    /**
     * 碎片800-900的人数占比
     */
    private BigDecimal fragment800To900UserRatio;

    /**
     * 碎片9000-1000的人数
     */
    private Integer fragment900To1000UserNum;

    /**
     * 碎片900-1000的人数占比
     */
    private BigDecimal fragment900To1000UserRatio;

    /**
     * 碎片大于1000的人数
     */
    private Integer fragmentMoreThan1000UserNum;

    /**
     * 碎片大于1000的人数占比
     */
    private BigDecimal fragmentMoreThan1000UserRatio;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
