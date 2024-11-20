package com.leo.ad.codriver.ads.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyOetaBaseReport;

/**
 * AdsDailyOetaBaseReportMapper
 *
 * @author HaiYinLong
 * @version 2024/09/01 11:07
 **/
@Mapper
@DS("mysql")
public interface AdsDailyOetaBaseReportMapper extends BaseMapper<AdsDailyOetaBaseReport> {

    List<AdsDailyOetaBaseReport> selectActiveUserVersionList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> selectNewUserVersionList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> queryOetaBaseReportList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> selectActiveUserPkgList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> selectNewUserAllPkgList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> selectActiveUsrcList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> selectNewUsrcList(@Param("dates") Integer dates);
}
