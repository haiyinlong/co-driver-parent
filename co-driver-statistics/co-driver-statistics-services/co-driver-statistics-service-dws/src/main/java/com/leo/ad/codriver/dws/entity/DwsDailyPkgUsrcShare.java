package com.leo.ad.codriver.dws.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 *
 * @TableName dws_daily_pkg_usrc_share
 */
@TableName(value = "dws_daily_pkg_usrc_share")
@Data
public class DwsDailyPkgUsrcShare implements BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

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
    private Long hasSubordinateUserNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public void init() {
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
