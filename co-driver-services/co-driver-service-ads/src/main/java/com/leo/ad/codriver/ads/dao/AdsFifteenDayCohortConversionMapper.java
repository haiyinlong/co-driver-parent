package com.leo.ad.codriver.ads.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsFifteenDayCohortConversion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HaiYinLong
 * @version 2024/04/15 17:34
 **/
@Mapper
@DS("mysql")
public interface AdsFifteenDayCohortConversionMapper extends BaseMapper<AdsFifteenDayCohortConversion> {
    void deleteByDates(@Param("list") List<Integer> dates);

    List<AdsFifteenDayCohortConversion> statistics(@Param("list") List<Integer> dates);

}
