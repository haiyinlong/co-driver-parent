package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName dws_daily_pkg_ver_usrc_withdraw
 */
@TableName(value ="dws_daily_pkg_ver_usrc_withdraw")
@Data
public class DwsDailyPkgVerUsrcWithdraw implements Serializable {
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
     * 用户来源
     */
    private String userSource;

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
     * 成功提现金额含手续费
     */
    private BigDecimal successAmountFee;

    /**
     * 成功提现金额(美元)
     */
    private BigDecimal successChangeAmount;

    /**
     * 成功提现金额含手续费(美元)
     */
    private BigDecimal successChangeAmountFee;

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

    /**
     * aws提现金额
     */
    private BigDecimal awsAmount;

    /**
     * aws提现金额含手续费
     */
    private BigDecimal awsAmountFee;

    /**
     * aws成功提现手续费
     */
    private BigDecimal awsFee;

    /**
     * aws提现金额(美元)
     */
    private BigDecimal awsChangeAmount;

    /**
     * aws提现金额含手续费(美元)
     */
    private BigDecimal awsChangeAmountFee;

    /**
     * aws成功提现手续费(美元)
     */
    private BigDecimal awsChangeFee;

    /**
     * aws提现数量
     */
    private Long awsRecordNum;

    /**
     * aws提现用户数
     */
    private Long awsUserNum;

    /**
     * bank提现金额
     */
    private BigDecimal bankAmount;

    /**
     * bank提现金额含手续费
     */
    private BigDecimal bankAmountFee;

    /**
     * bank提现手续费
     */
    private BigDecimal bankFee;

    /**
     * bank提现金额(美元)
     */
    private BigDecimal bankChangeAmount;

    /**
     * bank提现金额含手续费(美元)
     */
    private BigDecimal bankChangeAmountFee;

    /**
     * bank成功提现手续费(美元)
     */
    private BigDecimal bankChangeFee;

    /**
     * bank提现数量
     */
    private Long bankRecordNum;

    /**
     * bank提现用户数
     */
    private Long bankUserNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}