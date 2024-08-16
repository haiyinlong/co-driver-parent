package com.leo.ad.seatunnel.monitor.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leo.ad.seatunnel.monitor.config.SeaTunnelMonitorConfig;
import com.leo.ad.seatunnel.monitor.dao.SeaTunnelJobDao;
import com.leo.ad.seatunnel.monitor.entity.SeaTunnelJob;
import com.leo.ad.seatunnel.monitor.service.SeaTunnelJobService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SeatunnelServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/07/10 17:33
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SeaTunnelJobServiceImpl implements SeaTunnelJobService {

    private final SeaTunnelJobDao seatunnelJobDao;
    private final SeaTunnelMonitorConfig seatunnelMonitorConfig;

    @Override
    public List<SeaTunnelJob> getAllJob() {
        return seatunnelJobDao.selectList(new LambdaQueryWrapper<SeaTunnelJob>().eq(SeaTunnelJob::getEnabledFlag, 1)
            .eq(SeaTunnelJob::getDeletedFlag, 0));
    }

    @Override
    public void startJob(@NotNull SeaTunnelJob seatunnelJob) {
        seatunnelJob.start(seatunnelMonitorConfig.getSeatunnelHome());
        seatunnelJobDao.updateById(seatunnelJob);
    }
}
