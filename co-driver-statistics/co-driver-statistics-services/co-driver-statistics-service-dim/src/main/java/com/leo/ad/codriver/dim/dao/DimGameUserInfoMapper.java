package com.leo.ad.codriver.dim.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dim.entity.DimGameUserInfo;

/**
 * DimGameUserInfoMapper
 *
 * @author HaiYinLong
 * @version 2024/08/27 14:47
 **/
@Mapper
@DS("mysql")
public interface DimGameUserInfoMapper extends BaseMapper<DimGameUserInfo> {
    DimGameUserInfo getOdsGameUserInfo(@Param("gameUserId") long gameUserId);
}
