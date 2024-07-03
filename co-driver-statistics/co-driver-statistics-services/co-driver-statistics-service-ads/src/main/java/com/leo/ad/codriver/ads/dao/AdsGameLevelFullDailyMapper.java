package com.leo.ad.codriver.ads.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsGameLevelFullDaily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AdsGameLevelFullDailyMapper
 *
 * @author HaiYinLong
 * @version 2024/07/02 17:34
 **/
@Mapper
@DS("mysql")
public interface AdsGameLevelFullDailyMapper extends BaseMapper<AdsGameLevelFullDaily> {

    List<AdsGameLevelFullDaily> queryStatistics(@Param("dates") Integer dates);

}
