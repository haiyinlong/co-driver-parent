package com.leo.ad.codriver.dim.entity;

import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 用户来源
 *
 * @TableName dim_user_source
 */
@TableName(value = "dim_user_source")
@Data
public class DimUserSource {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    @JSONField(name = "user_id")
    private Long userId;

    /**
     *
     */
    private String network;

    /**
     *
     */
    private String series;

    /**
     *
     */
    @JSONField(name = "ad_group")
    private String adGroup;

    /**
     *
     */
    private String material;

    @JSONField(name = "create_time")
    private Date createTime;

    /**
     *
     */
    private String extraInfo;

    private Integer dates;

}
