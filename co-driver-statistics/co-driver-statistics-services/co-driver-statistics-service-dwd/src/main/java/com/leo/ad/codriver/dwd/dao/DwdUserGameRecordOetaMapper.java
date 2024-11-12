package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserGameRecordOeta;

/**
 * @author HaiYinLong
 * @version 2024/04/22 15:11
 **/

@Mapper
@DS("mysql")
public interface DwdUserGameRecordOetaMapper extends BaseMapper<DwdUserGameRecordOeta> {
    void deleteBySourceId(@Param("sourceId") Long sourceId);

    DwdUserGameRecordOeta getStatistics(@Param("sourceId") Long sourceId);

    DwCountDTO getDbCount(@Param("dates") Integer dates);

    DwCountDTO getStatisticsCount(@Param("dates") Integer dates, @Param("startId") Long startId);

    List<DwdUserGameRecordOeta> queryStatisticsByDate(@Param("dates") Integer dates,
        @Param("startSourceId") long startSourceId, @Param("endSourceId") long endSourceId);
}
