package com.leo.ad.codriver.dwd.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dwd_user_withdraw_record")
public class DwdUserWithdrawRecord implements BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private Long sourceId;
    private String projectName;
    private Long userId;
    private String withdrawType;
    private BigDecimal amount;
    private BigDecimal fee;
    private BigDecimal exchangeRate;
    private BigDecimal changeAmount;
    private BigDecimal changeFee;
    private BigDecimal changeTotalAmount;
    private Long status;
    private String version;
    private String pkg;
    private String country;
    private Date registerDate;
    private Integer registerDates;
    private String registerVersion;
    private Integer registerDay;
    private Long registerCohortDay;
    private Date createTime;
    private Integer updateDates;
    private Integer registerWithdrawDay;
    private Integer registerWithdrawCohortDay;
    private Date updateTime;
    @TableField(exist = false)
    private String userSource;
}
