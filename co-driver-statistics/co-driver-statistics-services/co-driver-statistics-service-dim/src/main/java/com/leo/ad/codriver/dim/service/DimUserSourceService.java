package com.leo.ad.codriver.dim.service;

/**
 * @author user
 * @description 针对表【dim_user_source(用户来源)】的数据库操作Service
 * @createDate 2024-11-19 14:38:36
 */
public interface DimUserSourceService {
    void syncUserSource(String odsUserAttributeMsg);
}
