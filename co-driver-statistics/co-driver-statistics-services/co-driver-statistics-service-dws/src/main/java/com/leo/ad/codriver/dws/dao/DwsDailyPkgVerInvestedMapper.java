package com.leo.ad.codriver.dws.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerInvested;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_invested(dws推广花费)】的数据库操作Mapper
 * @createDate 2024-11-26 17:28:36
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerInvested
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerInvestedMapper extends BaseMapper<DwsDailyPkgVerInvested> {

}
