package com.leo.ad.codriver.dws.service.impl.pkg.usrc;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dws.dao.DwsDailyPkgUsrcPromotionMapper;
import com.leo.ad.codriver.dws.entity.DwsDailyPkgUsrcPromotion;
import com.leo.ad.codriver.dws.service.DwsService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 * @description 针对表【dws_daily_pkg_usrc_promotion】的数据库操作Service实现
 * @createDate 2024-11-20 17:07:05
 */
@Order(Integer.MAX_VALUE)
@Slf4j
@Service
@RequiredArgsConstructor
public class DwsDailyPkgUsrcPromotionServiceImpl implements DwsService {
    private final DwsDailyPkgUsrcPromotionMapper dwsDailyPkgUsrcPromotionMapper;
    private final DwBatchMapper<DwsDailyPkgUsrcPromotion, DwsDailyPkgUsrcPromotionMapper> dwBatchMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShowExecuteTime(name = "DwsDailyPkgUsrcPromotion")
    @Transactional(rollbackFor = Exception.class)
    @Lock(paramName = "#dates")
    @Override
    public void syncData(Integer dates) {
        List<DwsDailyPkgUsrcPromotion> dbList = dwsDailyPkgUsrcPromotionMapper.queryDbList(dates);
        List<DwsDailyPkgUsrcPromotion> statisticsList = dwsDailyPkgUsrcPromotionMapper.queryStatisticsList(dates);
        if (CollectionUtils.isEmpty(statisticsList)) {
            return;
        }
        statisticsList.forEach(DwsDailyPkgUsrcPromotion::init);
        dwBatchMapper.batchInsert(statisticsList, DwsDailyPkgUsrcPromotionMapper.class);

        List<Long> delIds = getDelIds(dbList, statisticsList, null);
        if (!CollectionUtils.isEmpty(delIds)) {
            dwsDailyPkgUsrcPromotionMapper.deleteBatchIds(delIds);
        }

    }
}
