package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserLoginRecord;

/**
 * DWDDao
 *
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwdUserLoginRecordMapper extends BaseMapper<DwdUserLoginRecord> {
    Integer deleteByDates(@Param("dates") Integer dates);

    DwCountDTO getStatisticsCount(@Param("dates") Integer dates, @Param("startId") Long startId);

    List<DwdUserLoginRecord> queryStatisticsByDate(@Param("dates") Integer dates,
        @Param("startSourceId") long startSourceId, @Param("endSourceId") long endSourceId);

    DwCountDTO getDbCountOfId(@Param("dates") Integer dates);

    List<DwdUserLoginRecord> queryListByDates(@Param("dates") Integer dates, @Param("startId") Long startId,
        @Param("endId") Long endId);
}
