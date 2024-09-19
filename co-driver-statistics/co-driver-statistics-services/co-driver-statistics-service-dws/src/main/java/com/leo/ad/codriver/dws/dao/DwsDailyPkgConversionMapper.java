package com.leo.ad.codriver.dws.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgConversion;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPkgConversionMapper extends BaseMapper<DwsDailyPkgConversion> {
    void deleteByDates(@Param("dates") Integer dates);

    void syncActiveList(@Param("dates") Integer dates);

    void syncNewList(@Param("dates") Integer dates);

}
