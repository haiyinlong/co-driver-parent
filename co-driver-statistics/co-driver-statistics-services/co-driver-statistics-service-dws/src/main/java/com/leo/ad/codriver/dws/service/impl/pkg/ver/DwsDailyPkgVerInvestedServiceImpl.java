package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerInvestedMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerInvested;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_invested(dws推广花费)】的数据库操作Service实现
 * @createDate 2024-11-26 17:28:36
 */
@Order(Integer.MAX_VALUE)
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerInvestedServiceImpl implements DwsService {
    private final DwsDailyPkgVerInvestedMapper dwsDailyPkgVerInvestedMapper;
    private final DwBatchMapper<DwsDailyPkgVerInvested, DwsDailyPkgVerInvestedMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void syncData(Integer dates) {
        // 根据dwd数据进行汇总
    }
}
