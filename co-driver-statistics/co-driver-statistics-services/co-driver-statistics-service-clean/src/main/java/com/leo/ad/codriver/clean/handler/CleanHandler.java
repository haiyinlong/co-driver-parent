package com.leo.ad.codriver.clean.handler;

/**
 * CleanHandler
 *
 * @author HaiYinLong
 * @version 2024/07/12 17:50
 **/
public interface CleanHandler {
    void setNextHandler(CleanHandler handler);

    void executeClean();
}
