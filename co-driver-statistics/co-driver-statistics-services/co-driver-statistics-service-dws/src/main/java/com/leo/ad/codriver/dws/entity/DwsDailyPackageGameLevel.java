package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DwsDailyPackageActiveGameLevel
 *
 * @author HaiYinLong
 * @version 2024/07/02 16:56
 **/
@Data
@TableName("dws_daily_package_game_level")
public class DwsDailyPackageGameLevel implements BaseEntity {
    private Long id;
    private Integer dates;
    private Integer userType;
    private String pkg;
    private Integer gameLevel;
    private Integer userNum;
    private Integer recordCount;
    private BigDecimal avgRecordCount;
    private Integer passUserNum;
    private BigDecimal passRate;
    private Integer failUserNum;
    private BigDecimal failRate;
    private Integer failRecordCount;
    private BigDecimal failRecordRate;

    public DwsDailyPackageGameLevel() {
        this.id = 0L;
        this.dates = 0;
        this.pkg = "";
        this.gameLevel = 0;
        this.userNum = 0;
        this.recordCount = 0;
        this.avgRecordCount = BigDecimal.ZERO;
        this.passUserNum = 0;
        this.passRate = BigDecimal.ZERO;
        this.failUserNum = 0;
        this.failRate = BigDecimal.ZERO;
        this.failRecordCount = 0;
        this.failRecordRate = BigDecimal.ZERO;
    }
}
