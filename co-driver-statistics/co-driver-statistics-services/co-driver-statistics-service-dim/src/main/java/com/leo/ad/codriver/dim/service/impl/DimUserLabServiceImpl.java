package com.leo.ad.codriver.dim.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.dim.dao.DimUserLabMapper;
import com.leo.ad.codriver.dim.entity.DimUserLab;
import com.leo.ad.codriver.dim.service.DimUserLabService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DimUserLabServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/08/19 15:55
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DimUserLabServiceImpl implements DimUserLabService {
    private final DimUserLabMapper dimUserLabMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncUserLab(String odsUserLabId) {
        if (ObjectUtils.isEmpty(odsUserLabId)) {
            return;
        }
        long userLabId = Long.parseLong(odsUserLabId);
        // 汇总更新dim 数据, 用户会更新，
        DimUserLab userLab = dimUserLabMapper.getUserLab(userLabId);
        if (ObjectUtils.isEmpty(userLab)) {
            dimUserLabMapper.deleteBySourceId(userLabId);
            return;
        }
        if (ObjectUtils.isEmpty(userLab.getId())) {
            userLab.initDate();
            dimUserLabMapper.insert(userLab);
        } else {
            userLab.updateDate();
            dimUserLabMapper.updateById(userLab);
        }
    }
}
