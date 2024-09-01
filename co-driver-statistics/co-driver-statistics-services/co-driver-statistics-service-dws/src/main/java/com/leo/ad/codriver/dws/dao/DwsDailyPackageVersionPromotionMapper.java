package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageVersionPromotion;

/**
 * DwsDailyPackageVersionPromotionMapper
 *
 * @author HaiYinLong
 * @version 2024/09/01 16:35
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageVersionPromotionMapper extends BaseMapper<DwsDailyPackageVersionPromotion> {
    void deleteByDates(@Param("dates") Integer dates);

    List<DwsDailyPackageVersionPromotion> selectByDate(@Param("dates") Integer dates);
}
