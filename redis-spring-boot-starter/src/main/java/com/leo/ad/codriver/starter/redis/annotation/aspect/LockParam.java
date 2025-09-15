package com.leo.ad.codriver.starter.redis.annotation.aspect;

import lombok.Data;

import java.util.LinkedList;

/**
 * LockParam
 * @author HaiYinLong
 * @version 2024/08/02 17:45
**/
@Data
public class LockParam {
    private String param;
    private String value;
    private LinkedList<String> keys;
}
