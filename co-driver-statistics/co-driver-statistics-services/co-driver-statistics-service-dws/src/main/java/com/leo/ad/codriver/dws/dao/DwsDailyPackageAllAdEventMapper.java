package com.leo.ad.codriver.dws.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllAdEvent;

/**
 * DwsDailyPackageAllAdEventMapper
 *
 * @author HaiYinLong
 * @version 2024/09/18 16:49
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageAllAdEventMapper extends BaseMapper<DwsDailyPackageAllAdEvent> {
    // List<DwsDailyPackageAllAdEvent> queryList(@Param("dates") Integer dates);
    //
    // List<DwsDailyPackageAllAdEvent> queryStatisticsActive(@Param("dates") Integer dates);
    //
    // List<DwsDailyPackageAllAdEvent> queryStatisticsNew(@Param("dates") Integer dates);
}
