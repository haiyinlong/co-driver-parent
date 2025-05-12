package com.leo.ad.codriver.ads.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyFragmentReport;

/**
 * @author user
 * @description 针对表【ads_daily_fragment_report(ads每日包碎片)】的数据库操作Mapper
 * @createDate 2025-05-12 17:27:41
 * @Entity com.leo.ad.codriver.ads.entity.AdsDailyFragmentReport
 */
@Mapper
@DS("mysql")
public interface AdsDailyFragmentReportMapper extends BaseMapper<AdsDailyFragmentReport> {

    void deleteByDates(@Param("dates") Integer dates);

    List<AdsDailyFragmentReport> queryStatistics(@Param("dates") Integer dates);
}
