package com.leo.ad.codriver.clean.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.leo.ad.codriver.clean.dao.SkywalkingTableCleanDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SkywalkingTableCleanHandler
 *
 * @author HaiYinLong
 * @version 2024/07/12 18:26
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class SkywalkingTableCleanHandler extends AbstractCleanHandler {
    private final SkywalkingTableCleanDao skywalkingTableCleanDao;
    private final static List<String> TABLES = List.of("alarm_record", "alarm_record_tag", "browser_error_log",
        "ebpf_profiling_data", "ebpf_profiling_schedule", "ebpf_profiling_task", "endpoint_relation_server_side",
        "endpoint_traffic", "events", "instance_hierarchy_relation", "instance_traffic", "log", "log_tag", "meter_avg",
        "meter_avghistogrampercentile", "meter_avglabeled", "meter_latest", "meter_max", "meter_maxlabeled",
        "meter_sum", "meter_sumhistogrampercentile", "meter_sumlabeled", "metrics_apdex", "metrics_count",
        "metrics_cpm", "metrics_doubleavg", "metrics_longavg", "metrics_max", "metrics_percent", "metrics_percentile2",
        "metrics_rate", "metrics_sum", "network_address_alias", "process_relation_client_side",
        "process_relation_server_side", "process_traffic", "profile_task", "profile_task_log",
        "profile_task_segment_snapshot", "sampled_slow_trace_record", "sampled_status_4xx_trace_record",
        "sampled_status_5xx_trace_record", "segment", "segment_tag", "service_hierarchy_relation",
        "service_instance_relation_client_side", "service_instance_relation_server_side", "service_label",
        "service_relation_client_side", "service_relation_server_side", "service_traffic", "span_attached_event_record",
        "tag_autocomplete", "top_n_cache_read_command", "top_n_cache_write_command", "top_n_database_statement",
        "zipkin_query", "zipkin_service_relation_traffic", "zipkin_service_span_traffic", "zipkin_service_traffic",
        "zipkin_span");

    @Override
    public void cleanHandler() {
        // 动态删除表,清除3天之前的数据
        // Integer previousDate = DateUtils.getPreviousDate(2);
        // TABLES.forEach(tableName -> skywalkingTableCleanDao.deleteByTableNameAndDate(tableName, previousDate));
    }

}
