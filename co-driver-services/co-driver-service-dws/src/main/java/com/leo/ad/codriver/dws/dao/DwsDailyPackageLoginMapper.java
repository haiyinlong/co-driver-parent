package com.leo.ad.codriver.dws.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPackageLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwsDailyPackageLoginMapper extends BaseMapper<DwsDailyPackageLogin> {

    void delete(@Param("dates") Integer dates);

    void syncDailyPackageLogin(@Param("dates") Integer dates);

    List<DwsDailyPackageLogin> list(@Param("dates") Integer dates, @Param("productId") Long productId);
}
