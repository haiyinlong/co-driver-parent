package com.leo.ad.codriver.clean.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HaiYinLong
 * @version 2024/04/15 17:34
 **/
@Mapper
@DS("mysql")
public interface DwdUserAccountRecordCleanDao extends BaseMapper {

    void deleteByTwoMonthsAgo();
}
