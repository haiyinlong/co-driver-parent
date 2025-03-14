package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgAccumulateWithdraw;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_accumulate_withdraw(dws每日提现累计汇总统计)】的数据库操作Mapper
 * @createDate 2025-03-13 12:19:39
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgAccumulateWithdraw
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgAccumulateWithdrawMapper extends BaseMapper<DwsDailyPkgAccumulateWithdraw> {

    List<DwsDailyPkgAccumulateWithdraw> queryDates(@Param("dates") Integer dates);

    List<DwsDailyPkgAccumulateWithdraw> statisticsList(@Param("dates") Integer dates);
}
