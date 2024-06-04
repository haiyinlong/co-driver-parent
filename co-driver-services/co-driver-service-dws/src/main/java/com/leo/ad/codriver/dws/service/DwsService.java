package com.leo.ad.codriver.dws.service;

/**
 * DwsService, 读取dwd层数据进行汇总写入到dws层<br>
 * 可以直接通过sql进行汇总处理，需要维表时可以join维表进行处理
 *
 * @author HaiYinLong
 * @version 2024/04/15 18:34
 **/
public interface DwsService {
    /**
     * 根据日期同步处理数据
     *
     * @param dates 20241010 日期
     */
    void syncData(Integer dates);
}
