package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName dws_daily_pkg_ver_usrc_share
 */
@TableName(value ="dws_daily_pkg_ver_usrc_share")
@Data
public class DwsDailyPkgVerUsrcShare implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 归因用户数量(自动邀请归因)
     */
    private Long invitationNum;

    /**
     * 填写编码用户数量
     */
    private Long fillCodeNum;

    /**
     * 总用户数量
     */
    private Long totalUserNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}