package com.leo.ad.seatunnel.monitor.util;

import com.alibaba.fastjson2.JSONArray;
import com.leo.ad.seatunnel.monitor.config.SeaTunnelMonitorConfig;
import com.leo.ad.seatunnel.monitor.dto.SeaTunnelRunningJobDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

/**
 * SeaTunnelHttpUtils
 *
 * @author HaiYinLong
 * @version 2024/07/10 18:55
 **/
@Slf4j
public final class SeaTunnelHttpUtils {

    /**
     * 获取所有任务
     *
     * @return
     * @throws Exception
     */
    public static List<SeaTunnelRunningJobDto> getAllRunningJob(SeaTunnelMonitorConfig config) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(config.getSeatunnelBaseUrl() + "/hazelcast/rest/maps/running-jobs")).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            log.warn("返回非200, {}", response.body());
            throw new RuntimeException(response.body());
        }
        if (ObjectUtils.isEmpty(response.body())) {
            return Collections.emptyList();
        }
        return JSONArray.parseArray(response.body(), SeaTunnelRunningJobDto.class);
    }
}
