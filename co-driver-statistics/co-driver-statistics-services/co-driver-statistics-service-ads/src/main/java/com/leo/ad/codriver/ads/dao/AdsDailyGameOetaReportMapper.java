package com.leo.ad.codriver.ads.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyGameOetaReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AdsGameLevelFullDailyMapper
 *
 * @author HaiYinLong
 * @version 2024/07/02 17:34
 **/
@Mapper
@DS("mysql")
public interface AdsDailyGameOetaReportMapper extends BaseMapper<AdsDailyGameOetaReport> {

    List<AdsDailyGameOetaReport> queryStatisticsActiveList(@Param("dates") Integer dates);

    List<AdsDailyGameOetaReport> queryStatisticsNewList(@Param("dates") Integer dates);


    List<AdsDailyGameOetaReport> queryList(@Param("dates")Integer dates);
}
