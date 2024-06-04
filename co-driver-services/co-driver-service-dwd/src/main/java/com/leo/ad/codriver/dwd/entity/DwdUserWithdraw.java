package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("dwd_user_withdraw")
public class DwdUserWithdraw {

    private Long id;
    private Long dates;
    private Long userId;
    private String version;
    private String withdrawType;
    private BigDecimal amount;
    private BigDecimal fee;
    private BigDecimal exchangeRate;
    private BigDecimal changeAmount;
    private BigDecimal changeFee;
    private Date createTime;
    private Long status;
    private Date updated;
    private Long withdrawNum;

}
