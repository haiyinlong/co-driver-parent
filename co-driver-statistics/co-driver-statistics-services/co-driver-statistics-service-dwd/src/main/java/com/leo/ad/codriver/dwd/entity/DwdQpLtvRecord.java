package com.leo.ad.codriver.dwd.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dwd_qp_ltv_record")
public class DwdQpLtvRecord {

    private Long id;
    private Long dates;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private BigDecimal eventLtv;
    private String event;
    private BigDecimal userLtv;
    private Date createTime;

}
