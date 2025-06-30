package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdQpLtvRecord;

/**
 * DwdQpLtvRecordMapper
 *
 * @author HaiYinLong
 * @version 2024/09/01 18:11
 **/
@Mapper
@DS("mysql")
public interface DwdQpLtvRecordMapper extends BaseMapper<DwdQpLtvRecord> {
    Integer deleteByDate(@Param("dates") Integer dates);

    List<DwdQpLtvRecord> queryByDate(@Param("dates") Integer dates);

    DwCountDTO getDbCountOfId(@Param("dates") Integer dates);

    List<DwdQpLtvRecord> queryListByDates(@Param("dates") Integer dates, @Param("startId") Long startId,
        @Param("endId") Long endId);
}
