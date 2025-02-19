package com.leo.ad.codriver.ods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ods_event_report
 */
@TableName(value ="ods_event_report")
@Data
public class OdsEventReport implements Serializable {
    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private String gaid;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 日期
     */
    private Integer dates;

    /**
     * 事件id
     */
    private String eventId;

    /**
     * 事件描述
     */
    private String eventMsg;

    /**
     * 
     */
    private String userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}