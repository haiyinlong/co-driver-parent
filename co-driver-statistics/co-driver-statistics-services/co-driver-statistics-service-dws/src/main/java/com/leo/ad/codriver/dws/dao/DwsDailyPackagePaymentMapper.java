package com.leo.ad.codriver.dws.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackagePayment;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackagePaymentMapper extends BaseMapper<DwsDailyPackagePayment> {
    void deleteByDates(@Param("dates") Integer dates);

    DwsDailyPackagePayment query(@Param("dates") Integer dates, @Param("country") String country,
        @Param("pkg") String pkg, @Param("version") String version);

    List<DwsDailyPackagePayment> statisticsPackagePayment(@Param("dates") Integer dates,
        @Param("singleFee") BigDecimal singleFee);
}
