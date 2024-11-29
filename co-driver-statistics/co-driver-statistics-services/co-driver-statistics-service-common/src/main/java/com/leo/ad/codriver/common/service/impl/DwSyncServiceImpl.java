package com.leo.ad.codriver.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.ad.codriver.common.dao.DwSyncMapper;
import com.leo.ad.codriver.common.dao.entity.DwSync;
import com.leo.ad.codriver.common.service.DwSyncService;

import lombok.RequiredArgsConstructor;

/**
 * DwSyncServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/11/29 14:37
 **/
@Service
@RequiredArgsConstructor
public class DwSyncServiceImpl implements DwSyncService {
    private final DwSyncMapper dwSyncMapper;

    @Override
    public List<DwSync> queryList() {
        return dwSyncMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public DwSync getSyncById(Long id) {
        return dwSyncMapper.selectById(id);
    }
}
