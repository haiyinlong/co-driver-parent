package com.leo.ad.codriver.ads.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("ads_daily_game_oeta_report")
public class AdsDailyLabGameOetaReport implements BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long dates; // 日期

    private String version; // 版本

    private String pkg; // 包名

    private Long userType; // 用户类型

    private String configGroupType;
    private String configGroupName;

    private Long newUserNum; // 新增

    private Long secondUserNum; // 次留

    private Long tertiaryUserNum; // 三日留

    private Long activeUserNum; // 活跃

    private Long avgUserOnlineTime; // 人均时长

    private Long introUserCount; // 入门游戏用户

    private BigDecimal introUserRate; // 入门游戏渗透率

    private BigDecimal introGameReward; // 入门游戏发放奖励

    private BigDecimal introAvgGameReward; // 入门游戏人均奖励

    private Long turnUserCount; // 转盘用户

    private Long turnAvgGameCount; // 转盘人均次数

    private BigDecimal turnUserRate; // 转盘渗透率

    private BigDecimal turnGameConsume; // 转盘消耗

    private BigDecimal turnGameReward; // 转盘产出

    private BigDecimal turnAvgGameConsume; // 转盘人均消耗

    private BigDecimal turnAvgGameReward; // 转盘人均产出

    private Long miniExchangeChipCount; // 现金兑换筹码人数

    private Long miniExchangeChCount; // 筹码兑换现金人数

    private BigDecimal miniExchangeChAmount; // 现金兑换金额

    private BigDecimal miniExchangeChipAmount; // 筹码兑换金额

    private Long miniUserCount; // 小游戏用户

    private Long miniAvgGameCount; // 小游戏平均局数

    private Long miniAvgGameTime; // 小游戏人均时长

    private BigDecimal miniUserRate; // 小游戏渗透率

    private BigDecimal miniGameConsume; // 小游戏投注

    private BigDecimal miniAvgGameConsume; // 小游戏人均投注

    private BigDecimal miniGameReward; // 小游戏奖励

    private BigDecimal miniAvgGameReward; // 小游戏人均奖励

    private BigDecimal miniRtp; // 小游戏rtp

    private Date createTime; // 创建时间

    public void init() {
        this.createTime = new Date();
    }

}
