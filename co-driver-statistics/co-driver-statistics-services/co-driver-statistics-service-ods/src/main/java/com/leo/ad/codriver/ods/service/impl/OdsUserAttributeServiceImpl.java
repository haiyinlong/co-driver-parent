package com.leo.ad.codriver.ods.service.impl;

import com.leo.ad.codriver.ods.dao.OdsUserAttributeMapper;
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
@Order(3)
@Service
@RequiredArgsConstructor
public class OdsUserAttributeServiceImpl implements OdsService {
    private final OdsUserAttributeMapper odsUserAttributeMapper;

    @Override
    public void syncData(Integer dates) {
        odsUserAttributeMapper.updateCreateTime(dates);
        log.info("{}  {}  ods数据同步完成", dates, this.getClass().getSimpleName());
    }
}
