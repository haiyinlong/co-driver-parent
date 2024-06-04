package com.leo.ad.codriver.common.util;

import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * BigDecimalUtils
 *
 * @author HaiYinLong
 * @version 2024/04/10 18:42
 **/
public class BigDecimalUtils {
    public static BigDecimal reserved2(BigDecimal value) {
        if (Objects.isNull(value)) {
            return BigDecimal.ZERO;
        }

        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public static String reserved2ToString(BigDecimal value) {
        return reserved2(value).toString();
    }

    public static BigDecimal dividePercentage(Long divisor, Long divided) {
        if (ObjectUtils.isEmpty(divided) || ObjectUtils.isEmpty(divisor) || divisor <= 0 || divided <= 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(divisor).divide(BigDecimal.valueOf(divided), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }

    public static BigDecimal divide(Long divisor, Long divided) {
        if (ObjectUtils.isEmpty(divided) || ObjectUtils.isEmpty(divisor) || divisor <= 0 || divided <= 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(divisor).divide(BigDecimal.valueOf(divided), 8, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(BigDecimal divisor, BigDecimal divided) {
        if (ObjectUtils.isEmpty(divided) || ObjectUtils.isEmpty(divisor) || BigDecimal.ZERO.compareTo(divisor) == 0
                || BigDecimal.ZERO.compareTo(divided) == 0) {
            return BigDecimal.ZERO;
        }
        return divisor.divide(divided, 8, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(BigDecimal divisor, Long divided) {
        if (ObjectUtils.isEmpty(divided) || ObjectUtils.isEmpty(divisor) || BigDecimal.ZERO.compareTo(divisor) == 0
                || divided == 0) {
            return BigDecimal.ZERO;
        }
        return divisor.divide(BigDecimal.valueOf(divided), 8, RoundingMode.HALF_UP);
    }

    public static BigDecimal divideReserved2(Long divisor, Long divided) {
        return BigDecimal.valueOf(divisor).divide(BigDecimal.valueOf(divided), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal add(BigDecimal... values) {
        if (values != null && values.length > 0) {
            BigDecimal result = BigDecimal.ZERO;
            for (BigDecimal value : values) {
                if (ObjectUtils.isEmpty(value)) {
                    continue;
                }
                result = result.add(value);
            }
            return result;
        }
        return BigDecimal.ZERO;
    }
}
