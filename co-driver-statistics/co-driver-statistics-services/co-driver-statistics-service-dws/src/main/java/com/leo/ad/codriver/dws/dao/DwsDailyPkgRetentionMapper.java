package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgRetention;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPkgRetentionMapper extends BaseMapper<DwsDailyPkgRetention> {
    void deleteByDates(@Param("dates") Integer dates);

    List<DwsDailyPkgRetention> statisticsPkgRetention(@Param("dates") Integer dates);
}
