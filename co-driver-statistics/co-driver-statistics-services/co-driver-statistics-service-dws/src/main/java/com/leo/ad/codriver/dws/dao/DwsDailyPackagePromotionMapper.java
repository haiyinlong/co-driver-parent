package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackagePromotion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackagePromotionMapper extends BaseMapper<DwsDailyPackagePromotion> {

    void deleteDailyPackagePromotion(@Param("dates") Integer dates);

    void syncDailyPackagePromotion(@Param("dates") Integer dates, @Param("singleFee") BigDecimal singleFee);

    List<DwsDailyPackagePromotion> list(@Param("dates") Integer dates);
}
