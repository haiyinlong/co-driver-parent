package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserConversion;

/**
 * dwd每日同期群转化Mapper
 *
 * @author HaiYinLong
 * @version 2024/04/22 15:11
 **/

@Mapper
@DS("mysql")
public interface DwdUserConversionMapper extends BaseMapper<DwdUserConversion> {
    /**
     * 统计指定日期内部服务的同期转化数据集合
     *
     * @param dates 日期
     * @return
     */
    List<DwdUserConversion> statisticsFromOfferRecord(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer startRows);

    Long countFromOfferRecord(@Param("dates") Integer dates);

    /**
     * 统计指定日期河马服务的同期转化数据集合
     *
     * @param dates 日期
     * @return
     */
    List<DwdUserConversion> statisticsFromHemaOfferRecord(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer startRows);

    Long countFromHemaOfferRecord(@Param("dates") Integer dates);

    Integer deleteByDates(@Param("dates") Integer dates);
}
