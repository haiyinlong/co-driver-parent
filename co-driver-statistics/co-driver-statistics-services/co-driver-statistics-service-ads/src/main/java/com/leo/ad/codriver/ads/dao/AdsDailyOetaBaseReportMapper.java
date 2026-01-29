package com.leo.ad.codriver.ads.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsDailyOetaBaseReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AdsDailyOetaBaseReportMapper
 *
 * @author HaiYinLong
 * @version 2024/09/01 11:07
 **/
@Mapper
@DS("mysql")
public interface AdsDailyOetaBaseReportMapper extends BaseMapper<AdsDailyOetaBaseReport> {

    List<AdsDailyOetaBaseReport> queryOetaBaseReportList(@Param("dates") Integer dates);

    /**
     * TODO 推广花费计算有问题 promotion_cost，根据用户进行平均检查是否取值问题
     */
    List<AdsDailyOetaBaseReport> selectActivePkgVerList(@Param("dates") Integer dates);

    /**
     * TODO 推广花费计算有问题 promotion_cost，根据用户进行平均检查是否取值问题
     */
    List<AdsDailyOetaBaseReport> selectNewPkgVerList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> selectActivePkgList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> selectNewPkgList(@Param("dates") Integer dates);

    /**
     * TODO 推广花费计算有问题 promotion_cost，根据用户进行平均检查是否取值问题
     */
    List<AdsDailyOetaBaseReport> selectActivePkgVerUsrcList(@Param("dates") Integer dates);

    /**
     * TODO 推广花费计算有问题 promotion_cost，根据用户进行平均检查是否取值问题
     */
    List<AdsDailyOetaBaseReport> selectNewPkgVerUsrcList(@Param("dates") Integer dates);

    /**
     * TODO 推广花费计算有问题 promotion_cost，花费数据为0
     */
    List<AdsDailyOetaBaseReport> selectActivePkgUsrcList(@Param("dates") Integer dates);

    List<AdsDailyOetaBaseReport> selectNewPkgUsrcList(@Param("dates") Integer dates);
}
