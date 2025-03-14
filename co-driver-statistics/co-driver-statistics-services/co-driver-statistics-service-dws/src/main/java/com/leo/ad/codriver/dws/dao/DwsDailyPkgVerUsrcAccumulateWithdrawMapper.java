package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAccumulateWithdraw;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_accumulate_withdraw(dws每日提现累计汇总统计)】的数据库操作Mapper
 * @createDate 2025-03-13 15:46:45
 * @Entity com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcAccumulateWithdraw
 */
@Mapper
@DS("mysql")
public interface DwsDailyPkgVerUsrcAccumulateWithdrawMapper extends BaseMapper<DwsDailyPkgVerUsrcAccumulateWithdraw> {

    List<DwsDailyPkgVerUsrcAccumulateWithdraw> queryDates(@Param("dates") Integer dates);

    List<DwsDailyPkgVerUsrcAccumulateWithdraw> statisticsList(@Param("dates") Integer dates);
}
