package com.leo.ad.codriver.ads.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyMiniGameOetaReport;

/**
 * AdsDailyMinGameOetaReportMapper
 *
 * @author HaiYinLong
 * @version 2024/09/11 18:33
 **/
@Mapper
@DS("mysql")
public interface AdsDailyMiniGameOetaReportMapper extends BaseMapper<AdsDailyMiniGameOetaReport> {
    List<AdsDailyMiniGameOetaReport> queryActiveUserList(@Param("dates") Integer dates);

    List<AdsDailyMiniGameOetaReport> queryNewUserList(@Param("dates") Integer dates);

    List<AdsDailyMiniGameOetaReport> queryList(@Param("dates") Integer dates);
}
