package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DwsPkgGameFullDaily
 *
 * @author HaiYinLong
 * @version 2024/07/02 16:56
 **/
@Data
@TableName("dws_pkg_game_full_daily")
public class DwsPkgGameFullDaily implements BaseEntity {
    private Long id;
    private Integer dates;
    private Integer userType;
    private String pkg;
    private Integer totalUserNum;
    private Integer totalRecordCount;
    private Integer passRecordCount;
    private Integer failRecordCount;
    private Integer gameTotalTime;
    /**
     * 游戏总发放金
     */
    private BigDecimal totalGrant;
    /**
     * 总消耗金
     */
    private BigDecimal totalConsumption;
}
