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

    List<AdsDailyOetaBaseReport> selectActiveUserList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> selectNewUserList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> queryOetaBaseReportList(@Param("dates") Integer dates);
}
