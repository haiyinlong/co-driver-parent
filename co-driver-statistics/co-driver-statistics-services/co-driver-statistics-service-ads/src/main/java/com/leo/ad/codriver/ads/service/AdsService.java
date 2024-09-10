package com.leo.ad.codriver.ads.service;

/**
 * AdsService, 读取dws层数据进行汇总<br>
 * 通过sql直接join进行汇总统计<br>
 * 每次进行更新不需要删除，再插入
 *
 * @author HaiYinLong
 * @version 2024/04/09 16:22
 **/
public interface AdsService {
    /**
     * 根据日期同步处理数据
     *
     * @param dates 20241010 日期
     */
    void syncData(Integer dates);
}
