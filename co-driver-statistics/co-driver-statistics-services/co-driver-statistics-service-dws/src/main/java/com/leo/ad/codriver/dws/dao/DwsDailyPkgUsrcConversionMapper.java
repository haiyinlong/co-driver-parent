package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcConversion;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_conversion】的数据库操作Mapper
 * @createDate 2024-11-20 17:07:05
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcConversion
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgUsrcConversionMapper extends BaseMapper<DwsDailyPkgUsrcConversion> {

    List<DwsDailyPkgUsrcConversion> queryActiveList(@Param("dates") Integer dates);

    List<DwsDailyPkgUsrcConversion> queryNewList(@Param("dates") Integer dates);

    List<DwsDailyPkgUsrcConversion> queryDbList(@Param("dates") Integer dates);
}
