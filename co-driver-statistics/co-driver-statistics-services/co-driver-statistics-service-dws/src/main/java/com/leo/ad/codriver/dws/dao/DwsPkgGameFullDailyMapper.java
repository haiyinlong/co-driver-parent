package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsPkgGameFullDaily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsPkgGameFullDailyMapper extends BaseMapper<DwsPkgGameFullDaily> {
    void deleteByDates(@Param("dates") Integer dates);

    List<DwsPkgGameFullDaily> statisticsActive(@Param("dates") Integer dates);

    List<DwsPkgGameFullDaily> statisticsNew(@Param("dates") Integer dates);
}
