package com.leo.ad.codriver.dwd.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dwd_user_ltv_record")
public class DwdUserLtvRecord {

    private Long id;
    private Long dates;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private String type;
    private BigDecimal ltv;
    private Date createTime;

}
