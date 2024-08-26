package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserLtvRecord;

/**
 * DwdUserLtvRecordMapper
 *
 * @author HaiYinLong
 * @version 2024/08/26 10:40
 **/
@Mapper
@DS("mysql")
public interface DwdUserLtvRecordMapper extends BaseMapper<DwdUserLtvRecord> {
    Long getCountByDate(@Param("dates") Integer dates);

    List<DwdUserLtvRecord> queryByDate(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer startRows);
}
