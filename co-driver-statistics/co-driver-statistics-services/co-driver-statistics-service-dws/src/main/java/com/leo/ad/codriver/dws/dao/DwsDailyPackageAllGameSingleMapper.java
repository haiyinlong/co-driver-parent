package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllGameSingle;

/**
 * DwsDailyPackageAllGameSingleMapper
 *
 * @author HaiYinLong
 * @version 2024/09/11 16:39
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageAllGameSingleMapper extends BaseMapper<DwsDailyPackageAllGameSingle> {
    List<DwsDailyPackageAllGameSingle> queryActiveList(@Param("dates") Integer dates);

    List<DwsDailyPackageAllGameSingle> queryNewList(@Param("dates") Integer dates);

    List<DwsDailyPackageAllGameSingle> queryList(@Param("dates") Integer dates);
}
