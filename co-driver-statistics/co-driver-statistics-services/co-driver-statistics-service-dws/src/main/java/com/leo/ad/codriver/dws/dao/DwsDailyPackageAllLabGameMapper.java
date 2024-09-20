package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabAd;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabGame;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DwsDailyPackageAdMapper
 *
 * @author HaiYinLong
 * @version 2024/08/28 18:26
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageAllLabGameMapper extends BaseMapper<DwsDailyPackageAllLabGame> {
    List<DwsDailyPackageAllLabGame> queryStatisticsAll(@Param("dates") Integer dates);

    List<DwsDailyPackageAllLabGame> queryStatisticsNew(@Param("dates") Integer dates);

    void deleteByDates(@Param("dates") Integer dates);
}
