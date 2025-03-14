package com.leo.ad.codriver.dws.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgAccumulateRegister;

@Mapper
@DS("mysql")
public interface DwsDailyPkgAccumulateAdMapper extends BaseMapper<DwsDailyPkgAccumulateRegister> {

    List<DwsDailyPkgAccumulateRegister> queryDbList(@Param("dates") Integer dates);

}
