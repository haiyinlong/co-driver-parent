package com.leo.ad.codriver.dim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dim.entity.DimChannel;

/**
 * DWDDao
 *
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DimChannelMapper extends BaseMapper<DimChannel> {

    List<DimChannel> list();

    List<DimChannel> queryStatistics();
}
