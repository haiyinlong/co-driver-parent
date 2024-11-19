package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAd;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_ad】的数据库操作Mapper
 * @createDate 2024-11-19 16:43:31
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAd
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcAdMapper extends BaseMapper<DwsDailyPkgVerUsrcAd> {

    List<DwsDailyPkgVerUsrcAd> queryStatisticsActive(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcAd> queryStatisticsNew(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcAd> queryDbList(@Param("dates") Integer dates);
}
