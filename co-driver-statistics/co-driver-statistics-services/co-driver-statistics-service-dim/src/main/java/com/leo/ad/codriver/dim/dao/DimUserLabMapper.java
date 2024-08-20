package com.leo.ad.codriver.dim.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dim.entity.DimUserLab;

/**
 * DimUserLabDao
 *
 * @author HaiYinLong
 * @version 2024/08/19 19:05
 **/
@Mapper
@DS("mysql")
public interface DimUserLabMapper extends BaseMapper<DimUserLab> {
    DimUserLab getUserLab(long userLabId);
}
