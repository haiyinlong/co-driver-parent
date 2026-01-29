package com.leo.ad.codriver.dwd.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserPaymentRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author user
 * @description 针对表【dwd_user_payment_record(dwd用户充值明细表)】的数据库操作Mapper
 * @createDate 2026-01-29 10:36:48
 * @Entity com.leo.ad.codriver.dwd.entity.DwdUserPaymentRecord
 */
@Mapper
@DS("mysql")
public interface DwdUserPaymentRecordMapper extends BaseMapper<DwdUserPaymentRecord> {

    DwCountDTO getCountByDates(@Param("dates") Integer dates);

    List<DwdUserPaymentRecord> queryList(@Param("dates") Integer dates, @Param("singleFee") BigDecimal singleFee,
        @Param("startId") long startId, @Param("endId") long endId);
}




