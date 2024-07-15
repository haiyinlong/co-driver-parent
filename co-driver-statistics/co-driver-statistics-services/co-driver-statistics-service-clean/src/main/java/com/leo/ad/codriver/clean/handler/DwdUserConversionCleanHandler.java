package com.leo.ad.codriver.clean.handler;

import com.leo.ad.codriver.clean.dao.DwdUserConversionCleanDao;
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
public class DwdUserConversionCleanHandler extends AbstractCleanHandler {
    private final DwdUserConversionCleanDao dwdUserConversionCleanDao;

    @Override
    public void cleanHandler() {
        dwdUserConversionCleanDao.deleteByTwoMonthsAgo();
    }
}
