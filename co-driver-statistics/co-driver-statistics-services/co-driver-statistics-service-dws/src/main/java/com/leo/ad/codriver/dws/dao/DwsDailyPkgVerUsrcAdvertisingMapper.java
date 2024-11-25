package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAdvertising;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_advertising(dws广告汇总统计，有新增的广告商就新增字段)】的数据库操作Mapper
 * @createDate 2024-11-25 09:30:53
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAdvertising
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcAdvertisingMapper extends BaseMapper<DwsDailyPkgVerUsrcAdvertising> {

    List<DwsDailyPkgVerUsrcAdvertising> queryDbListByUserType(@Param("dates") Integer dates,
        @Param("userType") Integer userType);
}
