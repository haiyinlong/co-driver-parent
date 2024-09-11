package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabAssetExchange;

/**
 * DwsDailyPackageAllLabAssetExchangeMapper
 *
 * @author HaiYinLong
 * @version 2024/09/11 11:32
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageAllLabAssetExchangeMapper extends BaseMapper<DwsDailyPackageAllLabAssetExchange> {
    List<DwsDailyPackageAllLabAssetExchange> queryList(@Param("dates") Integer dates);

    List<DwsDailyPackageAllLabAssetExchange> selectActiveList(@Param("dates") Integer dates);

    List<DwsDailyPackageAllLabAssetExchange> selectNewList(@Param("dates") Integer dates);
}
