package com.leo.ad.codriver.ads.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.ads.entity.AdsFifteenDayCohortFinishMission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HaiYinLong
 * @version 2024/04/15 17:34
 **/
@Mapper
@DS("mysql")
public interface AdsFifteenDayCohortMissionMapper extends BaseMapper<AdsFifteenDayCohortFinishMission> {
    void deleteByDates(@Param("list") List<Integer> dates);

    List<AdsFifteenDayCohortFinishMission> statistics(@Param("list") List<Integer> dates);

}
