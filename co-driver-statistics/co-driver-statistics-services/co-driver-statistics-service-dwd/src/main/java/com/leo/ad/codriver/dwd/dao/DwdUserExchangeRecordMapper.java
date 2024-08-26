package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserExchangeRecord;

/**
 * DwdUserExchangeRecordmapper
 *
 * @author HaiYinLong
 * @version 2024/08/26 16:12
 **/
@Mapper
@DS("mysql")
public interface DwdUserExchangeRecordMapper extends BaseMapper<DwdUserExchangeRecord> {
    Long getCountByDate(@Param("dates") Integer dates);

    List<DwdUserExchangeRecord> queryByDate(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer startRows);
}
