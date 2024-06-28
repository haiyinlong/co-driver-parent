package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("dws_withdraw_full_daily")
public class DwsWithdrawFullDaily {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String pkg;
    private String country;
    private String version;
    private Integer userType;
    private Long totalRecordNum;
    private Long totalUserNum;
    private Long processRecordNum;
    private Long processUserNum;
    private BigDecimal successAmount;
    private BigDecimal successFee;
    private BigDecimal successAmountFee;
    private BigDecimal successChangeAmount;
    private BigDecimal successChangeAmountFee;
    private BigDecimal successChangeFee;
    private Long successRecordNum;
    private Long successUserNum;
    private Long failedRecordNum;
    private Long failedUserNum;
    private BigDecimal awsAmount;
    private BigDecimal awsAmountFee;
    private BigDecimal awsFee;
    private BigDecimal awsChangeAmount;
    private BigDecimal awsChangeAmountFee;
    private BigDecimal awsChangeFee;
    private Long awsRecordNum;
    private Long awsUserNum;
    private BigDecimal bankAmount;
    private BigDecimal bankAmountFee;
    private BigDecimal bankFee;
    private BigDecimal bankChangeAmount;
    private BigDecimal bankChangeAmountFee;
    private BigDecimal bankChangeFee;
    private Long bankRecordNum;
    private Long bankUserNum;

}
