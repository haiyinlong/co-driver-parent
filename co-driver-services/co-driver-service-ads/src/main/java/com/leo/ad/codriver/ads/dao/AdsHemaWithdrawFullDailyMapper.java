package com.leo.ad.codriver.ads.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsHemaWithdrawFullDaily;
import com.leo.ad.codriver.ads.entity.AdsHemaWithdrawFullDailyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AdsHemaWithdrawFullDailyMapper
 *
 * @author HaiYinLong
 * @version 2024/04/15 17:34
 **/
@Mapper
@DS("mysql")
public interface AdsHemaWithdrawFullDailyMapper extends BaseMapper<AdsHemaWithdrawFullDaily> {
    void delete(@Param("dates") Integer dates);

    List<AdsHemaWithdrawFullDailyDTO> queryStatisticsActiveList(@Param("dates") Integer dates);

    List<AdsHemaWithdrawFullDailyDTO> queryStatisticsNewList(@Param("dates") Integer dates);

}
