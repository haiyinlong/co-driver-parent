package com.leo.ad.codriver.dws.service.impl.pkg.ver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdUserAdRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwCountDTO;
import com.leo.ad.codriver.dwd.entity.DwdUserAdRecord;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerAdvertisingMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerAdvertising;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_advertising(dws广告汇总统计，有新增的广告商就新增字段)】的数据库操作Service实现
 * @createDate 2024-11-21 19:36:26
 */
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerAdvertisingServiceImpl implements DwsService {

    private final DwsDailyPkgVerAdvertisingMapper dwsDailyPkgVerAdvertisingMapper;
    private final DwdUserAdRecordMapper dwdUserAdRecordMapper;

    private final DwBatchMapper<DwsDailyPkgVerAdvertising, DwsDailyPkgVerAdvertisingMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @ShowExecuteTime(name = "DwsDailyPkgVerAdvertising")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    public void syncData(Integer dates) {
        // 批量处理，一条一条的从数据库中获取与内存中的数据近汇总
        DwCountDTO dbCount = dwdUserAdRecordMapper.getDbCountOfId(dates);
        Long minId = dbCount.getMinId();
        Long maxId = dbCount.getMaxId();
        List<DwdUserAdRecord> dbList = new ArrayList<>();
        do {
            // dbList = dwdUserAdRecordMapper.queryDbListByDate(dates, minId - 1, minId + 20000);
            minId += 20000;
        } while (minId < maxId);
    }

}
