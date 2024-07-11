package com.leo.ad.seatunnel.monitor.service;

import com.leo.ad.seatunnel.monitor.entity.SeaTunnelJob;

import java.util.List;

/**
 * SeatunnelService
 *
 * @author HaiYinLong
 * @version 2024/07/10 17:20
 **/
public interface SeaTunnelJobService {

    List<SeaTunnelJob> getAllJob();

    void startJob(SeaTunnelJob seatunnelJob);
}
