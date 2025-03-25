package com.leo.ad.codriver.clean.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * SkywalkingTableCleanDao
 *
 * @author HaiYinLong
 * @version 2025/03/25 10:39
 **/
@Mapper
@DS("skywalking")
public interface SkywalkingTableCleanDao extends BaseMapper {
    void deleteByTableNameAndDate(@Param("tableName") String tableName, @Param("dates") Integer dates);
}
