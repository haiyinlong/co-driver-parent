package com.leo.ad.codriver.dws.service.impl.pkg.ver.usrc;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcInvestedMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcInvested;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_invested(dws推广花费)】的数据库操作Service实现
 * @createDate 2024-11-26 17:28:36
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcInvestedServiceImpl implements DwsService {

    private final DwsDailyPkgVerUsrcInvestedMapper dwsDailyPkgVerUsrcInvestedMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcInvested, DwsDailyPkgVerUsrcInvestedMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void syncData(Integer dates) {

    }
}
