package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgShare;

/**
 * @author user
 * @description 针对表【dws_daily_cohort_pkg_share】的数据库操作Mapper
 * @createDate 2025-06-26 14:47:24
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyCohortPkgShare
 */
@Mapper
@DS("mysql")
public interface DwsDailyCohortPkgShareMapper extends BaseMapper<DwsDailyCohortPkgShare> {

    List<DwsDailyCohortPkgShare> queryDbListByDates(@Param("dates") Integer dates);
}
