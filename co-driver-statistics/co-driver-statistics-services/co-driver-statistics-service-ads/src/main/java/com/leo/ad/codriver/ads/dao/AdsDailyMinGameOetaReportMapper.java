package com.leo.ad.codriver.ads.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyMinGameOetaReport;

/**
 * AdsDailyMinGameOetaReportMapper
 *
 * @author HaiYinLong
 * @version 2024/09/11 18:33
 **/
@Mapper
@DS("mysql")
public interface AdsDailyMinGameOetaReportMapper extends BaseMapper<AdsDailyMinGameOetaReport> {
    List<AdsDailyMinGameOetaReport> queryActiveUserList(@Param("dates") Integer dates);

    List<AdsDailyMinGameOetaReport> queryNewUserList(@Param("dates") Integer dates);

    List<AdsDailyMinGameOetaReport> queryList(@Param("dates") Integer dates);
}
