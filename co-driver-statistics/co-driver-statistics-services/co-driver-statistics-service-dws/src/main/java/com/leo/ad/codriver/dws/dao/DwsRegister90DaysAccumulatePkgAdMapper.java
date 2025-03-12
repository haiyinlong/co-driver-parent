package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsRegister90DaysAccumulatePkgAd;

/**
 * @author user
 * @description 针对表【dws_register_90_days_accumulate_pkg_ad(dws注册日期90天广告汇总统计)】的数据库操作Mapper
 * @createDate 2025-03-07 12:05:52
 * @Entity com.leo.ad.codriver.dws.entity.DwsRegister90DaysAccumulatePkgAd
 */
@Mapper
@DS("mysql")
public interface DwsRegister90DaysAccumulatePkgAdMapper extends BaseMapper<DwsRegister90DaysAccumulatePkgAd> {

    List<DwsRegister90DaysAccumulatePkgAd> queryDbList(@Param("dates") Integer dates);

}
