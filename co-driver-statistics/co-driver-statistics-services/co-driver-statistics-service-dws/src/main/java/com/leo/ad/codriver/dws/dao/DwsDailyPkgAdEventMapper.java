package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgAdEvent;

/**
 * DwsDailyPkgAdEventMapper
 *
 * @author HaiYinLong
 * @version 2024/09/18 16:49
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPkgAdEventMapper extends BaseMapper<DwsDailyPkgAdEvent> {
    List<DwsDailyPkgAdEvent> queryList(@Param("dates") Integer dates);

    List<DwsDailyPkgAdEvent> queryStatisticsActive(@Param("dates") Integer dates);

    List<DwsDailyPkgAdEvent> queryStatisticsNew(@Param("dates") Integer dates);
}
