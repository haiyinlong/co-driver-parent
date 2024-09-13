package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("dws_daily_package_all_lab_game_single")
public class DwsDailyPackageAllLabGameSingle implements BaseEntity {

    private Long id;
    private Integer dates;
    private String pkg;
    private String version;
    private Long userType;
    private Long gameId;
    private String gameCode;
    private String configGroupType;
    private String configGroupName;
    private Long userNum;
    private Long playGameNum;
    private Long playGameTotalTime;
    private BigDecimal avgUserPlayGameNum;
    private BigDecimal avgUserPlayTime;
    private BigDecimal totalExpend;
    private BigDecimal avgUserExpend;
    private BigDecimal totalReward;
    private BigDecimal avgUserReward;
    private BigDecimal rtp;
    private Date createTime;

    public void calculate() {
        this.avgUserPlayGameNum = BigDecimalUtils.divideReserved2(playGameNum, userNum);
        this.avgUserPlayTime = BigDecimalUtils.divideReserved2(playGameTotalTime, userNum);
        this.avgUserExpend = BigDecimalUtils.divide(totalExpend, userNum).setScale(3, RoundingMode.HALF_UP);
        this.avgUserReward = BigDecimalUtils.divide(totalReward, userNum).setScale(3, RoundingMode.HALF_UP);
        this.rtp = BigDecimalUtils.divide(totalReward, totalExpend).setScale(3, RoundingMode.HALF_UP);
        this.createTime = new Date();
    }

}
