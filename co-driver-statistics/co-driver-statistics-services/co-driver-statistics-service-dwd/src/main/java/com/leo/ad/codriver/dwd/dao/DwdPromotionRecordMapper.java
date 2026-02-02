package com.leo.ad.codriver.dwd.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdPromotionRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

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

    DwCountDTO getOdsDbCountOfId(@Param("dates") Integer dates);

    List<DwdPromotionRecord> queryOdsByDateAndId(@Param("dates") Integer dates,
        @Param("singleFee") BigDecimal singleFee, @Param("startId") long startId, @Param("endId") long endId);

    List<DwdPromotionRecord> queryByDateAndId(@Param("dates") Integer dates, @Param("startId") long startId,
        @Param("endId") long endId);

    DwCountDTO getDbCountOfId(@Param("dates") Integer dates);
}
