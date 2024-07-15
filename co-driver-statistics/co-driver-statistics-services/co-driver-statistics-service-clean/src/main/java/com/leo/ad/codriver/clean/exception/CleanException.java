package com.leo.ad.codriver.clean.exception;

/**
 * CleanException
 *
 * @author HaiYinLong
 * @version 2024/07/12 17:51
 **/
public class CleanException extends Exception {
    public CleanException() {
    }

    public CleanException(String message) {
        super(message);
    }

    public CleanException(String message, Throwable cause) {
        super(message, cause);
    }

    public CleanException(Throwable cause) {
        super(cause);
    }

    public CleanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
