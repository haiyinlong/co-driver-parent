package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabOnline;

/**
 * DwsDailyPackageOnlineMapper
 *
 * @author HaiYinLong
 * @version 2024/08/29 14:23
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageAllLabOnlineMapper extends BaseMapper<DwsDailyPackageAllLabOnline> {
    void deleteByDates(@Param("dates") Integer dates);

    List<DwsDailyPackageAllLabOnline> queryStatistics(@Param("dates") Integer dates);
}
