package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("dwd_user_balance_record")
public class DwdUserBalanceRecord implements BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String pkg;
    private String country;
    private BigDecimal balance;
    private BigDecimal n1;
    private BigDecimal n2;
    private BigDecimal points;
    private Integer transfer;
    private Date updateTime;

}
