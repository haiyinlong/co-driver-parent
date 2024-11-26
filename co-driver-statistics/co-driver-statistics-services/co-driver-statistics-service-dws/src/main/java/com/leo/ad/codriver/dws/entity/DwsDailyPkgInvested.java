package com.leo.ad.codriver.dws.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.common.util.BigDecimalUtils;
import com.leo.ad.codriver.dwd.entity.DwdPromotionRecord;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * dws推广花费
 *
 * @TableName dws_daily_pkg_invested
 */
@TableName(value = "dws_daily_pkg_invested")
@Data
public class DwsDailyPkgInvested implements BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期
     */
    private Integer dates;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 推广花费 卢比
     */
    private BigDecimal inrCost;

    /**
     * 推广花费(美元)
     */
    private BigDecimal dollarCost;

    /**
     * 创建时间
     */
    private Date createTime;

    public DwsDailyPkgInvested() {
        this.inrCost = BigDecimal.ZERO;
        this.dollarCost = BigDecimal.ZERO;
    }

    public static DwsDailyPkgInvested of(Integer dates, String pkg) {
        DwsDailyPkgInvested dwsDailyPkgInvested = new DwsDailyPkgInvested();
        dwsDailyPkgInvested.setDates(dates);
        dwsDailyPkgInvested.setPkg(pkg);
        dwsDailyPkgInvested.setCreateTime(new Date());
        return dwsDailyPkgInvested;
    }

    public void calculate(DwdPromotionRecord promotionRecord) {
        this.setInrCost(BigDecimalUtils.add(this.getInrCost(), promotionRecord.getCost()));
        this.setDollarCost(BigDecimalUtils.add(this.getDollarCost(), promotionRecord.getChangeCost()));
    }

}
