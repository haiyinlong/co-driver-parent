package com.leo.ad.codriver.dim.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dim.dao.DimChannelMapper;
import com.leo.ad.codriver.dim.service.DimService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @ShowExecuteTime(name = "DimChannel")
    @Transactional(rollbackFor = Exception.class)
    public void syncData() {
        dimChannelMapper.truncate();
        dimChannelMapper.syncData();
    }
}
