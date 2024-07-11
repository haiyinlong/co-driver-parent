package com.leo.ad.seatunnel.monitor.service.impl;

import com.leo.ad.seatunnel.monitor.config.SeaTunnelMonitorConfig;
import com.leo.ad.seatunnel.monitor.dto.SeaTunnelRunningJobDto;
import com.leo.ad.seatunnel.monitor.entity.SeaTunnelJob;
import com.leo.ad.seatunnel.monitor.service.SeaTunnelJobService;
import com.leo.ad.seatunnel.monitor.service.SeaTunnelMonitorService;
import com.leo.ad.seatunnel.monitor.util.SeaTunnelHttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * SeaTunnelMonitorServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/07/10 19:02
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SeaTunnelMonitorServiceImpl implements SeaTunnelMonitorService {
    private final SeaTunnelJobService seaTunnelJobService;
    private final SeaTunnelMonitorConfig seatunnelMonitorConfig;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handle() {
        try {
            List<SeaTunnelJob> configJobs = seaTunnelJobService.getAllJob();
            if (CollectionUtils.isEmpty(configJobs)) {
                return;
            }
            List<SeaTunnelRunningJobDto> runningJobs = SeaTunnelHttpUtils.getAllRunningJob(seatunnelMonitorConfig);
            // 比较配置任务和运行任务，启动未启动的任务
            if (CollectionUtils.isEmpty(runningJobs)) {
                // 启动所有任务
                configJobs.forEach(seaTunnelJobService::startJob);
            } else {
                // 判断不存在就运行
                List<String> runningJobIds = runningJobs.stream().map(SeaTunnelRunningJobDto::getJobId).toList();
                configJobs.stream().filter(seaTunnelJob -> !runningJobIds.contains(seaTunnelJob.getJobId())).forEach(seaTunnelJobService::startJob);
            }
        } catch (Exception e) {
            log.error("获取运行任务异常", e);
        }
    }
}
