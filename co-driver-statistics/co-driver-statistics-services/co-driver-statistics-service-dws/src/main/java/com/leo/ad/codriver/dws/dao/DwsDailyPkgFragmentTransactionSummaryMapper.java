package com.leo.ad.codriver.dws.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgFragmentTransactionSummary;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_fragment_transaction_summary(dws每日包碎片记录汇总)】的数据库操作Mapper
 * @createDate 2025-05-12 17:02:12
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgFragmentTransactionSummary
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgFragmentTransactionSummaryMapper extends BaseMapper<DwsDailyPkgFragmentTransactionSummary> {

    void deleteByDates(@Param("dates") Integer dates);
}
