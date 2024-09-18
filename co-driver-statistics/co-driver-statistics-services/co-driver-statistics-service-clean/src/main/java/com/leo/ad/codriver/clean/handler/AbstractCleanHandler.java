package com.leo.ad.codriver.clean.handler;

import com.leo.ad.codriver.clean.exception.CleanException;

import lombok.extern.slf4j.Slf4j;

/**
 * AbstractCleanHandler
 *
 * @author HaiYinLong
 * @version 2024/07/12 17:59
 **/
@Slf4j
public abstract class AbstractCleanHandler implements CleanHandler {
    protected CleanHandler nextHandler;

    @Override
    public void setNextHandler(CleanHandler handler) {
        this.nextHandler = handler;
    }

    protected void next() {
        if (nextHandler != null) {
            nextHandler.executeClean();
        }
    }

    protected abstract void cleanHandler() throws CleanException;

    @Override
    public void executeClean() {
        try {
            log.info("{} 开始执行", this.getClass().getSimpleName());
            cleanHandler();
            log.info("{} 执行完成", this.getClass().getSimpleName());
        } catch (Exception e) {
            log.error(this.getClass().getSimpleName() + "清除数据异常", e);
        } finally {
            next();
        }
    }
}
