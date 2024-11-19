package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcWithdraw;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_withdraw】的数据库操作Mapper
 * @createDate 2024-11-19 16:43:31
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcWithdraw
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcWithdrawMapper extends BaseMapper<DwsDailyPkgVerUsrcWithdraw> {

    List<DwsDailyPkgVerUsrcWithdraw> queryList(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcWithdraw> queryStatisticsActiveList(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcWithdraw> queryStatisticsNewList(@Param("dates") Integer dates);
}
