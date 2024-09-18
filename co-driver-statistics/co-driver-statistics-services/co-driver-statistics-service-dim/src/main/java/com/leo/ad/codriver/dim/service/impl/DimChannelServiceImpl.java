package com.leo.ad.codriver.dim.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dim.dao.DimChannelMapper;
import com.leo.ad.codriver.dim.entity.DimChannel;
import com.leo.ad.codriver.dim.service.DimService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.AllArgsConstructor;

/**
 * DimChannelService
 *
 * @author HaiYinLong
 * @version 2024/04/30 11:59
 **/
@Service
@AllArgsConstructor
public class DimChannelServiceImpl implements DimService {
    private final DimChannelMapper dimChannelMapper;
    private final DwBatchMapper<DimChannel, DimChannelMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "DimChannel")
    @Transactional(rollbackFor = Exception.class)
    @Lock
    public void syncData() {
        List<DimChannel> list = dimChannelMapper.list();

        List<DimChannel> statistcsList = dimChannelMapper.queryStatistics();
        dwBatchMapper.batchInsert(statistcsList, DimChannelMapper.class);

        List<Long> delIds = getDelIds(list, statistcsList, Collections.emptyList());
        if (!CollectionUtils.isEmpty(delIds)) {
            dimChannelMapper.deleteBatchIds(delIds);
        }
    }
}
