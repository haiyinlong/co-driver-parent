package com.leo.ad.codriver.common.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.dao.entity.DwSyncHandle;

/**
 * @author user
 * @description 针对表【dw_sync_handle(同步处理配置)】的数据库操作Mapper
 * @createDate 2024-11-29 17:13:22
 * @Entity com.leo.ad.codriver.common.dao.entity.DwSyncHandle
 */
@Mapper
@DS("mysql")
public interface DwSyncHandleMapper extends BaseMapper<DwSyncHandle> {

    DwSyncHandle queryByTableName(String tableName);
}
