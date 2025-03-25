package com.leo.ad.codriver.clean.handler;

import org.springframework.stereotype.Component;

import com.leo.ad.codriver.clean.dao.DwdUserEventCleanDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HaiYinLong
 * @version 2024/07/12 18:26
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserEventCleanHandler extends AbstractCleanHandler {
    private final DwdUserEventCleanDao dwdUserEventCleanDao;

    @Override
    public void cleanHandler() {
        dwdUserEventCleanDao.deleteByLastMonthsAgo();
    }
}
