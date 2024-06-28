package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("dws_hema_balance_full_daily")
public class DwsHemaBalanceFullDaily {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String pkg;
    private String country;
    private String version;
    private Long userType;
    private Long userNum;
    private BigDecimal totalBalance;
    private BigDecimal totalN1;
    private BigDecimal totalN2;
    private BigDecimal totalPoints;
    private Long balance3000To5000UserNum;
    private Long balance1000To3000UserNum;
    private Long balance0To1000UserNum;
    private Long balanceGreaterThan5000UserNum;

}
