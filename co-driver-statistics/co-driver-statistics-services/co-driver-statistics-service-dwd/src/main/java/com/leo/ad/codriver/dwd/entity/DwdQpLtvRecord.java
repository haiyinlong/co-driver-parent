package com.leo.ad.codriver.dwd.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("dwd_qp_ltv_record")
public class DwdQpLtvRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private BigDecimal eventLtv;
    private String event;
    private BigDecimal userLtv;
    private Integer sourceType;
    private Date createTime;

    @TableField(exist = false)
    private String userSource;
    @TableField(exist = false)
    private Integer cohortDay;
    @TableField(exist = false)
    private Integer registerDates;
}
