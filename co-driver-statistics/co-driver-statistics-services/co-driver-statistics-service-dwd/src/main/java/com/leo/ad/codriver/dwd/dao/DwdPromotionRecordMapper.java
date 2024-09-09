package com.leo.ad.codriver.dwd.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdPromotionRecord;

/**
 * DwdPromotionRecordMapper
 *
 * @author HaiYinLong
 * @version 2024/09/01 15:47
 **/
@Mapper
@DS("mysql")
public interface DwdPromotionRecordMapper extends BaseMapper<DwdPromotionRecord> {
    Integer deleteByDate(@Param("dates") Integer dates);

    List<DwdPromotionRecord> queryByDate(@Param("dates") Integer dates, @Param("singleFee") BigDecimal singleFee);
}
