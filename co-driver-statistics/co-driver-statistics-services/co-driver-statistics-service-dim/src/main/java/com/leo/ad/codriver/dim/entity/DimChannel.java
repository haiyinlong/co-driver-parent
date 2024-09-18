package com.leo.ad.codriver.dim.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dim_channel")
public class DimChannel implements BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long projectId;
    private Long productId;
    private String productName;
    private String channelId;
    private String channelName;
    private String pkg;

}
