package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * dwd用户充值明细表
 *
 * @TableName dwd_user_payment_record
 */
@Data
@TableName("dwd_user_payment_record")
public class DwdUserPaymentRecord implements BaseEntity {
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
     * 来源id
     */
    private Long sourceId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 对应金额(卢比)
     */
    private BigDecimal amount;

    /**
     * 充值手续费(卢比)
     */
    private BigDecimal fee;

    /**
     * 卢比转美元汇率
     */
    private BigDecimal exchangeRate;

    /**
     * 转换充值金额(美元)
     */
    private BigDecimal changeAmount;

    /**
     * 转换充值手续费(美元)
     */
    private BigDecimal changeFee;

    /**
     * 0充值中; 1充值成功; 2充值失败
     */
    private Integer status;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 国家
     */
    private String country;

    /**
     * 注册日期
     */
    private Date registerDate;

    /**
     * 注册日期
     */
    private Integer registerDates;

    /**
     * 注册时版本
     */
    private String registerVersion;

    /**
     * 注册天（自然天）
     */
    private Integer registerDay;

    /**
     * 注册天（同期天）
     */
    private Integer registerCohortDay;

    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;
    private Integer updateDates;
}