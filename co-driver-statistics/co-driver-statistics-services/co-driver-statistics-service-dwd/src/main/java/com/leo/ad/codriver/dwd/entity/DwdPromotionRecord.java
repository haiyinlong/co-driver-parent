package com.leo.ad.codriver.dwd.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dwd_promotion_record")
public class DwdPromotionRecord implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private String pkg;
    private String network;
    private String series;
    private String adGroup;
    private String material;
    private BigDecimal cost;
    private BigDecimal changeCost;
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
