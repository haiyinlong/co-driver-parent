package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * dws每日充值数据汇总
 *
 * @TableName dws_daily_pkg_ver_payment
 */
@TableName(value = "dws_daily_pkg_ver_payment")
@Data
public class DwsDailyPkgVerPayment implements BaseEntity {
    /**
     * 主键id
     */
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
     * 应用版本
     */
    private String version;

    /**
     * 用户类型：活跃用户、新用户
     */
    private Integer userType;

    /**
     * 提现总数量
     */
    private Long totalRecordNum;

    /**
     * 提现用户数
     */
    private Long totalUserNum;

    /**
     * 提现中数量
     */
    private Long processRecordNum;

    /**
     * 提现中用户数
     */
    private Long processUserNum;

    /**
     * 成功提现金额
     */
    private BigDecimal successAmount;

    /**
     * 成功提现手续费
     */
    private BigDecimal successFee;

    /**
     * 成功提现金额(美元)
     */
    private BigDecimal successChangeAmount;

    /**
     * 成功提现手续费(美元)
     */
    private BigDecimal successChangeFee;

    /**
     * 成功提现数量
     */
    private Long successRecordNum;

    /**
     * 成功提现用户数
     */
    private Long successUserNum;

    /**
     * 提现失败记录数
     */
    private Long failedRecordNum;

    /**
     * 提现失败用户数
     */
    private Long failedUserNum;
}