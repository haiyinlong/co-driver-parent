package com.leo.ad.codriver.starter.redis.annotation;

import lombok.Getter;

/**
 * LockEnum
 *
 * @author HaiYinLong
 * @version 2024/12/31 11:54
 **/
@Getter
public enum LockEnum {
    TRY_LOCK("tryLock"), LOCK("lock");

    private final String value;

    LockEnum(String value) {
        this.value = value;
    }
}
