package com.leo.ad.codriver.dwd.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserLoginRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DWDDao
 *
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwdUserLoginRecordMapper extends BaseMapper<DwdUserLoginRecord> {
    void deleteByDates(@Param("dates") Integer dates);

    Long getStatisticsCount(@Param("dates") Integer dates);

    List<DwdUserLoginRecord> statistics(@Param("dates") Integer dates, @Param("rows") Integer rows,
                                        @Param("startRows") Integer startRows);
}
