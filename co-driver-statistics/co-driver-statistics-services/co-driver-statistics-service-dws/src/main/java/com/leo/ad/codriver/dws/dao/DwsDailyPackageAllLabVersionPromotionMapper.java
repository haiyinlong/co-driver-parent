package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageAllLabVersionPromotion;

/**
 * DwsDailyPackageVersionPromotionMapper
 *
 * @author HaiYinLong
 * @version 2024/09/01 16:35
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageAllLabVersionPromotionMapper extends BaseMapper<DwsDailyPackageAllLabVersionPromotion> {
    void deleteByDates(@Param("dates") Integer dates);

    List<DwsDailyPackageAllLabVersionPromotion> selectByDate(@Param("dates") Integer dates);
}
