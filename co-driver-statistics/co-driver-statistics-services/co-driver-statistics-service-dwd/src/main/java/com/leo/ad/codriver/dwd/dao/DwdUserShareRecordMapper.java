package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserShareRecord;

/**
 * DwdUserShareRecordMapper
 *
 * @author HaiYinLong
 * @version 2024/08/26 14:20
 **/
@Mapper
@DS("mysql")
public interface DwdUserShareRecordMapper extends BaseMapper<DwdUserShareRecord> {
    Long getCountByDate(@Param("dates") Integer dates);

    List<DwdUserShareRecord> queryByDate(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer startRows);
}
