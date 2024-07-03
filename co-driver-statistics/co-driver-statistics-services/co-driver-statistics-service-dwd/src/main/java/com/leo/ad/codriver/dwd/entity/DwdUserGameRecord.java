package com.leo.ad.codriver.dwd.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DwdUserGameRecord
 *
 * @author HaiYinLong
 * @version 2024/07/02 15:01
 **/
@Data
@TableName("dwd_user_game_record")
public class DwdUserGameRecord implements BaseEntity {
    private Long id;
    private Integer dates;
    private Long userId;
    private String pkg;
    private Integer gameLevel;
    private Long gameTime;
    private Integer gameStatus;
    private Integer rewardConsumptionType;
    private BigDecimal rewardConsumptionAmount;
    private Integer amountType;
}
