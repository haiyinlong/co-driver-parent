package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabShare;

/**
 * DwsDailyPackageShareMapper
 *
 * @author HaiYinLong
 * @version 2024/08/28 12:49
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageAllLabShareMapper extends BaseMapper<DwsDailyPackageAllLabShare> {
    List<DwsDailyPackageAllLabShare> queryStatistics(@Param("dates") Integer dates);
}
