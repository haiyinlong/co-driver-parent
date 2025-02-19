package com.leo.ad.codriver.dwd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.dwd.dto.DwdUserEventWithRegisterDateDTO;
import com.leo.ad.codriver.dwd.dto.DwdUserEventWithUserSourceDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserEvent;

/**
 * 用户事件记录表
 *
 * @author HaiYinLong
 * @version 2024/04/09 15:11
 **/
@Mapper
@DS("mysql")
public interface DwdUserEventMapper extends BaseMapper<DwdUserEvent> {

    DwCountDTO getCountByDate(@Param("dates") Integer dates, @Param("events") List<String> events);

    List<DwdUserEventWithRegisterDateDTO> queryWithRegisterDateByInterval(@Param("dates") Integer dates,
        @Param("events") List<String> events, @Param("startId") long startId, @Param("endId") long endId);

    List<DwdUserEventWithUserSourceDTO> queryWithRegisterDateAndSourceByInterval(@Param("dates") Integer dates,
        @Param("events") List<String> adEventList, @Param("startId") long startId, @Param("endId") long endId);

    void deleteByDate(@Param("dates") Integer dates);

}
