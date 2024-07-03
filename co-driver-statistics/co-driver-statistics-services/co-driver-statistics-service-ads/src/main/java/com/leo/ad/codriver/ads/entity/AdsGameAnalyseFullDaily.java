package com.leo.ad.codriver.ads.entity;

/**
 * AdsGameLevelFullDaily
 *
 * @author HaiYinLong
 * @version 2024/07/02 16:37
 **/

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("ads_game_analyse_full_daily")
public class AdsGameAnalyseFullDaily implements BaseEntity {

    private Long id;
    private Integer dates;
    private String channelId;
    private String channelName;
    private String pkg;
    private Integer userType;
    private Integer newUserNum;
    private Integer secondUserNum;
    private Integer tertiaryUerNum;
    private Integer activeUserNum;
    private Integer minorGameUserNum;
    private BigDecimal minorGamePenetrationRate;
    private BigDecimal minorGameLevelPerCapita;
    private BigDecimal minorGameSendAmount;
    private BigDecimal minorAmountPerCapita;
    private BigDecimal minorGameTimePerCapita;

}
