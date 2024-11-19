package com.leo.ad.codriver.dws.service.impl.usrc;

import org.springframework.stereotype.Service;

import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcConversionMapper;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_conversion】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:30
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcConversionServiceImpl implements DwsService {
    private final DwsDailyPkgVerUsrcConversionMapper dwsDailyPkgVerUsrcConversionMapper;

    @Override
    public void syncData(Integer dates) {

    }
}
