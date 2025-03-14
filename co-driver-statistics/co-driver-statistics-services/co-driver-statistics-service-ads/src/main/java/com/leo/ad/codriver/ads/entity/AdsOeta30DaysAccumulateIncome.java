package com.leo.ad.codriver.ads.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leo.ad.codriver.starter.mysql.entity.BaseEntity;

import lombok.Data;

/**
 * ads90天,每日oeta 累计收入和消耗
 *
 * @TableName ads_oeta_30_days_accumulate_income
 */
@TableName(value = "ads_oeta_30_days_accumulate_income")
@Data
public class AdsOeta30DaysAccumulateIncome implements BaseEntity, Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日期注册
     */
    private Integer dates;

    /**
     * 包名
     */
    private String pkg;

    /**
     * 应用版本
     */
    private String version;

    /**
     * 用户来源
     */
    private String userSource;

    /**
     * 新增用户数
     */
    private Long newUserNum;

    /**
     * ad总收入
     */
    @TableField("day_1_ad_income")
    private BigDecimal day1AdIncome;

    /**
     * 广告展示次数
     */
    @TableField("day_1_ad_show_num")
    private BigDecimal day1AdShowNum;

    /**
     * ecpm(广告收入/广告展示)x1000
     */
    @TableField("day_1_ad_reward_ecpm")
    private BigDecimal day1AdRewardEcpm;

    /**
     * 广告人均展示次数
     */
    @TableField("day_1_ad_avg_show_num")
    private BigDecimal day1AdAvgShowNum;

    /**
     * 提现金额
     */
    @TableField("day_1_withdraw_amount")
    private BigDecimal day1WithdrawAmount;

    /**
     * 提现手续费
     */
    @TableField("day_1_withdraw_fee")
    private BigDecimal day1WithdrawFee;

    /**
     * 提现成本(提现手续费+提现金额)
     */
    @TableField("day_1_withdraw_cost")
    private BigDecimal day1WithdrawCost;

    /**
     * 总收入 = 广告收入 - 提现成本
     */
    @TableField("day_1_total_income")
    private BigDecimal day1TotalIncome;

    /**
     * ad总收入
     */
    @TableField("day_2_ad_income")
    private BigDecimal day2AdIncome;

    /**
     * 广告展示次数
     */
    @TableField("day_2_ad_show_num")
    private BigDecimal day2AdShowNum;

    /**
     * ecpm(广告收入/广告展示)x1000
     */
    @TableField("day_2_ad_reward_ecpm")
    private BigDecimal day2AdRewardEcpm;

    /**
     * 广告人均展示次数
     */
    @TableField("day_2_ad_avg_show_num")
    private BigDecimal day2AdAvgShowNum;

    /**
     * 提现金额
     */
    @TableField("day_2_withdraw_amount")
    private BigDecimal day2WithdrawAmount;

    /**
     * 提现手续费
     */
    @TableField("day_2_withdraw_fee")
    private BigDecimal day2WithdrawFee;

    /**
     * 提现成本(提现手续费+提现金额)
     */
    @TableField("day_2_withdraw_cost")
    private BigDecimal day2WithdrawCost;

    /**
     * 总收入 = 广告收入 - 提现成本
     */
    @TableField("day_2_total_income")
    private BigDecimal day2TotalIncome;

    /**
     * ad总收入
     */
    @TableField("day_3_ad_income")
    private BigDecimal day3AdIncome;

    /**
     * 广告展示次数
     */
    @TableField("day_3_ad_show_num")
    private BigDecimal day3AdShowNum;

    /**
     * ecpm(广告收入/广告展示)x1000
     */
    @TableField("day_3_ad_reward_ecpm")
    private BigDecimal day3AdRewardEcpm;

    /**
     * 广告人均展示次数
     */
    @TableField("day_3_ad_avg_show_num")
    private BigDecimal day3AdAvgShowNum;

    /**
     * 提现金额
     */
    @TableField("day_3_withdraw_amount")
    private BigDecimal day3WithdrawAmount;

    /**
     * 提现手续费
     */
    @TableField("day_3_withdraw_fee")
    private BigDecimal day3WithdrawFee;

    /**
     * 提现成本(提现手续费+提现金额)
     */
    @TableField("day_3_withdraw_cost")
    private BigDecimal day3WithdrawCost;

    /**
     * 总收入 = 广告收入 - 提现成本
     */
    @TableField("day_3_total_income")
    private BigDecimal day3TotalIncome;

    /**
     * ad总收入
     */
    @TableField("day_4_ad_income")
    private BigDecimal day4AdIncome;

    /**
     * 广告展示次数
     */
    @TableField("day_4_ad_show_num")
    private BigDecimal day4AdShowNum;

    /**
     * ecpm(广告收入/广告展示)x1000
     */
    @TableField("day_4_ad_reward_ecpm")
    private BigDecimal day4AdRewardEcpm;

    /**
     * 广告人均展示次数
     */
    @TableField("day_4_ad_avg_show_num")
    private BigDecimal day4AdAvgShowNum;

    /**
     * 提现金额
     */
    @TableField("day_4_withdraw_amount")
    private BigDecimal day4WithdrawAmount;

    /**
     * 提现手续费
     */
    @TableField("day_4_withdraw_fee")
    private BigDecimal day4WithdrawFee;

    /**
     * 提现成本(提现手续费+提现金额)
     */
    @TableField("day_4_withdraw_cost")
    private BigDecimal day4WithdrawCost;

    /**
     * 总收入 = 广告收入 - 提现成本
     */
    @TableField("day_4_total_income")
    private BigDecimal day4TotalIncome;

    /**
     * ad总收入
     */
    @TableField("day_5_ad_income")
    private BigDecimal day5AdIncome;

    /**
     * 广告展示次数
     */
    @TableField("day_5_ad_show_num")
    private BigDecimal day5AdShowNum;

    /**
     * ecpm(广告收入/广告展示)x1000
     */
    @TableField("day_5_ad_reward_ecpm")
    private BigDecimal day5AdRewardEcpm;

    /**
     * 广告人均展示次数
     */
    @TableField("day_5_ad_avg_show_num")
    private BigDecimal day5AdAvgShowNum;

    /**
     * 提现金额
     */
    @TableField("day_5_withdraw_amount")
    private BigDecimal day5WithdrawAmount;

    /**
     * 提现手续费
     */
    @TableField("day_5_withdraw_fee")
    private BigDecimal day5WithdrawFee;

    /**
     * 提现成本(提现手续费+提现金额)
     */
    @TableField("day_5_withdraw_cost")
    private BigDecimal day5WithdrawCost;

    /**
     * 总收入 = 广告收入 - 提现成本
     */
    @TableField("day_5_total_income")
    private BigDecimal day5TotalIncome;
    @TableField("day_6_ad_income")
    private BigDecimal day6AdIncome;

    @TableField("day_6_ad_show_num")
    private BigDecimal day6AdShowNum;

    @TableField("day_6_ad_reward_ecpm")
    private BigDecimal day6AdRewardEcpm;

    @TableField("day_6_ad_avg_show_num")
    private BigDecimal day6AdAvgShowNum;

    @TableField("day_6_withdraw_amount")
    private BigDecimal day6WithdrawAmount;

    @TableField("day_6_withdraw_fee")
    private BigDecimal day6WithdrawFee;

    @TableField("day_6_withdraw_cost")
    private BigDecimal day6WithdrawCost;

    @TableField("day_6_total_income")
    private BigDecimal day6TotalIncome;

    // 继续为其他属性添加@TableField注解

    @TableField("day_7_ad_income")
    private BigDecimal day7AdIncome;

    @TableField("day_7_ad_show_num")
    private BigDecimal day7AdShowNum;

    @TableField("day_7_ad_reward_ecpm")
    private BigDecimal day7AdRewardEcpm;

    @TableField("day_7_ad_avg_show_num")
    private BigDecimal day7AdAvgShowNum;

    @TableField("day_7_withdraw_amount")
    private BigDecimal day7WithdrawAmount;

    @TableField("day_7_withdraw_fee")
    private BigDecimal day7WithdrawFee;

    @TableField("day_7_withdraw_cost")
    private BigDecimal day7WithdrawCost;

    @TableField("day_7_total_income")
    private BigDecimal day7TotalIncome;
    @TableField("day_8_ad_income")
    private BigDecimal day8AdIncome;

    @TableField("day_8_ad_show_num")
    private BigDecimal day8AdShowNum;

    @TableField("day_8_ad_reward_ecpm")
    private BigDecimal day8AdRewardEcpm;

    @TableField("day_8_ad_avg_show_num")
    private BigDecimal day8AdAvgShowNum;

    @TableField("day_8_withdraw_amount")
    private BigDecimal day8WithdrawAmount;

    @TableField("day_8_withdraw_fee")
    private BigDecimal day8WithdrawFee;

    @TableField("day_8_withdraw_cost")
    private BigDecimal day8WithdrawCost;

    @TableField("day_8_total_income")
    private BigDecimal day8TotalIncome;

    @TableField("day_9_ad_income")
    private BigDecimal day9AdIncome;

    @TableField("day_9_ad_show_num")
    private BigDecimal day9AdShowNum;

    @TableField("day_9_ad_reward_ecpm")
    private BigDecimal day9AdRewardEcpm;

    @TableField("day_9_ad_avg_show_num")
    private BigDecimal day9AdAvgShowNum;

    @TableField("day_9_withdraw_amount")
    private BigDecimal day9WithdrawAmount;

    @TableField("day_9_withdraw_fee")
    private BigDecimal day9WithdrawFee;

    @TableField("day_9_withdraw_cost")
    private BigDecimal day9WithdrawCost;

    @TableField("day_9_total_income")
    private BigDecimal day9TotalIncome;

    @TableField("day_10_ad_income")
    private BigDecimal day10AdIncome;

    @TableField("day_10_ad_show_num")
    private BigDecimal day10AdShowNum;

    @TableField("day_10_ad_reward_ecpm")
    private BigDecimal day10AdRewardEcpm;

    @TableField("day_10_ad_avg_show_num")
    private BigDecimal day10AdAvgShowNum;

    @TableField("day_10_withdraw_amount")
    private BigDecimal day10WithdrawAmount;

    @TableField("day_10_withdraw_fee")
    private BigDecimal day10WithdrawFee;

    @TableField("day_10_withdraw_cost")
    private BigDecimal day10WithdrawCost;

    @TableField("day_10_total_income")
    private BigDecimal day10TotalIncome;

    @TableField("day_11_ad_income")
    private BigDecimal day11AdIncome;

    @TableField("day_11_ad_show_num")
    private BigDecimal day11AdShowNum;

    @TableField("day_11_ad_reward_ecpm")
    private BigDecimal day11AdRewardEcpm;

    @TableField("day_11_ad_avg_show_num")
    private BigDecimal day11AdAvgShowNum;

    @TableField("day_11_withdraw_amount")
    private BigDecimal day11WithdrawAmount;

    @TableField("day_11_withdraw_fee")
    private BigDecimal day11WithdrawFee;

    @TableField("day_11_withdraw_cost")
    private BigDecimal day11WithdrawCost;

    @TableField("day_11_total_income")
    private BigDecimal day11TotalIncome;

    @TableField("day_12_ad_income")
    private BigDecimal day12AdIncome;

    @TableField("day_12_ad_show_num")
    private BigDecimal day12AdShowNum;

    @TableField("day_12_ad_reward_ecpm")
    private BigDecimal day12AdRewardEcpm;

    @TableField("day_12_ad_avg_show_num")
    private BigDecimal day12AdAvgShowNum;

    @TableField("day_12_withdraw_amount")
    private BigDecimal day12WithdrawAmount;

    @TableField("day_12_withdraw_fee")
    private BigDecimal day12WithdrawFee;

    @TableField("day_12_withdraw_cost")
    private BigDecimal day12WithdrawCost;

    @TableField("day_12_total_income")
    private BigDecimal day12TotalIncome;

    @TableField("day_13_ad_income")
    private BigDecimal day13AdIncome;

    @TableField("day_13_ad_show_num")
    private BigDecimal day13AdShowNum;

    @TableField("day_13_ad_reward_ecpm")
    private BigDecimal day13AdRewardEcpm;

    @TableField("day_13_ad_avg_show_num")
    private BigDecimal day13AdAvgShowNum;

    @TableField("day_13_withdraw_amount")
    private BigDecimal day13WithdrawAmount;

    @TableField("day_13_withdraw_fee")
    private BigDecimal day13WithdrawFee;

    @TableField("day_13_withdraw_cost")
    private BigDecimal day13WithdrawCost;

    @TableField("day_13_total_income")
    private BigDecimal day13TotalIncome;

    @TableField("day_14_ad_income")
    private BigDecimal day14AdIncome;

    @TableField("day_14_ad_show_num")
    private BigDecimal day14AdShowNum;

    @TableField("day_14_ad_reward_ecpm")
    private BigDecimal day14AdRewardEcpm;

    @TableField("day_14_ad_avg_show_num")
    private BigDecimal day14AdAvgShowNum;

    @TableField("day_14_withdraw_amount")
    private BigDecimal day14WithdrawAmount;

    @TableField("day_14_withdraw_fee")
    private BigDecimal day14WithdrawFee;

    @TableField("day_14_withdraw_cost")
    private BigDecimal day14WithdrawCost;

    @TableField("day_14_total_income")
    private BigDecimal day14TotalIncome;

    @TableField("day_15_ad_income")
    private BigDecimal day15AdIncome;

    @TableField("day_15_ad_show_num")
    private BigDecimal day15AdShowNum;

    @TableField("day_15_ad_reward_ecpm")
    private BigDecimal day15AdRewardEcpm;

    @TableField("day_15_ad_avg_show_num")
    private BigDecimal day15AdAvgShowNum;

    @TableField("day_15_withdraw_amount")
    private BigDecimal day15WithdrawAmount;

    @TableField("day_15_withdraw_fee")
    private BigDecimal day15WithdrawFee;

    @TableField("day_15_withdraw_cost")
    private BigDecimal day15WithdrawCost;

    @TableField("day_15_total_income")
    private BigDecimal day15TotalIncome;

    @TableField("day_16_ad_income")
    private BigDecimal day16AdIncome;

    @TableField("day_16_ad_show_num")
    private BigDecimal day16AdShowNum;

    @TableField("day_16_ad_reward_ecpm")
    private BigDecimal day16AdRewardEcpm;

    @TableField("day_16_ad_avg_show_num")
    private BigDecimal day16AdAvgShowNum;

    @TableField("day_16_withdraw_amount")
    private BigDecimal day16WithdrawAmount;

    @TableField("day_16_withdraw_fee")
    private BigDecimal day16WithdrawFee;

    @TableField("day_16_withdraw_cost")
    private BigDecimal day16WithdrawCost;

    @TableField("day_16_total_income")
    private BigDecimal day16TotalIncome;

    @TableField("day_17_ad_income")
    private BigDecimal day17AdIncome;

    @TableField("day_17_ad_show_num")
    private BigDecimal day17AdShowNum;

    @TableField("day_17_ad_reward_ecpm")
    private BigDecimal day17AdRewardEcpm;

    @TableField("day_17_ad_avg_show_num")
    private BigDecimal day17AdAvgShowNum;

    @TableField("day_17_withdraw_amount")
    private BigDecimal day17WithdrawAmount;

    @TableField("day_17_withdraw_fee")
    private BigDecimal day17WithdrawFee;

    @TableField("day_17_withdraw_cost")
    private BigDecimal day17WithdrawCost;

    @TableField("day_17_total_income")
    private BigDecimal day17TotalIncome;

    @TableField("day_18_ad_income")
    private BigDecimal day18AdIncome;

    @TableField("day_18_ad_show_num")
    private BigDecimal day18AdShowNum;

    @TableField("day_18_ad_reward_ecpm")
    private BigDecimal day18AdRewardEcpm;

    @TableField("day_18_ad_avg_show_num")
    private BigDecimal day18AdAvgShowNum;

    @TableField("day_18_withdraw_amount")
    private BigDecimal day18WithdrawAmount;

    @TableField("day_18_withdraw_fee")
    private BigDecimal day18WithdrawFee;

    @TableField("day_18_withdraw_cost")
    private BigDecimal day18WithdrawCost;

    @TableField("day_18_total_income")
    private BigDecimal day18TotalIncome;

    @TableField("day_19_ad_income")
    private BigDecimal day19AdIncome;

    @TableField("day_19_ad_show_num")
    private BigDecimal day19AdShowNum;

    @TableField("day_19_ad_reward_ecpm")
    private BigDecimal day19AdRewardEcpm;

    @TableField("day_19_ad_avg_show_num")
    private BigDecimal day19AdAvgShowNum;

    @TableField("day_19_withdraw_amount")
    private BigDecimal day19WithdrawAmount;

    @TableField("day_19_withdraw_fee")
    private BigDecimal day19WithdrawFee;

    @TableField("day_19_withdraw_cost")
    private BigDecimal day19WithdrawCost;

    @TableField("day_19_total_income")
    private BigDecimal day19TotalIncome;
    @TableField("day_20_ad_income")
    private BigDecimal day20AdIncome;

    @TableField("day_20_ad_show_num")
    private BigDecimal day20AdShowNum;

    @TableField("day_20_ad_reward_ecpm")
    private BigDecimal day20AdRewardEcpm;

    @TableField("day_20_ad_avg_show_num")
    private BigDecimal day20AdAvgShowNum;

    @TableField("day_20_withdraw_amount")
    private BigDecimal day20WithdrawAmount;

    @TableField("day_20_withdraw_fee")
    private BigDecimal day20WithdrawFee;

    @TableField("day_20_withdraw_cost")
    private BigDecimal day20WithdrawCost;

    @TableField("day_20_total_income")
    private BigDecimal day20TotalIncome;

    @TableField("day_21_ad_income")
    private BigDecimal day21AdIncome;

    @TableField("day_21_ad_show_num")
    private BigDecimal day21AdShowNum;

    @TableField("day_21_ad_reward_ecpm")
    private BigDecimal day21AdRewardEcpm;

    @TableField("day_21_ad_avg_show_num")
    private BigDecimal day21AdAvgShowNum;

    @TableField("day_21_withdraw_amount")
    private BigDecimal day21WithdrawAmount;

    @TableField("day_21_withdraw_fee")
    private BigDecimal day21WithdrawFee;

    @TableField("day_21_withdraw_cost")
    private BigDecimal day21WithdrawCost;

    @TableField("day_21_total_income")
    private BigDecimal day21TotalIncome;

    @TableField("day_22_ad_income")
    private BigDecimal day22AdIncome;

    @TableField("day_22_ad_show_num")
    private BigDecimal day22AdShowNum;

    @TableField("day_22_ad_reward_ecpm")
    private BigDecimal day22AdRewardEcpm;

    @TableField("day_22_ad_avg_show_num")
    private BigDecimal day22AdAvgShowNum;

    @TableField("day_22_withdraw_amount")
    private BigDecimal day22WithdrawAmount;

    @TableField("day_22_withdraw_fee")
    private BigDecimal day22WithdrawFee;

    @TableField("day_22_withdraw_cost")
    private BigDecimal day22WithdrawCost;

    @TableField("day_22_total_income")
    private BigDecimal day22TotalIncome;

    @TableField("day_23_ad_income")
    private BigDecimal day23AdIncome;

    @TableField("day_23_ad_show_num")
    private BigDecimal day23AdShowNum;

    @TableField("day_23_ad_reward_ecpm")
    private BigDecimal day23AdRewardEcpm;

    @TableField("day_23_ad_avg_show_num")
    private BigDecimal day23AdAvgShowNum;

    @TableField("day_23_withdraw_amount")
    private BigDecimal day23WithdrawAmount;

    @TableField("day_23_withdraw_fee")
    private BigDecimal day23WithdrawFee;

    @TableField("day_23_withdraw_cost")
    private BigDecimal day23WithdrawCost;

    @TableField("day_23_total_income")
    private BigDecimal day23TotalIncome;

    @TableField("day_24_ad_income")
    private BigDecimal day24AdIncome;

    @TableField("day_24_ad_show_num")
    private BigDecimal day24AdShowNum;

    @TableField("day_24_ad_reward_ecpm")
    private BigDecimal day24AdRewardEcpm;

    @TableField("day_24_ad_avg_show_num")
    private BigDecimal day24AdAvgShowNum;

    @TableField("day_24_withdraw_amount")
    private BigDecimal day24WithdrawAmount;

    @TableField("day_24_withdraw_fee")
    private BigDecimal day24WithdrawFee;

    @TableField("day_24_withdraw_cost")
    private BigDecimal day24WithdrawCost;

    @TableField("day_24_total_income")
    private BigDecimal day24TotalIncome;

    @TableField("day_25_ad_income")
    private BigDecimal day25AdIncome;

    @TableField("day_25_ad_show_num")
    private BigDecimal day25AdShowNum;

    @TableField("day_25_ad_reward_ecpm")
    private BigDecimal day25AdRewardEcpm;

    @TableField("day_25_ad_avg_show_num")
    private BigDecimal day25AdAvgShowNum;

    @TableField("day_25_withdraw_amount")
    private BigDecimal day25WithdrawAmount;

    @TableField("day_25_withdraw_fee")
    private BigDecimal day25WithdrawFee;

    @TableField("day_25_withdraw_cost")
    private BigDecimal day25WithdrawCost;

    @TableField("day_25_total_income")
    private BigDecimal day25TotalIncome;

    @TableField("day_26_ad_income")
    private BigDecimal day26AdIncome;

    @TableField("day_26_ad_show_num")
    private BigDecimal day26AdShowNum;

    @TableField("day_26_ad_reward_ecpm")
    private BigDecimal day26AdRewardEcpm;

    @TableField("day_26_ad_avg_show_num")
    private BigDecimal day26AdAvgShowNum;

    @TableField("day_26_withdraw_amount")
    private BigDecimal day26WithdrawAmount;

    @TableField("day_26_withdraw_fee")
    private BigDecimal day26WithdrawFee;

    @TableField("day_26_withdraw_cost")
    private BigDecimal day26WithdrawCost;

    @TableField("day_26_total_income")
    private BigDecimal day26TotalIncome;

    @TableField("day_27_ad_income")
    private BigDecimal day27AdIncome;

    @TableField("day_27_ad_show_num")
    private BigDecimal day27AdShowNum;

    @TableField("day_27_ad_reward_ecpm")
    private BigDecimal day27AdRewardEcpm;

    @TableField("day_27_ad_avg_show_num")
    private BigDecimal day27AdAvgShowNum;

    @TableField("day_27_withdraw_amount")
    private BigDecimal day27WithdrawAmount;

    @TableField("day_27_withdraw_fee")
    private BigDecimal day27WithdrawFee;

    @TableField("day_27_withdraw_cost")
    private BigDecimal day27WithdrawCost;

    @TableField("day_27_total_income")
    private BigDecimal day27TotalIncome;

    @TableField("day_28_ad_income")
    private BigDecimal day28AdIncome;

    @TableField("day_28_ad_show_num")
    private BigDecimal day28AdShowNum;

    @TableField("day_28_ad_reward_ecpm")
    private BigDecimal day28AdRewardEcpm;

    @TableField("day_28_ad_avg_show_num")
    private BigDecimal day28AdAvgShowNum;

    @TableField("day_28_withdraw_amount")
    private BigDecimal day28WithdrawAmount;

    @TableField("day_28_withdraw_fee")
    private BigDecimal day28WithdrawFee;

    @TableField("day_28_withdraw_cost")
    private BigDecimal day28WithdrawCost;

    @TableField("day_28_total_income")
    private BigDecimal day28TotalIncome;

    @TableField("day_29_ad_income")
    private BigDecimal day29AdIncome;

    @TableField("day_29_ad_show_num")
    private BigDecimal day29AdShowNum;

    @TableField("day_29_ad_reward_ecpm")
    private BigDecimal day29AdRewardEcpm;

    @TableField("day_29_ad_avg_show_num")
    private BigDecimal day29AdAvgShowNum;

    @TableField("day_29_withdraw_amount")
    private BigDecimal day29WithdrawAmount;

    @TableField("day_29_withdraw_fee")
    private BigDecimal day29WithdrawFee;

    @TableField("day_29_withdraw_cost")
    private BigDecimal day29WithdrawCost;

    @TableField("day_29_total_income")
    private BigDecimal day29TotalIncome;

    @TableField("day_30_ad_income")
    private BigDecimal day30AdIncome;

    @TableField("day_30_ad_show_num")
    private BigDecimal day30AdShowNum;

    @TableField("day_30_ad_reward_ecpm")
    private BigDecimal day30AdRewardEcpm;

    @TableField("day_30_ad_avg_show_num")
    private BigDecimal day30AdAvgShowNum;

    @TableField("day_30_withdraw_amount")
    private BigDecimal day30WithdrawAmount;

    @TableField("day_30_withdraw_fee")
    private BigDecimal day30WithdrawFee;

    @TableField("day_30_withdraw_cost")
    private BigDecimal day30WithdrawCost;

    @TableField("day_30_total_income")
    private BigDecimal day30TotalIncome;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public String getUniqueKey() {
        return String.format("%s_%s_%s_%s", dates, pkg, version, userSource);
    }
}
