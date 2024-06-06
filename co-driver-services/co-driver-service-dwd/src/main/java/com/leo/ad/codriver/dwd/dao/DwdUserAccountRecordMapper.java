package com.leo.ad.codriver.dwd.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserAccountRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户账户流水表
 *
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwdUserAccountRecordMapper extends BaseMapper<DwdUserAccountRecord> {
    void deleteByDates(@Param("dates") Integer dates);

    long getRecordCount(@Param("dates") Integer dates);

    List<DwdUserAccountRecord> queryStatistics(@Param("dates") Integer dates, @Param("rows") Integer rows,
                                               @Param("startRows") Integer pageSize);

}
