package com.leo.ad.codriver.dws.service.impl.pkg.ver.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgVerUsrcPromotionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgVerUsrcPromotion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_ver_usrc_promotion】的数据库操作Service实现
 * @createDate 2024-11-19 16:43:31
 */
@Order(Integer.MAX_VALUE)
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgVerUsrcPromotionServiceImpl implements DwsService {

    private final DwsDailyPkgVerUsrcPromotionMapper dwsDailyPkgVerUsrcPromotionMapper;
    private final DwBatchMapper<DwsDailyPkgVerUsrcPromotion, DwsDailyPkgVerUsrcPromotionMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgVerUsrcPromotion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgVerUsrcPromotion> dbList = dwsDailyPkgVerUsrcPromotionMapper.queryDbList(dates);
        List<DwsDailyPkgVerUsrcPromotion> statisticsList = dwsDailyPkgVerUsrcPromotionMapper.queryStatisticsList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        statisticsList.forEach(DwsDailyPkgVerUsrcPromotion::init);
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgVerUsrcPromotionMapper.class);

        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgVerUsrcPromotionMapper.deleteBatchIds(delIds);
        }

    }
}
