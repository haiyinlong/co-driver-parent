package com.leo.ad.codriver.dim.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dim.entity.DimUserSource;

/**
 * @author user
 * @description 针对表【dim_user_source(用户来源)】的数据库操作Mapper
 * @createDate 2024-11-19 14:38:36
 * @Entity com.leo.ad.codriver.dim.entity.DimUserSource
 */
@Mapper
@DS("mysql")
public interface DimUserSourceMapper extends BaseMapper<DimUserSource> {

}
