package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcQpLtv;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_qp_ltv】的数据库操作Mapper
 * @createDate 2024-11-19 16:43:31
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcQpLtv
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcQpLtvMapper extends BaseMapper<DwsDailyPkgVerUsrcQpLtv> {

    List<DwsDailyPkgVerUsrcQpLtv> queryDbList(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcQpLtv> queryStatisticList(@Param("dates") Integer dates);
}
