package com.leo.ad.codriver.dwd.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * DwdUserGameRecord
 *
 * @author HaiYinLong
 * @version 2024/07/02 15:01
 **/
@Data
@TableName("dwd_user_game_record_oeta")
public class DwdUserGameRecordOeta implements BaseEntity {

    private Long id;
    private Long sourceId;
    private Long dates;
    private Long userId;
    private String version;
    private String pkg;
    private String country;
    private Long pkgId;
    private Long gameId;
    private String gameCode;
    private Long playedTime;
    private Long gameResult;
    private Long rewardSource;
    private String rewardAccountType;
    private BigDecimal reward;
    private String expendAccountType;
    private BigDecimal expend;
    private Date sourceCreateTime;
    private Date sourceUpdateTime;
    private Date createTime;

    public void init() {
        if (ObjectUtils.isEmpty(this.createTime)) {
            this.createTime = new Date();
        }
    }
}
