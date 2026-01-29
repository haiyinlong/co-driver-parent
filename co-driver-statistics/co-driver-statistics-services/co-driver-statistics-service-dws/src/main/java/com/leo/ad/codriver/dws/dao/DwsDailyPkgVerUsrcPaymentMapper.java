package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcPayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcPaymentMapper extends BaseMapper<DwsDailyPkgVerUsrcPayment> {

    List<DwsDailyPkgVerUsrcPayment> queryList(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcPayment> queryStatisticsActiveList(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcPayment> queryStatisticsNewList(@Param("dates") Integer dates);
}




