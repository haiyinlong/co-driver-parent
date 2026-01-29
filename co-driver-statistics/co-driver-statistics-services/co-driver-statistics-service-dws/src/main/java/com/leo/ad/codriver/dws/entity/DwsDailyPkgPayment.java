package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * dws每日充值数据汇总
 *
 */
@TableName(value = "dws_daily_pkg_payment")
@Data
public class DwsDailyPkgPayment implements BaseEntity {
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
     * 用户类型：活跃用户、新用户
     */
    private Integer userType;

    /**
     * 充值总数量
     */
    private Long totalRecordNum;

    /**
     * 充值用户数
     */
    private Long totalUserNum;

    /**
     * 充值中数量
     */
    private Long processRecordNum;

    /**
     * 充值中用户数
     */
    private Long processUserNum;

    /**
     * 成功充值金额
     */
    private BigDecimal successAmount;

    /**
     * 成功充值手续费
     */
    private BigDecimal successFee;

    /**
     * 成功充值金额(美元)
     */
    private BigDecimal successChangeAmount;

    /**
     * 成功充值手续费(美元)
     */
    private BigDecimal successChangeFee;

    /**
     * 成功充值数量
     */
    private Long successRecordNum;

    /**
     * 成功充值用户数
     */
    private Long successUserNum;

    /**
     * 充值失败记录数
     */
    private Long failedRecordNum;

    /**
     * 充值失败用户数
     */
    private Long failedUserNum;
}