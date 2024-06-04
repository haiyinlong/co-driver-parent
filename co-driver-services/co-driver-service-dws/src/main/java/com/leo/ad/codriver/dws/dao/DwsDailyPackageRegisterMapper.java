package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageRegister;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageRegisterMapper extends BaseMapper<DwsDailyPackageRegister> {

    void delete(@Param("dates") Integer dates);

    List<DwsDailyPackageRegister> statistics(@Param("dates") Integer dates);

    DwsDailyPackageRegister query(@Param("dates") Integer dates, @Param("country") String country,
                                  @Param("pkg") String pkg, @Param("version") String version);
}
