package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * dws每日包碎片汇总
 *
 * @TableName dws_daily_pkg_fragment_summary
 */
@TableName(value = "dws_daily_pkg_fragment_summary")
@Data
public class DwsDailyPkgFragmentSummary implements BaseEntity {
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
     * 碎片数量
     */
    private Integer fragmentNum;

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

    public DwsDailyPkgFragmentSummary() {}

    public DwsDailyPkgFragmentSummary(Integer dates, Integer fragment) {
        this.dates = dates;
        this.fragment = fragment;
        this.fragmentNum = 0;
        this.fragmentUserNum = 0;
        this.fragmentPerCapitaNum = BigDecimal.ZERO;
        this.fragment1To100UserNum = 0;
        this.fragment1To100UserRatio = BigDecimal.ZERO;
        this.fragment100To300UserNum = 0;
        this.fragment100To300UserRatio = BigDecimal.ZERO;
        this.fragment300To500UserNum = 0;
        this.fragment300To500UserRatio = BigDecimal.ZERO;
        this.fragment500To700UserNum = 0;
        this.fragment500To700UserRatio = BigDecimal.ZERO;
        this.fragment700To800UserNum = 0;
        this.fragment700To800UserRatio = BigDecimal.ZERO;
        this.fragment800To900UserNum = 0;
        this.fragment800To900UserRatio = BigDecimal.ZERO;
        this.fragment900To1000UserNum = 0;
        this.fragment900To1000UserRatio = BigDecimal.ZERO;
        this.fragmentMoreThan1000UserNum = 0;
        this.fragmentMoreThan1000UserRatio = BigDecimal.ZERO;
        this.createTime = LocalDateTime.now();
    }

    public static DwsDailyPkgFragmentSummary of(Integer dates, Integer goods) {
        return new DwsDailyPkgFragmentSummary(dates, goods);
    }

    public void updateStatistics(Integer num) {
        this.fragmentNum += num;
        this.fragmentUserNum += 1;
        if (this.fragmentNum <= 100) {
            this.fragment1To100UserNum += 1;
        } else if (this.fragmentNum <= 300) {
            this.fragment100To300UserNum += 1;
        } else if (this.fragmentNum <= 500) {
            this.fragment300To500UserNum += 1;
        } else if (this.fragmentNum <= 700) {
            this.fragment500To700UserNum += 1;
        } else if (this.fragmentNum <= 800) {
            this.fragment700To800UserNum += 1;
        } else if (this.fragmentNum <= 900) {
            this.fragment800To900UserNum += 1;
        } else if (this.fragmentNum <= 1000) {
            this.fragment900To1000UserNum += 1;
        } else {
            this.fragmentMoreThan1000UserNum += 1;
        }
        // 计算人均碎片数量
        this.fragmentPerCapitaNum =
            BigDecimalUtils.divide(BigDecimal.valueOf(this.fragmentNum), BigDecimal.valueOf(this.fragmentUserNum));
        // 计算占比
        this.fragment1To100UserRatio = BigDecimalUtils.divideReserved2(BigDecimal.valueOf(this.fragment1To100UserNum),
            BigDecimal.valueOf(this.fragmentUserNum));
        this.fragment100To300UserRatio = BigDecimalUtils.divideReserved2(
            BigDecimal.valueOf(this.fragment100To300UserNum), BigDecimal.valueOf(this.fragmentUserNum));
        this.fragment300To500UserRatio = BigDecimalUtils.divideReserved2(
            BigDecimal.valueOf(this.fragment300To500UserNum), BigDecimal.valueOf(this.fragmentUserNum));
        this.fragment500To700UserRatio = BigDecimalUtils.divideReserved2(
            BigDecimal.valueOf(this.fragment500To700UserNum), BigDecimal.valueOf(this.fragmentUserNum));
        this.fragment700To800UserRatio = BigDecimalUtils.divideReserved2(
            BigDecimal.valueOf(this.fragment700To800UserNum), BigDecimal.valueOf(this.fragmentUserNum));
        this.fragment800To900UserRatio = BigDecimalUtils.divideReserved2(
            BigDecimal.valueOf(this.fragment800To900UserNum), BigDecimal.valueOf(this.fragmentUserNum));
        this.fragment900To1000UserRatio = BigDecimalUtils.divideReserved2(
            BigDecimal.valueOf(this.fragment900To1000UserNum), BigDecimal.valueOf(this.fragmentUserNum));
        this.fragmentMoreThan1000UserRatio = BigDecimalUtils.divideReserved2(
            BigDecimal.valueOf(this.fragmentMoreThan1000UserNum), BigDecimal.valueOf(this.fragmentUserNum));
    }
}
