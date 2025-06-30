package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgUsrcConversion;

/**
 * @author user
 * @description 针对表【dws_daily_cohort_pkg_usrc_conversion】的数据库操作Mapper
 * @createDate 2025-06-26 14:47:24
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgUsrcConversion
 */
@Mapper
@DS("mysql")
public interface DwsDailyCohortPkgUsrcConversionMapper extends BaseMapper<DwsDailyCohortPkgUsrcConversion> {

    List<DwsDailyCohortPkgUsrcConversion> queryDbListByDates(@Param("dates") Integer dates);
}
