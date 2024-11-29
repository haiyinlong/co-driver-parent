package com.leo.ad.codriver.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.dao.entity.DwTaskRecord;

/**
 * @author user
 * @description 针对表【dw_task_record(数据同步记录,针对把的执行记录)】的数据库操作Mapper
 * @createDate 2024-11-11 15:21:49
 * @Entity com.leo.ad.codriver.common.dao.enetity.DwTaskRecord
 */
@Mapper
@DS("mysql")
public interface DwTaskRecordMapper extends BaseMapper<DwTaskRecord> {

    DwTaskRecord getLastTaskRecord(@Param("dates") Integer dates, @Param("type") String type);

    List<DwTaskRecord> queryTaskRecord(@Param("dates") Integer dates, @Param("type") String type,
        @Param("status") int status);

    DwTaskRecord queryFirstTaskRecordNotDone();

}
