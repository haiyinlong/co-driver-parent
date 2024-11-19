package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcLogin;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_login】的数据库操作Mapper
 * @createDate 2024-11-19 16:43:31
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcLogin
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcLoginMapper extends BaseMapper<DwsDailyPkgVerUsrcLogin> {

    List<DwsDailyPkgVerUsrcLogin> queryDbList(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcLogin> queryStatisticsList(@Param("dates") Integer dates);
}
