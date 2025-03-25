package com.leo.ad.codriver.clean.handler;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.clean.dao.OdsEventReportCleanDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
public class OdsEventReportCleanHandler extends AbstractCleanHandler {
    private final OdsEventReportCleanDao odsEventReportCleanDao;

    @Override
    public void cleanHandler() {
        odsEventReportCleanDao.deleteByLastMonthsAgo();
    }
}
