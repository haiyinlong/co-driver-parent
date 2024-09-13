package com.leo.ad.codriver.ads.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyLabMiniGameOetaReport;

/**
 * AdsDailyLabMinGameOetaReportMapper
 *
 * @author HaiYinLong
 * @version 2024/09/11 18:33
 **/
@Mapper
@DS("mysql")
public interface AdsDailyLabMiniGameOetaReportMapper extends BaseMapper<AdsDailyLabMiniGameOetaReport> {
    List<AdsDailyLabMiniGameOetaReport> queryActiveUserList(@Param("dates") Integer dates);

    List<AdsDailyLabMiniGameOetaReport> queryNewUserList(@Param("dates") Integer dates);

    List<AdsDailyLabMiniGameOetaReport> queryList(@Param("dates") Integer dates);
}
