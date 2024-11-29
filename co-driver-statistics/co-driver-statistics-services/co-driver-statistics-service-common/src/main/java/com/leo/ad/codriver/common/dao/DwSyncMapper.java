package com.leo.ad.codriver.common.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.common.dao.entity.DwSync;

/**
 * @author user
 * @description 针对表【dw_sync(数据同步配置)】的数据库操作Mapper
 * @createDate 2024-11-29 14:39:05
 * @Entity com.leo.ad.codriver.common.dao.entity.DwSync
 */
@Mapper
@DS("mysql")
public interface DwSyncMapper extends BaseMapper<DwSync> {

}
