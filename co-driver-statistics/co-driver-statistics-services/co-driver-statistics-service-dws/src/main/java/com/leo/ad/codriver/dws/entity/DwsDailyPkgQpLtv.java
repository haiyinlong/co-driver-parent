package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_pkg_qp_ltv")
public class DwsDailyPkgQpLtv implements BaseEntity {

    private Long id;
    private Long dates;
    private String pkg;
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
