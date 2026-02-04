package com.leo.ad.codriver.ods.service.impl;

import com.leo.ad.codriver.ods.dao.OdsUserVersionMapper;
import com.leo.ad.codriver.ods.service.OdsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 *
 * @author HaiYinLong
 * @version 2026/02/04 13:30
 **/
@Slf4j
@Order(2)
@Service
@RequiredArgsConstructor
public class OdsUserVersionServiceImpl implements OdsService {
    private final OdsUserVersionMapper odsUserVersionMapper;

    @Override
    public void syncData(Integer dates) {
        odsUserVersionMapper.updateCreateTime(dates);
        log.info("{}  {}  ods数据同步完成", dates, this.getClass().getSimpleName());
    }
}
