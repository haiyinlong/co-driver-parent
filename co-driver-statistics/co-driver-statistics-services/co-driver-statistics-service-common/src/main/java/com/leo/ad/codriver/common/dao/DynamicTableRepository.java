package com.leo.ad.codriver.common.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.leo.ad.codriver.common.DwCountDTO;

/**
 * @author Hai Yinlong
 */
@Mapper
@DS("mysql")
public interface DynamicTableRepository {

    DwCountDTO getDbCount(@Param("dates") Integer dates, @Param("tableName") String tableName,
        @Param("startId") Long startId);
}
