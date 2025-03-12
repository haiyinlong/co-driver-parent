package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerAccumulateAd;

/**
 * @author user
 * @description 针对表【dws_register_90_days_accumulate_pkg_ver_ad(dws注册日期90天广告汇总统计)】的数据库操作Mapper
 * @createDate 2025-03-07 12:05:52
 * @Entity com.leo.ad.codriver.dws.entity.DwsRegister90DaysAccumulatePkgVerAd
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerAccumulateAdMapper extends BaseMapper<DwsDailyPkgVerAccumulateAd> {

    List<DwsDailyPkgVerAccumulateAd> queryDbList(@Param("dates") Integer dates);
}
