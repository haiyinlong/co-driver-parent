package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageRetention;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageRetentionMapper extends BaseMapper<DwsDailyPackageRetention> {
    void deleteByDates(@Param("dates") Integer dates);

    void syncData(@Param("dates") Integer dates);

    DwsDailyPackageRetention query(@Param("loginDates") Integer loginDates,
                                   @Param("registerDates") Integer registerDates, @Param("country") String country, @Param("pkg") String pkg,
                                   @Param("version") String version);

}
