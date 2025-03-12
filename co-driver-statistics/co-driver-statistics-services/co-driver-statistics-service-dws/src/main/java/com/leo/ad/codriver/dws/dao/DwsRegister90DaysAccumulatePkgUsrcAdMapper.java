package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsRegister90DaysAccumulatePkgUsrcAd;

/**
 * @author user
 * @description 针对表【dws_register_90_days_accumulate_pkg_usrc_ad(dws广告汇总统计，有新增的广告商就新增字段)】的数据库操作Mapper
 * @createDate 2025-03-07 12:05:52
 * @Entity com.leo.ad.codriver.dws.entity.DwsRegister90DaysAccumulatePkgUsrcAd
 */
@Mapper
@DS("mysql")
public interface DwsRegister90DaysAccumulatePkgUsrcAdMapper extends BaseMapper<DwsRegister90DaysAccumulatePkgUsrcAd> {

    List<DwsRegister90DaysAccumulatePkgUsrcAd> queryDbList(@Param("dates") Integer dates);
}
