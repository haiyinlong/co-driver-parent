package com.leo.ad.codriver.clean.handler;

import com.leo.ad.codriver.clean.dao.DwdUserEventDetailCleanDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
        dwdUserEventDetailCleanDao.deleteByTwoMonthsAgo();
    }
}
