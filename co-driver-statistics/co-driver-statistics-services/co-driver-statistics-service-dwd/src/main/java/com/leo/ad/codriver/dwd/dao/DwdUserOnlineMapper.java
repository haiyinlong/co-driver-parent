package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserOnline;

/**
 * DwdUserOnlineMapper
 *
 * @author HaiYinLong
 * @version 2024/08/23 19:45
 **/
@Mapper
@DS("mysql")
public interface DwdUserOnlineMapper extends BaseMapper<DwdUserOnline> {
    Long getCountByDate(@Param("dates") Integer dates);

    List<DwdUserOnline> queryByDate(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer startRows);
}
