package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageQpLtv;

/**
 * DwsDailyPackageQpLtvMapper
 *
 * @author HaiYinLong
 * @version 2024/09/01 18:46
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageQpLtvMapper extends BaseMapper<DwsDailyPackageQpLtv> {
    void deleteByDates(@Param("dates") Integer dates);

    List<DwsDailyPackageQpLtv> selectByDates(@Param("dates") Integer dates);
}
