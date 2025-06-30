package com.leo.ad.codriver.dwd.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
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

    DwCountDTO getWithdrawCountByUpdateDate(@Param("dateStr") String dateStr);

    List<DwdUserWithdrawRecord> queryWithdrawList(@Param("dates") Integer dates, @Param("dateStr") String dateStr,
        @Param("singleFee") BigDecimal singleFee, @Param("startId") long startId, @Param("endId") long endId);

    DwCountDTO getDbCountOfId(@Param("dates") Integer dates);

    List<DwdUserWithdrawRecord> queryListByDates(@Param("dates") Integer dates, @Param("startId") Long startId,
        @Param("endId") Long endId);
}
