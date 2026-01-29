package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerPayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@DS("mysql")
public interface DwsDailyPkgVerPaymentMapper extends BaseMapper<DwsDailyPkgVerPayment> {
    void deleteByDates(@Param("dates") Integer dates);

    List<DwsDailyPkgVerPayment> queryStatisticsActiveList(@Param("dates") Integer dates);

    List<DwsDailyPkgVerPayment> queryStatisticsNewList(@Param("dates") Integer dates);
}




