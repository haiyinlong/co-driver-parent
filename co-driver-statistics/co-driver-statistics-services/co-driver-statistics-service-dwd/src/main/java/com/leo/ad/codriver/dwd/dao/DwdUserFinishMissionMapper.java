package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserFinishMission;

/**
 * dwd每日同期群转化Mapper
 *
 * @author HaiYinLong
 * @version 2024/04/22 15:11
 **/

@Mapper
@DS("mysql")
public interface DwdUserFinishMissionMapper extends BaseMapper<DwdUserFinishMission> {
    Integer deleteByDates(@Param("dates") Integer dates);

    List<DwdUserFinishMission> statisticsAid(@Param("dates") Integer dates);

    List<DwdUserFinishMission> statisticsUserId(@Param("dates") Integer dates);
}
