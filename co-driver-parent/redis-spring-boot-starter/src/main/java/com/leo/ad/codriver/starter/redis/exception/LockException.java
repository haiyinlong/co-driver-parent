package com.leo.ad.codriver.starter.redis.exception;

import java.io.Serial;

/**
 * LockException
 *
 * @author HaiYinLong
 * @version 2024/08/23 10:21
 **/
public class LockException extends Exception {
    @Serial
    private static final long serialVersionUID = -7398610566776047065L;

    public LockException() {
        super();
    }

    public LockException(String message) {
        super(message);
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockException(Throwable cause) {
        super(cause);
    }

    protected LockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
