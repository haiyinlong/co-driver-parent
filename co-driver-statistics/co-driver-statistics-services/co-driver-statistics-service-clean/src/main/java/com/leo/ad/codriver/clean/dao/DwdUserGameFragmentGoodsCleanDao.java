package com.leo.ad.codriver.clean.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
@DS("mysql")
public interface DwdUserGameFragmentGoodsCleanDao extends BaseMapper {

    void deleteByTwoMonthsAgo();
}
