package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageUserConversion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageUserConversionMapper extends BaseMapper<DwsDailyPackageUserConversion> {
    void deleteByDates(@Param("dates") Integer dates);

    void syncActiveList(@Param("dates") Integer dates);

    void syncNewList(@Param("dates") Integer dates);

    DwsDailyPackageUserConversion query(@Param("dates") Integer dates, @Param("country") String country,
                                        @Param("pkg") String pkg, @Param("version") String version);
}
