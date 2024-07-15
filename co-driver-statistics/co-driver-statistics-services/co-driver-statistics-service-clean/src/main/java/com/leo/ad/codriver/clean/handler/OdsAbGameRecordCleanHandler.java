package com.leo.ad.codriver.clean.handler;

import com.leo.ad.codriver.clean.dao.OdsAbGameRecordCleanDao;
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
public class OdsAbGameRecordCleanHandler extends AbstractCleanHandler {
    private final OdsAbGameRecordCleanDao odsAbGameRecordCleanDao;

    @Override
    public void cleanHandler() {
        // 清理明细；
        odsAbGameRecordCleanDao.deleteRecordDetailsByTwoMonthsAgo();
        // 清理主记录；
        odsAbGameRecordCleanDao.deleteRecordByTwoMonthsAgo();
    }
}
