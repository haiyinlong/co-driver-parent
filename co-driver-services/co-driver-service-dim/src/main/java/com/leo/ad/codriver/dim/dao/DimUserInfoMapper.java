package com.leo.ad.codriver.dim.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.ad.codriver.dim.entity.DimUserInfo;
import com.leo.ad.codriver.dim.entity.RealTimeUserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * DimUserInfoMapper
 *
 * @author HaiYinLong
 * @version 2024/06/24 15:11
 **/
@Mapper
@DS("mysql")
public interface DimUserInfoMapper extends BaseMapper<DimUserInfo> {

    RealTimeUserInfoDTO queryRealTimeUserInfo(@Param("userId") Long userId);

    DimUserInfo getUserInfoByUserId(@Param("userId") Long userId);
}
