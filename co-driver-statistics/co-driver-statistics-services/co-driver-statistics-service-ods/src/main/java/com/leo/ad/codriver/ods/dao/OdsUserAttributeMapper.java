package com.leo.ad.codriver.ods.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author HaiYinLong
 * @version 2024/08/19 19:05
 **/
@Mapper
@DS("mysql")
public interface OdsUserAttributeMapper extends BaseMapper {

    void updateCreateTime(@Param("dates") int dates);
}
