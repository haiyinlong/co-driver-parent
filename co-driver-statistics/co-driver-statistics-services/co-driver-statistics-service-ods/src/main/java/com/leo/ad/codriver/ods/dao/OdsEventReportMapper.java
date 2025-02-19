package com.leo.ad.codriver.ods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.ods.entity.OdsEventReport;

/**
 * @author user
 * @description 针对表【ods_event_report】的数据库操作Mapper
 * @createDate 2025-02-18 18:21:04
 * @Entity com.leo.ad.codriver.ods.entity.OdsEventReport
 */
@Mapper
@DS("mysql")
public interface OdsEventReportMapper extends BaseMapper<OdsEventReport> {

    DwCountDTO getOdsCount(@Param("dates") Integer dates);

    List<OdsEventReport> queryOdsByInterval(@Param("dates") Integer dates, @Param("startId") long startId,
        @Param("endId") long endId);
}
