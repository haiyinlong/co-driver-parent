package com.leo.ad.codriver.dws.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgFragmentSummary;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_fragment_summary(dws每日包碎片汇总)】的数据库操作Mapper
 * @createDate 2025-05-12 15:00:59
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgFragmentSummary
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgFragmentSummaryMapper extends BaseMapper<DwsDailyPkgFragmentSummary> {

    void deleteByDates(@Param("dates") Integer dates);

    DwCountDTO getDwdStatisticsCount(@Param("dates") Integer dates);
}
