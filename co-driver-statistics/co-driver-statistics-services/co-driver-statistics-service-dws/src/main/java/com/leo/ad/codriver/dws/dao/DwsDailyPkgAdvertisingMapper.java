package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgAdvertising;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_advertising(dws广告汇总统计，有新增的广告商就新增字段)】的数据库操作Mapper
 * @createDate 2024-11-25 09:30:53
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgAdvertising
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgAdvertisingMapper extends BaseMapper<DwsDailyPkgAdvertising> {

    List<DwsDailyPkgAdvertising> queryDbListByUserType(@Param("dates") Integer integer,
        @Param("userType") Integer userType);
}
