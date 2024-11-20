package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcAd;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_ad】的数据库操作Mapper
 * @createDate 2024-11-20 17:07:05
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcAd
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgUsrcAdMapper extends BaseMapper<DwsDailyPkgUsrcAd> {

    List<DwsDailyPkgUsrcAd> queryDbList(@Param("dates") Integer dates);

    List<DwsDailyPkgUsrcAd> queryStatisticsActive(@Param("dates") Integer dates);

    List<DwsDailyPkgUsrcAd> queryStatisticsNew(@Param("dates") Integer dates);
}
