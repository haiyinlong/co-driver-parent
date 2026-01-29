package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgPayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@DS("mysql")
public interface DwsDailyPkgPaymentMapper extends BaseMapper<DwsDailyPkgPayment> {
    void deleteByDates(@Param("dates") Integer dates);

    List<DwsDailyPkgPayment> queryStatisticsActiveList(@Param("dates") Integer dates);

    List<DwsDailyPkgPayment> queryStatisticsNewList(@Param("dates") Integer dates);
}




