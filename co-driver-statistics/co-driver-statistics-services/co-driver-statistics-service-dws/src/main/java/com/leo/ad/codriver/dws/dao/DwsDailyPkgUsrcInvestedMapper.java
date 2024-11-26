package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcInvested;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_invested(dws推广花费)】的数据库操作Mapper
 * @createDate 2024-11-26 17:28:36
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcInvested
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgUsrcInvestedMapper extends BaseMapper<DwsDailyPkgUsrcInvested> {

    List<DwsDailyPkgUsrcInvested> queryDbList(@Param("dates") Integer dates);
}
