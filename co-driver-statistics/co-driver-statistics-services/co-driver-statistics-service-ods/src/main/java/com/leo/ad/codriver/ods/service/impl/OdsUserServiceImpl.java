package com.leo.ad.codriver.ods.service.impl;

import com.leo.ad.codriver.ods.dao.OdsUserMapper;
import com.leo.ad.codriver.ods.service.OdsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * OdsUserServiceImpl
 *
 * @author HaiYinLong
 * @version 2026/02/04 13:30
 **/
@Slf4j
@Order(1)
@Service
@RequiredArgsConstructor
public class OdsUserServiceImpl implements OdsService {
    private final OdsUserMapper odsUserMapper;

    @Override
    public void syncData(Integer dates) {
        odsUserMapper.updateCreateTime(dates);
        log.info("{}  {}  ods数据同步完成", dates, this.getClass().getSimpleName());
    }
}
