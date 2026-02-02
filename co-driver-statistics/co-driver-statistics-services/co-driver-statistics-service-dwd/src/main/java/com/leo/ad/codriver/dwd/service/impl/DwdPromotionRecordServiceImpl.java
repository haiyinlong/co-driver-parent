package com.leo.ad.codriver.dwd.service.impl;

import com.leo.ad.codriver.common.DwCountDTO;
import com.leo.ad.codriver.common.ExchangeRate;
import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.dwd.dao.DwdPromotionRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdPromotionRecord;
import com.leo.ad.codriver.dwd.event.DwdPromotionRecordUpdateDwEvent;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * DwdPromotionRecordServiceImpl
 *
 * @author HaiYinLong
 * @version 2024/09/01 15:48
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DwdPromotionRecordServiceImpl implements DwdService {
    private final DwdPromotionRecordMapper dwdPromotionRecordMapper;
    private final ExchangeRate exchangeRate;
    private final DwBatchMapper<DwdPromotionRecord, DwdPromotionRecordMapper> batchMapper;

    @Override
    @ShowExecuteTime(name = "dwdPromotionRecord syncData")
    @AutoPushEventWithTrue(events = {DwdPromotionRecordUpdateDwEvent.class})
    @Lock(paramName = "#dates")
    public boolean syncData(Integer dates) {
        try {
            TimeUnit.MINUTES.sleep(2);
        } catch (InterruptedException e) {
            log.info(" {} 推广花费数据，延迟2分钟处理", dates);
            throw new RuntimeException(e);
        }
        dwdPromotionRecordMapper.deleteByDate(dates);

        DwCountDTO dbCount = dwdPromotionRecordMapper.getOdsDbCountOfId(dates);
        if (ObjectUtils.isEmpty(dbCount) || dbCount.getCount() == 0) {
            log.info("ods_adjust_cost {} 统计数据为空，跳过处理", dates);
            return false;
        }
        int loopPageRowNum = dbCount.loopNum();
        for (int i = 1; i <= dbCount.loopNum(loopPageRowNum); i++) {
            List<DwdPromotionRecord> promotionRecordList =
                dwdPromotionRecordMapper.queryOdsByDateAndId(dates, exchangeRate.getIndianToDollar(),
                    dbCount.loopStartId(i), dbCount.loopEndId(i));
            if (CollectionUtils.isEmpty(promotionRecordList)) {
                continue;
            }
            promotionRecordList.forEach(DwdPromotionRecord::init);
            batchMapper.batchInsert(promotionRecordList, DwdPromotionRecordMapper.class);
        }
        return true;
    }
}
