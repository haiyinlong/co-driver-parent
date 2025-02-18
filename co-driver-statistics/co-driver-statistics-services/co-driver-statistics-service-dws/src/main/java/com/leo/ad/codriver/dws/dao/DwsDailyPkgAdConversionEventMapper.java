package com.leo.ad.codriver.dws.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgAdConversionEvent;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ad_conversion_event(dws每日广告事件统计)】的数据库操作Mapper
 * @createDate 2025-02-18 10:27:49
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgAdConversionEvent
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgAdConversionEventMapper extends BaseMapper<DwsDailyPkgAdConversionEvent> {

    void deleteByDate(@Param("dates") Integer dates);
}
