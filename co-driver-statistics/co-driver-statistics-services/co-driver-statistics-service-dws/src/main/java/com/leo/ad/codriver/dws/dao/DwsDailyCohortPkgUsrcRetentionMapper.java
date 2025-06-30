package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgUsrcRetention;

/**
 * @author user
 * @description 针对表【dws_daily_cohort_pkg_usrc_retention】的数据库操作Mapper
 * @createDate 2025-06-26 14:47:24
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgUsrcRetention
 */
@Mapper
@DS("mysql")
public interface DwsDailyCohortPkgUsrcRetentionMapper extends BaseMapper<DwsDailyCohortPkgUsrcRetention> {

    List<DwsDailyCohortPkgUsrcRetention> queryDbListByDates(@Param("dates") Integer dates);
}
