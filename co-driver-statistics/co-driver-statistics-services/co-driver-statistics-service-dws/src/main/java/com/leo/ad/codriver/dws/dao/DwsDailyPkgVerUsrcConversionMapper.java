package com.leo.ad.codriver.dws.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcConversion;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_conversion】的数据库操作Mapper
 * @createDate 2024-11-19 16:43:30
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcConversion
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcConversionMapper extends BaseMapper<DwsDailyPkgVerUsrcConversion> {

}
