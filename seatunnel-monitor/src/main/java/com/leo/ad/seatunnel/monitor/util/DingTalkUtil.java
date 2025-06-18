package com.leo.ad.seatunnel.monitor.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * DingTalkUtil
 *
 * @author HaiYinLong
 * @version 2025/06/18 14:55
 **/
public class DingTalkUtil {
    public static final DingTalkUtil INSTANCE = new DingTalkUtil();
    private static final RestTemplate REST_TEMPLATE;
    private static final String APP_NAME;
    private static final String webhookUrl =
        "https://oapi.dingtalk.com/robot/send?access_token=83d70b0c4a587aacc5289777152d02643df25b23d90b16c8ae57c753db0ccb9f";
    static {
        REST_TEMPLATE = new RestTemplate(new SimpleClientHttpRequestFactory());
        APP_NAME = System.getProperty("sun.java.command");
    }

    public void sendMessage(String conf, String jobId) {
        String message = APP_NAME + "\n数据同步Notice" + "\n启动任务: " + conf + "\n任务ID: " + jobId;
        sendToDingTalk(message);
    }

    private void sendToDingTalk(String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        String body = "{ \"msgtype\": \"text\", \"text\": {\"content\":\"" + content.replaceAll("\"", "\\\"") + "\"}}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        REST_TEMPLATE.postForObject(webhookUrl, entity, String.class);
    }

    public static void main(String[] args) {
        DingTalkUtil.INSTANCE.sendMessage("test", "test");
    }
}
