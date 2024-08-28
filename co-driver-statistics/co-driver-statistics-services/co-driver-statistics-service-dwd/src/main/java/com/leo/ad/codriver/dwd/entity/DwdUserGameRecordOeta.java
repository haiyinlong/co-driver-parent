package com.leo.ad.codriver.dwd.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * DwdUserGameRecordOeta
 *
 * @author HaiYinLong
 * @version 2024/07/02 15:01
 **/
@Data
@TableName("dwd_user_game_record_oeta")
public class DwdUserGameRecordOeta implements BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long dates;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private Long gameId;
    private String name;
    private String type;
    private Long playedTime;
    private Long gameResult;
    private Long rewardSource;
    private String rewardAccountType;
    private BigDecimal reward;
    private String expendAccountType;
    private BigDecimal expend;
    private Date createTime;
    private Date updateTime;

}
