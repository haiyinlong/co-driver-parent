package com.leo.ad.codriver.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leo.ad.codriver.common.DwTaskTypeConstant;
import com.leo.ad.codriver.common.dao.DwTaskRecordMapper;
import com.leo.ad.codriver.common.dao.enetity.DwTaskRecord;
import com.leo.ad.codriver.common.service.DwTaskRecordService;

import lombok.RequiredArgsConstructor;

/**
 * @author user
 * @description 针对表【dw_task_record(数据同步记录,针对把的执行记录)】的数据库操作Service实现
 * @createDate 2024-11-11 15:21:49
 */
@Service
@RequiredArgsConstructor
public class DwTaskRecordServiceImpl implements DwTaskRecordService {

    private final DwTaskRecordMapper dwTaskRecordMapper;

    @Override
    public DwTaskRecord getOetaGameRecordLastTaskRecord(Integer dates) {
        // 获取所有数据，获取最大endId的记录返回
        return dwTaskRecordMapper.getLastTaskRecord(dates, DwTaskTypeConstant.GAME_RECORD_OETA);
    }

    @Override
    public List<DwTaskRecord> queryOetaGameRecordTaskRecordOfProcess(Integer dates) {
        return dwTaskRecordMapper.queryTaskRecord(dates, DwTaskTypeConstant.GAME_RECORD_OETA, 1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DwTaskRecord add(DwTaskRecord taskRecord) {
        dwTaskRecordMapper.insert(taskRecord);
        return taskRecord;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DwTaskRecord taskRecord) {
        dwTaskRecordMapper.updateById(taskRecord);
    }

}
