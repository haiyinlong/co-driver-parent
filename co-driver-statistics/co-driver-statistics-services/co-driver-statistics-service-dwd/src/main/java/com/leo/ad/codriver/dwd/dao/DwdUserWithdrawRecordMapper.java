package com.leo.ad.codriver.dwd.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserWithdrawRecord;

/**
 * 用户事提现记录表
 *
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwdUserWithdrawRecordMapper extends BaseMapper<DwdUserWithdrawRecord> {
    Integer deleteByDates(@Param("dates") Integer dates);

    long getWithdrawCount(@Param("dates") Integer dates);

    List<DwdUserWithdrawRecord> queryWithdrawList(@Param("dates") Integer dates,
        @Param("singleFee") BigDecimal singleFee, @Param("rows") Integer rows, @Param("startRows") Integer pageSize);

}
