package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserRegister;

/**
 * @author HaiYinLong
 * @version 2024/04/22 15:11
 **/

@Mapper
@DS("mysql")
public interface DwdUserRegisterMapper extends BaseMapper<DwdUserRegister> {

    Integer deleteByDates(@Param("dates") Integer dates);

    List<DwdUserRegister> statistics(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer startRows);

    Long getStatisticsCount(@Param("dates") Integer dates);

}
