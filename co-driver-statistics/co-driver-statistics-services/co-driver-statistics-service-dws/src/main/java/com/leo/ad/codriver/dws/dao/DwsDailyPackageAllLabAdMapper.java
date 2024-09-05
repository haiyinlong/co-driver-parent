package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabAd;

/**
 * DwsDailyPackageAdMapper
 *
 * @author HaiYinLong
 * @version 2024/08/28 18:26
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageAllLabAdMapper extends BaseMapper<DwsDailyPackageAllLabAd> {
    List<DwsDailyPackageAllLabAd> queryStatisticsAll(@Param("dates") Integer dates);

    List<DwsDailyPackageAllLabAd> queryStatisticsNew(@Param("dates") Integer dates);

    void deleteByDates(@Param("dates") Integer dates);
}
