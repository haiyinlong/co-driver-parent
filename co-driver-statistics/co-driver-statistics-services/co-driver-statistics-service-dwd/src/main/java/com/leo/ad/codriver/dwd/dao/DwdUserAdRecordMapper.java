package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;

/**
 * DwdUserAdRecordMapper
 *
 * @author HaiYinLong
 * @version 2024/08/26 15:15
 **/
@Mapper
@DS("mysql")
public interface DwdUserAdRecordMapper extends BaseMapper<DwdUserAdRecord> {

    DwCountDTO getDbCountOfId(@Param("dates") Integer dates);

    DwCountDTO getDbCount(@Param("dates") Integer dates);

    DwCountDTO getStatisticsCount(@Param("dates") Integer dates);

    List<DwdUserAdRecord> queryStatisticsByDate(@Param("dates") Integer dates,
        @Param("startSourceId") long startSourceId, @Param("endSourceId") long endSourceId);

    List<DwdUserAdRecord> queryDbActiveListByDate(@Param("dates") Integer dates, @Param("startId") Long startId,
        @Param("endId") Long endId);

    List<DwdUserAdRecord> queryDbNewListByDate(@Param("dates") Integer dates, @Param("startId") Long startId,
        @Param("endId") Long endId);
}
