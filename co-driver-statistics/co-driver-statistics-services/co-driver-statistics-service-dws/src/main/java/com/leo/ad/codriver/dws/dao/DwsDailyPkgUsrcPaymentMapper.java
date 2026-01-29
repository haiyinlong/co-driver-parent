package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcPayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@DS("mysql")
public interface DwsDailyPkgUsrcPaymentMapper extends BaseMapper<DwsDailyPkgUsrcPayment> {

    List<DwsDailyPkgUsrcPayment> queryList(@Param("dates") Integer dates);

    List<DwsDailyPkgUsrcPayment> queryStatisticsActiveList(@Param("dates") Integer dates);

    List<DwsDailyPkgUsrcPayment> queryStatisticsNewList(@Param("dates") Integer dates);
}




