package com.leo.ad.codriver.ads.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsOeta30DaysAccumulateIncome;

/**
 * @author user
 * @description 针对表【ads_oeta_30_days_accumulate_income(ads90天,每日oeta 累计收入和消耗)】的数据库操作Mapper
 * @createDate 2025-03-13 17:22:16
 * @Entity com.leo.ad.codriver.ads.entity.AdsOeta30DaysAccumulateIncome
 */
@Mapper
@DS("mysql")
public interface AdsOeta30DaysAccumulateIncomeMapper extends BaseMapper<AdsOeta30DaysAccumulateIncome> {

    List<AdsOeta30DaysAccumulateIncome> queryDbList(@Param("startDate") Integer startDate,
        @Param("dates") Integer dates);

    List<AdsOeta30DaysAccumulateIncome> statisticsPkgList(@Param("startDate") Integer startDate,
        @Param("dates") Integer dates);

    List<AdsOeta30DaysAccumulateIncome> statisticsPkgVerList(@Param("startDate") Integer startDate,
        @Param("dates") Integer dates);

    List<AdsOeta30DaysAccumulateIncome> statisticsPkgUsrcList(@Param("startDate") Integer startDate,
        @Param("dates") Integer dates);

    List<AdsOeta30DaysAccumulateIncome> statisticsPkgVersionUsrcList(@Param("startDate") Integer startDate,
        @Param("dates") Integer dates);
}
