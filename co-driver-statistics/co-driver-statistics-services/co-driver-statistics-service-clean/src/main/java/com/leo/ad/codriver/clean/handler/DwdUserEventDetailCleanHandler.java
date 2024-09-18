package com.leo.ad.codriver.clean.handler;

import org.springframework.stereotype.Component;

import com.leo.ad.codriver.clean.dao.DwdUserEventDetailCleanDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HaiYinLong
 * @version 2024/07/12 18:26
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserEventDetailCleanHandler extends AbstractCleanHandler {
    private final DwdUserEventDetailCleanDao dwdUserEventDetailCleanDao;

    @Override
    public void cleanHandler() {
        Integer delRowNum = dwdUserEventDetailCleanDao.deleteByLastMonthsAgo();
        while (delRowNum > 0) {
            delRowNum = dwdUserEventDetailCleanDao.deleteByLastMonthsAgo();
        }
    }
}
