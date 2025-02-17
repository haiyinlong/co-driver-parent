package com.leo.ad.codriver.dwd.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserEvent;

/**
 * 用户事件记录表
 *
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwdUserEventMapper extends BaseMapper<DwdUserEvent> {

}
