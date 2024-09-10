package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserEventDetail;

/**
 * 用户事件记录表
 *
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwdUserEventDetailMapper extends BaseMapper<DwdUserEventDetail> {
    Integer deleteByDates(@Param("dates") Integer dates, @Param("source") String source);

    long getEventReportCount(@Param("dates") Integer dates);

    List<DwdUserEventDetail> queryEventReport(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer pageSize);

    long getReportEventCount(@Param("dates") Integer dates);

    List<DwdUserEventDetail> queryReportEvent(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer pageSize);

    long getReportPointCount(@Param("dates") Integer dates);

    List<DwdUserEventDetail> queryReportPoint(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer pageSize);

    long getAdsReportPointCount(@Param("dates") Integer dates);

    List<DwdUserEventDetail> queryAdsReportPoint(@Param("dates") Integer dates, @Param("rows") Integer rows,
        @Param("startRows") Integer pageSize);

    DwdUserEventDetail getStatisticsEventReport(@Param("source") String source, @Param("sourceId") Long sourceId);

    void deleteEventReportBySourceId(@Param("source") String source, @Param("sourceId") Long sourceId);
}
