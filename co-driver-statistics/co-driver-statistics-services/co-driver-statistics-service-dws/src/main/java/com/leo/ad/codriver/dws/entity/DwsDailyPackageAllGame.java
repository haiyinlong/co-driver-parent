package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("dws_daily_package_all_game")
public class DwsDailyPackageAllGame implements BaseEntity {

    private Long id;
    private Long dates;
    private String version;
    private String pkg;
    private Long introUserCount;
    private Long introGameCount;
    private Long introGameTime;
    private BigDecimal introGameReward;
    private BigDecimal introGameConsume;
    private BigDecimal introAvgReward;
    private BigDecimal introAvgConsume;
    private BigDecimal introAvgLevel;
    private BigDecimal introAvgGameCount;
    private BigDecimal introRtp;
    private Long turnUserCount;
    private Long turnGameCount;
    private Long turnGameTime;
    private BigDecimal turnGameReward;
    private BigDecimal turnGameConsume;
    private BigDecimal turnAvgReward;
    private BigDecimal turnAvgConsume;
    private BigDecimal turnAvgLevel;
    private BigDecimal turnAvgGameCount;
    private BigDecimal turnRtp;
    private Long miniUserCount;
    private Long miniGameCount;
    private Long miniGameTime;
    private BigDecimal miniGameReward;
    private BigDecimal miniGameConsume;
    private BigDecimal miniAvgReward;
    private BigDecimal miniAvgConsume;
    private BigDecimal miniAvgLevel;
    private BigDecimal miniAvgGameCount;
    private BigDecimal miniRtp;
    private Date createTime;
    private int userType;

    public DwsDailyPackageAllGame() {
        this.introUserCount = 0L;
        this.introGameCount = 0L;
        this.introGameTime = 0L;
        this.introGameReward = BigDecimal.ZERO;
        this.introGameConsume = BigDecimal.ZERO;
        this.introAvgReward = BigDecimal.ZERO;
        this.introAvgConsume = BigDecimal.ZERO;
        this.introAvgLevel  = BigDecimal.ZERO;
        this.introAvgGameCount = BigDecimal.ZERO;
        this.introRtp = BigDecimal.ZERO;
        this.turnUserCount = 0L;
        this.turnGameCount = 0L;
        this.turnGameTime = 0L;
        this.turnGameReward = BigDecimal.ZERO;
        this.turnGameConsume = BigDecimal.ZERO;
        this.turnAvgReward = BigDecimal.ZERO;
        this.turnAvgConsume = BigDecimal.ZERO;
        this.turnAvgLevel   = BigDecimal.ZERO;
        this.turnAvgGameCount  = BigDecimal.ZERO;
        this.turnRtp = BigDecimal.ZERO;
        this.miniUserCount = 0L;
        this.miniGameCount = 0L;
        this.miniGameTime = 0L;
        this.miniGameReward = BigDecimal.ZERO;
        this.miniGameConsume = BigDecimal.ZERO;
        this.miniAvgReward = BigDecimal.ZERO;
        this.miniAvgConsume = BigDecimal.ZERO;
        this.miniAvgLevel  = BigDecimal.ZERO;
        this.miniAvgGameCount = BigDecimal.ZERO;
        this.miniRtp = BigDecimal.ZERO;
    }

    public String getUniqueId() {
        return String.format("%s_%s", pkg, version);
    }


}
