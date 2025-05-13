package com.leo.ad.codriver.dws.entity;

import java.io.Serial;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * dws每日包碎片记录汇总
 *
 * @TableName dws_daily_pkg_fragment_transaction_summary
 */
@TableName(value = "dws_daily_pkg_fragment_transaction_summary")
@Data
public class DwsDailyPkgFragmentTransactionSummary implements BaseEntity {
    @Serial
    private static final long serialVersionUID = 4806332912401507300L;
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

    private Integer fragmentUserNum;

    /**
     * 产出数量
     */
    private Integer outputNum;

    /**
     * 消耗数量
     */
    private Integer expendNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public DwsDailyPkgFragmentTransactionSummary() {}

    public DwsDailyPkgFragmentTransactionSummary(Integer dates, String pkg, Integer fragment) {
        this.dates = dates;
        this.pkg = pkg;
        this.fragment = fragment;
        this.fragmentUserNum = 0;
        this.createTime = LocalDateTime.now();
        this.outputNum = 0;
        this.expendNum = 0;
    }

    public static DwsDailyPkgFragmentTransactionSummary of(Integer dates, String pkg, Integer goods) {
        return new DwsDailyPkgFragmentTransactionSummary(dates, pkg, goods);
    }

    public void updateStatistics(Integer recordType, Integer num) {
        if (recordType == 1) {
            this.outputNum += num;
        } else {
            this.expendNum += num;
        }
        this.fragmentUserNum += 1;
        this.createTime = LocalDateTime.now();
    }
}
