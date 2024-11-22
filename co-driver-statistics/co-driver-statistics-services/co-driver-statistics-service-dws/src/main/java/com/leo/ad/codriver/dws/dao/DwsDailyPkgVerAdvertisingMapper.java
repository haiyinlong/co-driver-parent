package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerAdvertising;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_advertising(dws广告汇总统计，有新增的广告商就新增字段)】的数据库操作Mapper
 * @createDate 2024-11-21 19:36:26
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerAdvertising
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerAdvertisingMapper extends BaseMapper<DwsDailyPkgVerAdvertising> {

    List<DwsDailyPkgVerAdvertising> queryDbActiveList(@Param("dates") Integer dates);

    List<DwsDailyPkgVerAdvertising> queryDbNewList(@Param("dates") Integer dates);
}
