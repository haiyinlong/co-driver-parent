package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dws_daily_package_qp_ltv")
public class DwsDailyPackageQpLtv {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private String pkg;
    private String version;
    private String country;
    private Long activeUserNum;
    private BigDecimal activeEventLtv;
    private BigDecimal activeUserLtv;
    private Long newUserNum;
    private BigDecimal newUserEventLtv;
    private BigDecimal newUserLtv;
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
