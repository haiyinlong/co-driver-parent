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
@TableName("ads_game_level_full_daily")
public class AdsGameLevelFullDaily implements BaseEntity {

    private Long id;
    private Integer dates;
    private String channelId;
    private String channelName;
    private String pkg;
    private Integer userType;
    private Integer gameLevel;
    private Integer userNum;
    private Integer recordCount;
    private BigDecimal avgRecordCount;
    private Integer passUserNum;
    private BigDecimal passRate;
    private Integer failUserNum;
    private BigDecimal failRate;

}
