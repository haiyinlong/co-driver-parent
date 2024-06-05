package com.leo.ad.codriver.dwd.service.impl;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import com.leo.ad.codriver.common.async.AsyncThreadExecutor;
import com.leo.ad.codriver.common.util.LongUtils;
import com.leo.ad.codriver.dwd.dao.DwdUserLoginRecordMapper;
import com.leo.ad.codriver.dwd.entity.DwdUserLoginRecord;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.starter.mysql.BatchConst;
import com.leo.ad.codriver.starter.mysql.DwBatchMapper;
import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 每天凌晨更新的会有有一小部分数据丢失<br>
 * 用户在0点后更新最后登录事件，导致数据变成第二天的数据了。
 *
 * @author HaiYinLong
 * @version 2024/04/30 15:15
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DwdUserLoginRecordServiceImpl implements DwdService {
    private final DwdUserLoginRecordMapper dwdUserLoginRecordMapper;
    private final DwBatchMapper<DwdUserLoginRecord, DwdUserLoginRecordMapper> dwBatchMapper;

    @Override
    @ShowExecuteTime(name = "dwdUserLoginRecord  syncData")
    @Lock(paramName = "dates")
    public void syncData(Integer dates) {
        dwdUserLoginRecordMapper.delete(dates);
        // 分页查询数据
        List<Long> userIds = dwdUserLoginRecordMapper.queryLoginUserIds(dates);
        long totalPageNum = LongUtils.divide((long) userIds.size(), BatchConst.BATCH_NUMBER.longValue());

        AsyncThreadExecutor asyncThreadExecutor = AsyncThreadExecutor.of((int) totalPageNum);
        for (int i = 0; i < totalPageNum; i++) {
            int index = i;
            asyncThreadExecutor.execute(() -> {
                int startIndex = index * BatchConst.BATCH_NUMBER.intValue();
                int endIndex = (index + 1) * BatchConst.BATCH_NUMBER.intValue();
                if (endIndex > userIds.size()) {
                    endIndex = userIds.size();
                }
                List<Long> ids = userIds.subList(startIndex, endIndex);
                List<DwdUserLoginRecord> dwdUserLoginRecords = dwdUserLoginRecordMapper.statistics(dates, ids);
                dwBatchMapper.batchInsert(dwdUserLoginRecords, DwdUserLoginRecordMapper.class);
            });
        }
        try {
            asyncThreadExecutor.await();
        } catch (InterruptedException e) {
            log.error("dwdUserLoginRecord  syncData 执行异常", e);
            throw new RuntimeException(e);
        }
    }
}
