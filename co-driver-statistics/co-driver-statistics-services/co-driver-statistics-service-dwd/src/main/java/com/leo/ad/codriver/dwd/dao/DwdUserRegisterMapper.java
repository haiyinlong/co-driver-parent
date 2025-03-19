package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserRegister;

/**
 * @author HaiYinLong
 * @version 2024/04/22 15:11
 **/

@Mapper
@DS("mysql")
public interface DwdUserRegisterMapper extends BaseMapper<DwdUserRegister> {

    Integer deleteByDates(@Param("dates") Integer dates);

    DwCountDTO getStatisticsCount(@Param("dates") Integer dates);

    DwCountDTO getDbCountOfId(@Param("dates") Integer dates);

    List<DwdUserRegister> queryUsrcListWithAndId(@Param("dates") Integer dates, @Param("startId") long startId,
        @Param("endId") long endId);

    List<DwdUserRegister> statisticsPage(@Param("dates") Integer dates, @Param("startId") long startId,
        @Param("endId") long endId);
}
