package com.leo.ad.codriver.clean.handler;

import com.leo.ad.codriver.clean.dao.OdsAdjustCostCleanDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * OdsAbGameRecordClean
 *
 * @author HaiYinLong
 * @version 2024/07/12 18:26
 **/
@Slf4j
@Component
@Order(Integer.MIN_VALUE)
@RequiredArgsConstructor
public class OdsAdjustCostCleanHandler extends AbstractCleanHandler {
    private final OdsAdjustCostCleanDao odsAdjustCostCleanDao;

    @Override
    public void cleanHandler() {
        odsAdjustCostCleanDao.deleteByTwoMonthsAgo();
    }
}
