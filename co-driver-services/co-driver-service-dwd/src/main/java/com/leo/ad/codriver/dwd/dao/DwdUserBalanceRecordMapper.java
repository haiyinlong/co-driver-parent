package com.leo.ad.codriver.dwd.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserBalanceRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户余额流水表
 *
 * @author HaiYinLong
 * @version 2024/05/13 15:11
 **/
@Mapper
@DS("mysql")
public interface DwdUserBalanceRecordMapper extends BaseMapper<DwdUserBalanceRecord> {

    long getRecordCount();

    List<DwdUserBalanceRecord> queryStatistics(@Param("rows") Integer rows, @Param("startRows") Integer pageSize);

}
