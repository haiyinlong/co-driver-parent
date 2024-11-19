package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcOnline;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_online】的数据库操作Mapper
 * @createDate 2024-11-19 16:43:31
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcOnline
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcOnlineMapper extends BaseMapper<DwsDailyPkgVerUsrcOnline> {

    List<DwsDailyPkgVerUsrcOnline> queryDbList(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcOnline> queryStatisticsList(@Param("dates") Integer dates);

}
