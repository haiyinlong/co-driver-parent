package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcShare;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_share】的数据库操作Mapper
 * @createDate 2024-11-20 17:07:05
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcShare
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgUsrcShareMapper extends BaseMapper<DwsDailyPkgUsrcShare> {

    List<DwsDailyPkgUsrcShare> queryDbList(@Param("dates") Integer dates);

    List<DwsDailyPkgUsrcShare> queryStatisticsList(@Param("dates") Integer dates);
}
