package com.leo.ad.codriver.ads.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsHemaDataAnalyseFullDaily;
import com.leo.ad.codriver.ads.entity.AdsHemaDataAnalyseFullDailyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AdsHemaDataAnalyseFullDailyMapper
 *
 * @author HaiYinLong
 * @version 2024/04/15 17:34
 **/
@Mapper
@DS("mysql")
public interface AdsHemaDataAnalyseFullDailyMapper extends BaseMapper<AdsHemaDataAnalyseFullDaily> {
    void deleteByDates(@Param("dates") Integer dates);

    List<AdsHemaDataAnalyseFullDailyDTO> queryStatisticsActiveList(@Param("dates") Integer dates);

    List<AdsHemaDataAnalyseFullDailyDTO> queryStatisticsNewList(@Param("dates") Integer dates);

}
