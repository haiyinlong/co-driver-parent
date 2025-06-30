package com.leo.ad.codriver.ads.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyUserQualityAnalyse;
import com.leo.ad.codriver.ads.entity.TempDailyUserQualityAnalyse;

/**
 * @author user
 * @description 针对表【ads_daily_user_quality_analyse】的数据库操作Mapper
 * @createDate 2025-06-30 11:38:16
 * @Entity com.leo.ad.codriver.ads.entity.AdsDailyUserQualityAnalyse
 */
@Mapper
@DS("mysql")
public interface AdsDailyUserQualityAnalyseMapper extends BaseMapper<AdsDailyUserQualityAnalyse> {

    List<AdsDailyUserQualityAnalyse> queryCohortUserAnalysePkgUsrcReportList(@Param("dates") Integer dates);

    List<TempDailyUserQualityAnalyse> queryCohortD1ToD7(@Param("dates") Integer dates);

    List<TempDailyUserQualityAnalyse> queryCohortD8ToD14(@Param("dates") Integer dates);

    List<TempDailyUserQualityAnalyse> queryCohortD15ToD30(@Param("dates") Integer dates);

}
