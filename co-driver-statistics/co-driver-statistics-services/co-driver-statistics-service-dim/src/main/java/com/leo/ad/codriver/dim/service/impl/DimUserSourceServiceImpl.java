package com.leo.ad.codriver.dim.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dim.dao.DimUserSourceMapper;
import com.leo.ad.codriver.dim.entity.DimUserSource;
import com.leo.ad.codriver.dim.service.DimUserSourceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dim_user_source(用户来源)】的数据库操作Service实现
 * @createDate 2024-11-19 14:38:36
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DimUserSourceServiceImpl implements DimUserSourceService {
    private final DimUserSourceMapper dimUserSourceMapper;

    @Override
    public void syncUserSource(String odsUserAttributeMsg) {
        DimUserSource dimUserSource = JSONObject.parseObject(odsUserAttributeMsg, DimUserSource.class);
        DimUserSource dbUserSource = dimUserSourceMapper.selectById(dimUserSource.getUserId());
        if (ObjectUtils.isEmpty(dbUserSource)) {
            dimUserSourceMapper.insert(dimUserSource);
        } else {
            dimUserSourceMapper.updateById(dimUserSource);
        }
    }

}
