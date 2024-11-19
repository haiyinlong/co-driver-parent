package com.leo.ad.codriver.dws.service.impl.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcRegisterMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcRegister;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_register】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:31
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcRegisterServiceImpl implements DwsService {
    private final DwsDailyPkgVerUsrcRegisterMapper dwsDailyPkgVerUsrcRegisterMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcRegister, DwsDailyPkgVerUsrcRegisterMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgVerUsrcRegister> dbList = dwsDailyPkgVerUsrcRegisterMapper.queryDbList(dates);
        List<DwsDailyPkgVerUsrcRegister> statisticsList = dwsDailyPkgVerUsrcRegisterMapper.queryStatisticList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgVerUsrcRegisterMapper.class);
        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        dwsDailyPkgVerUsrcRegisterMapper.deleteBatchIds(delIds);
    }
}
