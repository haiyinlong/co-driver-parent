package com.leo.ad.codriver.clean.handler;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.clean.dao.OdsReportEventCleanDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * OdsUserGameGoodsRecordCleanHandler
 *
 * @author HaiYinLong
 * @version 2024/07/12 18:26
 **/
@Slf4j
@Component
@Order(Integer.MIN_VALUE)
@RequiredArgsConstructor
public class OdsUserGameGoodsRecordCleanHandler extends AbstractCleanHandler {
    private final OdsReportEventCleanDao odsReportEventCleanDao;

    @Override
    public void cleanHandler() {
        odsReportEventCleanDao.deleteByTwoMonthsAgo();
    }
}
