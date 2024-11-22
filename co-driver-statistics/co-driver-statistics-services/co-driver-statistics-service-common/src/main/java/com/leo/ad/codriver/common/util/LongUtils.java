package com.leo.ad.codriver.common.util;

import java.math.RoundingMode;

import org.springframework.util.ObjectUtils;

/**
 * BigDecimalUtils
 *
 * @author HaiYinLong
 * @version 2024/04/10 18:42
 **/
public class LongUtils {

    public static long divide(Long divisor, Long divided) {
        if (ObjectUtils.isEmpty(divided) || ObjectUtils.isEmpty(divisor) || divisor <= 0 || divided <= 0) {
            return 0;
        }
        return BigDecimalUtils.divide(divisor, divided).setScale(0, RoundingMode.UP).longValue();
    }

    public static long add(Long... values) {
        if (values != null && values.length > 0) {
            long result = 0L;
            for (Long value : values) {
                if (ObjectUtils.isEmpty(value)) {
                    continue;
                }
                result = result + value;
            }
            return result;
        }
        return 0L;
    }

    public static Long getDefault(Long value) {
        if (ObjectUtils.isEmpty(value)) {
            return 0L;
        }
        return value;
    }
}
