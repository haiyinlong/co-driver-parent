package com.leo.ad.codriver.dim.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dim.entity.DimChannel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DWDDao
 *
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DimChannelMapper extends BaseMapper<DimChannel> {
    void truncate();

    void syncData();

    List<DimChannel> list();
}
