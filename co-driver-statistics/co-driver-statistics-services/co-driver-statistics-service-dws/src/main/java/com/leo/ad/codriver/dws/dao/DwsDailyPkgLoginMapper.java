package com.leo.ad.codriver.dws.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgLogin;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPkgLoginMapper extends BaseMapper<DwsDailyPkgLogin> {

    void deleteByDates(@Param("dates") Integer dates);

    void syncDailyPackageAllLogin(@Param("dates") Integer dates);

}
