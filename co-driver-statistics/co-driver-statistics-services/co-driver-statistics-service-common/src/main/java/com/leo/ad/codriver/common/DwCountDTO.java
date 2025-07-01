package com.leo.ad.codriver.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import com.leo.ad.codriver.common.util.BigDecimalUtils;

import lombok.Data;

/**
 * DwCountDTO
 *
 * @author HaiYinLong
 * @version 2024/11/29 10:38
 **/
@Data
public class DwCountDTO {
    private Long minId;
    private Long maxId;
    private Long count;

    private static final long LOOP_PAGE_ROW_NUM = 1000L;
    private Long loopPageRowNum;

    public int loopNum() {
        return loopNum(LOOP_PAGE_ROW_NUM);
    }

    public int loopNum(long pageRowNum) {
        if (Objects.isNull(maxId) || Objects.isNull(minId)) {
            return 0;
        }
        if (Objects.equals(maxId, minId)) {
            return 1;
        }
        if (count < pageRowNum) {
            return 1;
        }
        this.loopPageRowNum = pageRowNum;
        return BigDecimalUtils.divide(BigDecimal.valueOf((maxId - minId)), BigDecimal.valueOf(loopPageRowNum))
            .setScale(0, RoundingMode.UP).intValue();
    }

    /**
     * 获取当前循环的开始id
     *
     * @param currentLoopNum 次数大于0
     * @return
     */
    public long loopStartId(int currentLoopNum) {
        if (currentLoopNum <= 0) {
            throw new RuntimeException("currentLoopNum must > 0");
        }
        if (this.loopPageRowNum == null) {
            this.loopPageRowNum = LOOP_PAGE_ROW_NUM;
        }
        if (count < this.loopPageRowNum) {
            return minId;
        }
        return minId + ((currentLoopNum - 1) * loopPageRowNum);
    }

    public long loopEndId(int currentLoopNum) {
        if (this.loopPageRowNum == null) {
            this.loopPageRowNum = LOOP_PAGE_ROW_NUM;
        }
        if (count < this.loopPageRowNum) {
            return maxId;
        }

        long endId = minId + (currentLoopNum * loopPageRowNum);
        if (endId > maxId) {
            return maxId;
        }
        return endId;
    }
}
