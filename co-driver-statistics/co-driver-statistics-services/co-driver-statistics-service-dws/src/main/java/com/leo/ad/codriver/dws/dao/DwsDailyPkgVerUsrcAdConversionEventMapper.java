package com.leo.ad.codriver.dws.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAdConversionEvent;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_ad_conversion_event(dws每日用户来源版本事件统计)】的数据库操作Mapper
 * @createDate 2025-02-18 10:27:49
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAdConversionEvent
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcAdConversionEventMapper extends BaseMapper<DwsDailyPkgVerUsrcAdConversionEvent> {

    void deleteByDate(@Param("dates") Integer dates);
}
