package com.leo.ad.codriver.dwd.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    DwCountDTO getStatisticsCount(@Param("dates") Integer dates);

    List<Long> queryNotExistsOdsId(@Param("dates") Integer dates, @Param("limitNum") Integer limitNum);

    List<DwdUserAdRecord> queryStatisticsByDate(@Param("dates") Integer dates,
        @Param("startSourceId") long startSourceId, @Param("endSourceId") long endSourceId);

    List<DwdUserAdRecord> queryDbActiveListByDate(@Param("dates") Integer dates, @Param("startId") Long startId,
        @Param("endId") Long endId);

    List<DwdUserAdRecord> queryDbNewListByDate(@Param("dates") Integer dates, @Param("startId") Long startId,
        @Param("endId") Long endId);

    List<DwdUserAdRecord> queryDbActiveListByDateUsrc(@Param("dates") Integer dates, @Param("startId") Long startId,
        @Param("endId") Long endId);

    List<DwdUserAdRecord> queryDbNewListByDateUsrc(@Param("dates") Integer dates, @Param("startId") Long startId,
        @Param("endId") Long endId);

    void deleteSourceId(@Param("notExistsOdsId") List<Long> notExistsOdsId);
}
