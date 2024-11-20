package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcRetention;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_retention】的数据库操作Mapper
 * @createDate 2024-11-20 17:07:05
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcRetention
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgUsrcRetentionMapper extends BaseMapper<DwsDailyPkgUsrcRetention> {

    List<DwsDailyPkgUsrcRetention> queryDbList(@Param("dates") Integer dates);

    List<DwsDailyPkgUsrcRetention> queryStatisticsList(@Param("dates") Integer dates);
}
