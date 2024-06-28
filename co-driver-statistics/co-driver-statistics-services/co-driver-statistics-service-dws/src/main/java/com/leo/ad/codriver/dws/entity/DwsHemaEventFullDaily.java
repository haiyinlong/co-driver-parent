package com.leo.ad.codriver.dws.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("dws_hema_event_full_daily")
public class DwsHemaEventFullDaily {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer dates;
    private String pkg;
    private String country;
    private String version;
    private Integer userType;
    private Long matchPageShowNum;
    private Long matchPageShowUserNum;
    private Long gameStartNum;
    private Long gameStartUserNum;
    private BigDecimal gameFinishTotalTime;
    private Long gameFinishNum;
    private Long gameFinishUserNum;
    private BigDecimal promotionTotalCoin;
    private Long promotionCoinNum;
    private Long promotionCoinUserNum;
    private BigDecimal promotionTotalLifeGain;
    private Long promotionLifeGainNum;
    private Long promotionLifeGainUserNum;
    private Long gameEnterOnlyNum;
    private Long gameEnterOnlyUserNum;
    private BigDecimal gameSlotsTotalBet;
    private Long gameSlotsBetNum;
    private Long gameSlotsBetUserNum;
    private Long showOfferPageNum;
    private Long showOfferPageUserNum;
    private Long clickTaskNum;
    private Long clickTaskUserNum;
    private Long webLoadingNum;
    private Long webLoadingUserNum;
    private Long downloadPagShowNum;
    private Long downloadPagShowUserNum;
    private Long downloadPagClickNum;
    private Long downloadPagClickUserNum;
    private Long showWithdrawPageNum;
    private Long showWithdrawPageUserNum;
    private BigDecimal goldGameSlotsSpinTotalBet;
    private Long goldGameSlotsSpinNum;
    private Long goldGameSlotsSpinUserNum;
    private Long goldGameEnterOnlyNum;
    private Long goldGameEnterOnlyUserNum;

}
