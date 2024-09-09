package com.leo.ad.codriver.dwd.service;

/**
 * DwdService,读取原始数据进行数据处理写入到dwd<br>
 * 通过代码分批从mysql读取数据再写入到dwd。<br>
 * 原始数据量大，通过sql直接处理会对数据库造成压力
 *
 * @author HaiYinLong
 * @version 2024/04/18 17:26
 **/
public interface DwdService {
    /**
     * 根据日期同步处理数据
     *
     * @param dates 20241010 日期
     * @return true 有修改数据，false 没有修改数据
     */
    boolean syncData(Integer dates);
}
