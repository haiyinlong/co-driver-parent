package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsHemaAccountFullDaily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsHemaAccountFullDailyMapper extends BaseMapper<DwsHemaAccountFullDaily> {
    void deleteByDates(@Param("dates") Integer dates);

    List<DwsHemaAccountFullDaily> queryStatisticsActiveList(@Param("dates") Integer dates);

    List<DwsHemaAccountFullDaily> queryStatisticsNewList(@Param("dates") Integer dates);
}
