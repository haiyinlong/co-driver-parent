package com.leo.ad.codriver.ads.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.TempDailyUserQualityAnalyse;

@Mapper
@DS("mysql")
public interface TempDailyUserQualityAnalyseMapper extends BaseMapper<TempDailyUserQualityAnalyse> {
    void cleanTempTable(Integer dates);
}
