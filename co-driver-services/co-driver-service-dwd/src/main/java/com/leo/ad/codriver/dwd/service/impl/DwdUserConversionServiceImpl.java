package com.leo.ad.codriver.dwd.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserConversionMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserConversion;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HaiYinLong
 * @version 2024/04/09 15:15
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserConversionServiceImpl implements DwdService {
    private final DwdUserConversionMapper dwdUserConversionMapper;
    private final DwBatchMapper<DwdUserConversion, DwdUserConversionMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserConversion syncData")
    public void syncData(Integer dates) {
        dwdUserConversionMapper.delete(dates);
        // 查询自己的转化记录
        Long totalRecord = dwdUserConversionMapper.countFromOfferRecord(dates);
        long totalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());
        for (int i = 0; i < totalPageNum; i++) {
            List<DwdUserConversion> dwdUserConversionList = dwdUserConversionMapper.statisticsFromOfferRecord(dates,
                    BatchConst.BATCH_NUMBER.intValue(), i * BatchConst.BATCH_NUMBER.intValue());
            dwBatchMapper.batchInsert(dwdUserConversionList, DwdUserConversionMapper.class);

        }

        // 查询河马的转化记录
        totalRecord = dwdUserConversionMapper.countFromHemaOfferRecord(dates);
        long hemaTotalPageNum = LongUtils.divide(totalRecord, BatchConst.BATCH_NUMBER.longValue());

        for (int i = 0; i < hemaTotalPageNum; i++) {
            List<DwdUserConversion> dwdUserConversionList = dwdUserConversionMapper.statisticsFromHemaOfferRecord(dates,
                    BatchConst.BATCH_NUMBER.intValue(), i * BatchConst.BATCH_NUMBER.intValue());
            dwBatchMapper.batchInsert(dwdUserConversionList, DwdUserConversionMapper.class);
        }
    }
}
