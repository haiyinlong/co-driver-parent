package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    Long getCountByDate(@Param("dates") Integer dates);

    List<DwdUserAdRecord> queryByDate(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer startRows);
}
