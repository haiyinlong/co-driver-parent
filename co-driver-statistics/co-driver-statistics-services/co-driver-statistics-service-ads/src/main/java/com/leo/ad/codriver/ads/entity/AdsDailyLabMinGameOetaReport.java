
package com.leo.ad.codriver.ads.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

@Data
@TableName("ads_daily_lab_min_game_oeta_report")
public class AdsDailyLabMinGameOetaReport implements BaseEntity {

    private Long id;
    private Integer dates;
    private String pkg;
    private String version;
    private Long userType;
    private String configGroupType;
    private String configGroupName;
    private Long newUserNum;
    private Long activeUserNum;
    private String gameCode;
    private Long gameUserNum;
    private BigDecimal gamePermeateRate;
    private BigDecimal avgUserPlayGameNum;
    private BigDecimal avgUserPlayTime;
    private BigDecimal totalExpend;
    private BigDecimal avgUserExpend;
    private BigDecimal totalReward;
    private BigDecimal avgUserReward;
    private BigDecimal rtp;
    private Date createTime;

    public void init() {
        this.createTime = new Date();
    }
}
