package com.leo.ad.codriver.clean.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author HaiYinLong
 * @version 2024/04/15 17:34
 **/
@Mapper
@DS("mysql")
public interface DwdUserEventCleanDao extends BaseMapper {

    Integer deleteByLastMonthsAgo();
}
